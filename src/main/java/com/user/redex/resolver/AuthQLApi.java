package com.user.redex.resolver;

import com.user.redex.business.dto.request.AuthRequest;
import com.user.redex.business.dto.request.RestPasswordRequest;
import com.user.redex.business.dto.response.GQLResponse;
import com.user.redex.business.dto.response.TokenResponse;
import com.user.redex.business.service.AuthService;
import com.user.redex.util.ExceptionUtil;
import com.user.redex.util.ReduxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api use to perform crud operation
 * @author Nabeel Ahmed
 */
@RestController
public class AuthQLApi {

    private Logger logger = LoggerFactory.getLogger(AuthQLApi.class);

    @Autowired
    private AuthService authService;


    /**
     * QL method use to login the user and get the token
     * @param payload
     * return QLResponse<TokenResponse>
     * */
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public GQLResponse<TokenResponse> getToken(@Argument() AuthRequest payload) {
        try {
            return this.authService.getToken(payload);
        } catch (Exception ex) {
            logger.error("An error occurred while deleteAuthor[TokenResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * QL method use to send email to client with url for reset password
     * @param username
     * return QLResponse<?>
     * */
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public GQLResponse<?> forgotPassword(@Argument(value = "username") String username) {
        try {
            return this.authService.forgotPassword(username);
        } catch (Exception ex) {
            logger.error("An error occurred while forgotPassword[?] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * QL method use to reset password
     * @param payload
     * return QLResponse<?>
     * */
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public GQLResponse<?> restPassword(@Argument() RestPasswordRequest payload) {
        try {
            return this.authService.restPassword(payload);
        } catch (Exception ex) {
            logger.error("An error occurred while restPassword[?] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

}
