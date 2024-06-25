package com.user.redex.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.user.redex.business.document.Author;
import com.user.redex.business.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsExt implements UserDetails {

    private String appUserId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String image;
    private Role role; // for token payload
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsExt() {
    }

    /**
     * build method provide the auth user detail
     * @param author => db user detail wrap into UserDetailsImpl
     * @return UserDetailsImpl
     * */
    public static UserDetailsExt build(Author author) {
        UserDetailsExt userDetailsExt = new UserDetailsExt();
        userDetailsExt.setAppUserId(author.getId());
        userDetailsExt.setFirstName(author.getFirstName());
        userDetailsExt.setLastName(author.getLastName());
        userDetailsExt.setUsername(author.getUsername());
        userDetailsExt.setEmail(author.getEmail());
        userDetailsExt.setPassword(author.getPassword());
        userDetailsExt.setImage(author.getImage());
        userDetailsExt.setRole(author.getRole());
        userDetailsExt.setAuthorities(List.of(new SimpleGrantedAuthority(author.getRole().name())));
        return userDetailsExt;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

