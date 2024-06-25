package com.user.redex.business.service.impl;

import com.user.redex.business.dto.request.AuthRequest;
import com.user.redex.business.dto.request.RestPasswordRequest;
import com.user.redex.business.dto.response.GQLResponse;
import com.user.redex.business.dto.response.TokenResponse;
import com.user.redex.business.repository.AuthorRepository;
import com.user.redex.business.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nabeel Ahmed
 */
@Service
public class AuthServiceImpl implements AuthService {

    private Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public GQLResponse<TokenResponse> getToken(AuthRequest payload) throws Exception {
        return null;
    }

    @Override
    public GQLResponse<?> forgotPassword(String username) throws Exception {
        return null;
    }

    @Override
    public GQLResponse<?> restPassword(RestPasswordRequest payload) throws Exception {
        return null;
    }
}
