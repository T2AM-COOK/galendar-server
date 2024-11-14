package com.galendar.global.security;

import com.galendar.domain.user.dto.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

//@Getter
//public class CustomUserDetails implements UserDetails {
//
//    private final User user;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    private CustomUserDetails(final User user, final Collection<? extends GrantedAuthority> authorities) {
//        this.user = user;
//        this.authorities = authorities;
//    }
//
//    public static CustomUserDetails create(User user) {
//        return new CustomUserDetails(user, Collections.singleton((GrantedAuthority) user.getRole()::getKey));
//    }
//
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        return authorities;
////    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        return authorities;
//
//        Collection<GrantedAuthority> collection = new ArrayList<>();
//
//        collection.add(new GrantedAuthority() {
//
//            @Override
//            public String getAuthority() {
//
//                return userEntity.getRole().value();
//            }
//        });
//
//        return collection;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;
    private Collection<? extends GrantedAuthority> authorities;

    private CustomUserDetails(final User user, final Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public static CustomUserDetails create(User user) {
        return new CustomUserDetails(user, Collections.singleton((GrantedAuthority) user.getRole()::getKey));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().name();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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