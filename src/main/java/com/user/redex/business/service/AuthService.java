package com.user.redex.business.service;

import com.user.redex.business.dto.request.AuthRequest;
import com.user.redex.business.dto.request.RestPasswordRequest;
import com.user.redex.business.dto.response.GQLResponse;
import com.user.redex.business.dto.response.TokenResponse;

/**
 * @author Nabeel Ahmed
 */
public interface AuthService {

    public GQLResponse<TokenResponse> getToken(AuthRequest payload) throws Exception;

    public GQLResponse<?> forgotPassword(String username) throws Exception;

    public GQLResponse<?> resetPassword(RestPasswordRequest payload) throws Exception;

}
