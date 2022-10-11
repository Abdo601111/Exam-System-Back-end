package com.exam.model;

import com.exam.model.social.UserImages;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enable;
    private String phone;
    private String image;
    private String bio;
    private String profile;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> userRole= new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,)
    private Set<UserImages> userImages = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Set<Authority> authorities= new HashSet<>();
       this.userRole.forEach(userRole1 -> {
           authorities.add(new Authority(userRole1.getRole().getRoleName()));
       });

        return authorities;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
