package com.os.controller;
import com.os.security.CustomUserDetails;
import com.os.user.dto.UpdateUserRequest;
import com.os.user.entity.User;
import com.os.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user/update")
    public ResponseEntity<?> updatePassword(@RequestBody UpdateUserRequest request) {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = currentUser.getUserId();

        User user = userService.getUserById(userId);
        String storedPassword = user.getPassword();
        if (!passwordEncoder.matches(request.getCurrentPassword(), storedPassword)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("현재 비밀번호가 일치하지 않습니다.");
        }

        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("새로운 비밀번호가 일치하지 않습니다.");
        }

        userService.updatePassword(userId, request.getNewPassword());

        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }
}