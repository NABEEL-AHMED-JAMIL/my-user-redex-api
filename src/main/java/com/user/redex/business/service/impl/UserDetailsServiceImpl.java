package com.user.redex.business.service.impl;

import com.user.redex.business.document.Author;
import com.user.redex.business.enums.Status;
import com.user.redex.business.repository.AuthorRepository;
import com.user.redex.security.UserDetailsExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * @author Nabeel Ahmed
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;
    /**
     * loadUserByUsername method provide the auth user detail
     * user can login with email or username
     * @param username
     * @return UserDetails
     * */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Author> author = this.authorRepository.findByUsernameAndStatus(username, Status.ACTIVE);
        if (author.isPresent()) {
            return UserDetailsExt.build(author.get());
        }
        throw new UsernameNotFoundException("User Not Found with username : " + username);
    }
}
