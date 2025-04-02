package com.user.redex.business.service;

import com.user.redex.business.dto.request.AuthorRequest;
import com.user.redex.business.dto.response.AuthorListResponse;
import com.user.redex.business.dto.response.GQLResponse;

/**
 * @author Nabeel Ahmed
 */
public interface AuthorService extends EntityService<AuthorRequest> {

	public GQLResponse<AuthorListResponse> getAllAuthoritySearch(String search) throws Exception;

}
