package com.example.temipj.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Objects;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String membername;

    @Column(nullable = false, unique = true)
    private String emailId;

    @Column(nullable = false)
    private String password;

//    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Employee> employee;

//    public Member(String emailId, String membername, String encodedPassword) {
//        this.emailId = emailId;
//        this.membername = membername;
//        this.password = encodedPassword;
//    }

    @Override
    public boolean equals(Object obj) { //데이터 타입이 Object인 obj 값을 받는 equals 메서드
        if (this == obj) { //조건문을 통해 주소 값과 실제 저장된 값을 동일하게 인식하게 해주는 작업을 진행
            return true;
        }
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            return false;
        }
        Member member = (Member) obj;
        return id != null && Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void nameUpdate(String username) {
        this.membername = membername;
    }

    public void passwordUpdate(String password) {
        this.password = password;
    }

    public boolean validatePassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.password);
    }
}