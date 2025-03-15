package com.user.redex.resolver;

import com.user.redex.business.dto.request.AuthRequest;
import com.user.redex.business.dto.request.RestPasswordRequest;
import com.user.redex.business.dto.response.AuthorResponse;
import com.user.redex.business.dto.response.GQLResponse;
import com.user.redex.business.dto.response.TokenResponse;
import com.user.redex.business.service.AuthService;
import com.user.redex.util.ExceptionUtil;
import com.user.redex.util.ReduxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Api use to perform crud operation
 * @author Nabeel Ahmed
 */
@RestController
@RequestMapping(value="/auth")
public class AuthQLApi {

    private Logger logger = LoggerFactory.getLogger(AuthQLApi.class);

    @Autowired
    private AuthService authService;

    public AuthQLApi() { }

    /**
     * QL method use to login the user and get the token
     * @param payload
     * return QLResponse<TokenResponse>
     * */
    @RequestMapping(value="/getToken", method=RequestMethod.POST)
    public GQLResponse<TokenResponse> getToken(@RequestBody AuthRequest payload) {
        try {
            return this.authService.getToken(payload);
        } catch (Exception ex) {
            logger.error("An error occurred while getToken[TokenResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * QL method use to send email to client with url for reset password
     * @param username
     * return QLResponse<?>
     * */
    @RequestMapping(value="/forgotPassword", method=RequestMethod.POST)
    public GQLResponse<AuthorResponse> forgotPassword(@RequestParam(value = "username") String username) {
        try {
            return this.authService.forgotPassword(username);
        } catch (Exception ex) {
            logger.error("An error occurred while forgotPassword[AuthorResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * QL method use to reset password
     * @param payload
     * return QLResponse<?>
     * */
    @RequestMapping(value="/resetPassword", method= RequestMethod.POST)
    public GQLResponse<?> resetPassword(@RequestBody RestPasswordRequest payload) {
        try {
            return this.authService.resetPassword(payload);
        } catch (Exception ex) {
            logger.error("An error occurred while restPassword[?] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

}
