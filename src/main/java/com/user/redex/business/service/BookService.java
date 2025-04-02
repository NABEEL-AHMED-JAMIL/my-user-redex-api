package com.user.redex.business.service;

import com.user.redex.business.dto.request.BookRequest;
import com.user.redex.business.dto.response.BookListResponse;
import com.user.redex.business.dto.response.GQLResponse;

/**
 * @author Nabeel Ahmed
 */
public interface BookService extends EntityService<BookRequest> {

	public GQLResponse<BookListResponse> getAllBookSearch(String search) throws Exception;

}
