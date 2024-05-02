package com.os.security;

import com.os.user.entity.User;
import com.os.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     @method : loadUserByUsername
     @desc : 주어진 이메일을 사용하여 사용자의 인증을 처리하는 메서드
     @author : 김홍성
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null){
            throw new UsernameNotFoundException("존재하지 않는 이메일 입니다 다시 확인해주세요.");
        }

        return new CustomUserDetails(user);
    }
}
