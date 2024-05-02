package com.os.user.entity;

import com.os.customer.entity.Customer;
import com.os.memo.entity.Memo;
import com.os.save.entity.SavePayment;
import com.os.util.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false ,unique = true)
    @Email(message = "email 형식으로 작성해주세요")
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<Memo> memos;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<Customer> customer;

    @OneToOne(mappedBy= "user" , cascade = CascadeType.ALL)
    private SavePayment savePayment;

    @Builder
    public User(Long id, String username, String email, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;

    }
}
