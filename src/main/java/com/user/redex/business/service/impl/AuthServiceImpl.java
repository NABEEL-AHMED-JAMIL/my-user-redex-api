package com.user.redex.business.service.impl;

import com.user.redex.business.converter.AuthorConverter;
import com.user.redex.business.document.Author;
import com.user.redex.business.dto.response.AuthorResponse;
import com.user.redex.business.enums.Status;
import com.user.redex.business.repository.AuthorRepository;
import com.user.redex.manager.emailer.EmailMessageRequest;
import com.user.redex.manager.emailer.EmailMessagesFactory;
import com.user.redex.manager.velocity.TemplateType;
import com.user.redex.util.ExceptionUtil;
import com.user.redex.util.ReduxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.user.redex.business.dto.request.AuthRequest;
import com.user.redex.business.dto.request.RestPasswordRequest;
import com.user.redex.business.dto.response.GQLResponse;
import com.user.redex.business.dto.response.TokenResponse;
import com.user.redex.business.service.AuthService;
import com.user.redex.security.JwtTokenUtil;
import com.user.redex.security.UserDetailsExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * @author Nabeel Ahmed
 */
@Service
public class AuthServiceImpl implements AuthService {

    private Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthorConverter authorConverter;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailMessagesFactory emailMessagesFactory;

    public AuthServiceImpl() {
    }

    /**
     * Method use to generate the token
     * @param payload
     * @return GQLResponse<TokenResponse>
     * */
    @Override
    public GQLResponse<TokenResponse> getToken(AuthRequest payload) throws Exception {
        logger.info("Request For Token :- " + payload);
        Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get the user detail from authentication
        UserDetailsExt userDetailsExt = (UserDetailsExt) authentication.getPrincipal();
        TokenResponse tokenResponse = getTokenResponse(userDetailsExt);
        String token = this.jwtTokenUtil.generateTokenFromUsername(tokenResponse.getUsername());
        tokenResponse.setToken(token);
        return new GQLResponse("Author token fetch successfully.", ReduxUtil.SUCCESS, tokenResponse);
    }

    /**
     * Method use to forgot password
     * @param username
     * @return GQLResponse<?>
     * */
    @Override
    public GQLResponse<?> forgotPassword(String username) throws Exception {
        logger.info("Request forgotPassword :- " + username);
        if (ReduxUtil.isNull(username)) {
            return new GQLResponse("Username missing.", ReduxUtil.ERROR);
        }
        Optional<Author> author = this.authorRepository.findByUsernameAndStatus(username, Status.ACTIVE);
        if (author.isPresent()) {
            Thread registerForgotThread = new Thread(() -> {
                AuthorResponse authorResponse = this.authorConverter.convertToAuthor(author.get());
                this.sendForgotEmail(authorResponse);
            });
            registerForgotThread.start();
            return new GQLResponse("Email send successfully.", ReduxUtil.SUCCESS);
        }
        return new GQLResponse("Author not exist.", ReduxUtil.ERROR);
    }

    /**
     * Method use to reset password
     * @param payload
     * @return GQLResponse<?>
     * */
    @Override
    public GQLResponse<?> resetPassword(RestPasswordRequest payload) throws Exception {
        logger.info("Request resetPassword :- " + payload);
        if (ReduxUtil.isNull(payload.getUsername())) {
            return new GQLResponse("Username missing.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getNewPassword())) {
            return new GQLResponse("New password missing.", ReduxUtil.ERROR);
        }
        Optional<Author> author = this.authorRepository.findByUsernameAndStatus(payload.getUsername(), Status.ACTIVE);
        if (!author.isPresent()) {
            return new GQLResponse("Author not exist.", ReduxUtil.ERROR);
        }
        author.get().setPassword(this.passwordEncoder.encode(payload.getNewPassword()));
        this.authorRepository.save(author.get());
        Thread passwordRestThread = new Thread(() -> {
            AuthorResponse authorResponse = this.authorConverter.convertToAuthor(author.get());
            this.sendPasswordRestEmail(authorResponse);
        });
        passwordRestThread.start();
        return new GQLResponse("Email send successfully.", ReduxUtil.SUCCESS);
    }

    /**
     * Method use get the token response
     * @param userDetails
     * @return TokenResponse
     * */
    private TokenResponse getTokenResponse(UserDetailsExt userDetails) {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setFirstName(userDetails.getFirstName());
        tokenResponse.setLastName(userDetails.getLastName());
        tokenResponse.setUsername(userDetails.getUsername());
        tokenResponse.setEmail(userDetails.getEmail());
        tokenResponse.setRole(userDetails.getRole());
        return tokenResponse;
    }

    /**
     * Method use to send forgot password email
     * @param authorResponse
     * */
    private void sendForgotEmail(AuthorResponse authorResponse) {
        try {
            EmailMessageRequest emailMessageRequest = new EmailMessageRequest();
            emailMessageRequest.setTemplateType(TemplateType.FORGOT_PASS_PATH);
            emailMessageRequest.setRecipients(authorResponse.getEmail());
            emailMessageRequest.setSubject("Password Reset Request");
            emailMessageRequest.getBodyMap().put("author", authorResponse);
            logger.info(emailMessagesFactory.sendSimpleMail(emailMessageRequest));
        } catch (Exception ex) {
            logger.error("Error while sending register email :- " + ExceptionUtil.getRootCauseMessage(ex));
        }
    }

    /**
     * Method use to send forgot password email
     * @param authorResponse
     * */
    private void sendPasswordRestEmail(AuthorResponse authorResponse) {
        try {
            EmailMessageRequest emailMessageRequest = new EmailMessageRequest();
            emailMessageRequest.setTemplateType(TemplateType.PASSWORD_RESET_PATH);
            emailMessageRequest.setRecipients(authorResponse.getEmail());
            emailMessageRequest.setSubject("Password Successfully Reset");
            emailMessageRequest.getBodyMap().put("author", authorResponse);
            logger.info(emailMessagesFactory.sendSimpleMail(emailMessageRequest));
        } catch (Exception ex) {
            logger.error("Error while sending register email :- " + ExceptionUtil.getRootCauseMessage(ex));
        }
    }

}
