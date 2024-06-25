package com.user.redex.business.service;

import com.user.redex.business.dto.request.AuthorRequest;
import com.user.redex.business.dto.response.AuthorResponse;
import com.user.redex.business.dto.response.GQLResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Nabeel Ahmed
 */
public interface AuthorService extends EntityService<AuthorRequest, AuthorResponse> {

    GQLResponse<AuthorResponse> uploadAuthorImage(MultipartFile file, AuthorRequest payload) throws Exception;

}
