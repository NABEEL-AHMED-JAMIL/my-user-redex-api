package com.user.redex.business.service.impl;

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
import org.springframework.stereotype.Service;

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
        return null;
    }

    /**
     * Method use to reset password
     * @param payload
     * @return GQLResponse<?>
     * */
    @Override
    public GQLResponse<?> restPassword(RestPasswordRequest payload) throws Exception {
        return null;
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

}
