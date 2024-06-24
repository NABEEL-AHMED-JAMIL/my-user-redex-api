package com.user.redex.business.service;

import com.user.redex.business.dto.response.AuthorListResponse;
import com.user.redex.business.dto.response.BookListResponse;
import com.user.redex.business.dto.response.GQLResponse;

/**
 * @author Nabeel Ahmed
 */
public interface PublishBookService {

    GQLResponse<BookListResponse> fetchPublicBooks() throws Exception;

    GQLResponse<AuthorListResponse> fetchPublicAuthors() throws Exception;

}
