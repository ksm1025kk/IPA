package com.os.util;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

    @Column(nullable = false)
//    @CreationTimestamp
    private LocalDateTime createTime;                // 결제 생성시간

    @Column(nullable = false)
//    @UpdateTimestamp
    private LocalDateTime updateTime;               // 수정시간

}
