package yoon.project.onlineShop.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import yoon.project.onlineShop.enums.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="member")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(nullable = false, length = 50)
    private String id;

    @Column(nullable = false, length = 250)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime regdate;

    @Builder
    public Members(String id, String password, String name, Role role){
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.getRoleKey()));
        return authorities;
    }

}
