package com.user.redex.business.service;

import com.user.redex.business.dto.response.AuthorListResponse;
import com.user.redex.business.dto.response.BookListResponse;
import com.user.redex.business.dto.response.QLResponse;

/**
 * @author Nabeel Ahmed
 */
public interface PublishBookService {

    QLResponse<BookListResponse> fetchPublicBooks() throws Exception;

    QLResponse<AuthorListResponse> fetchPublicAuthors() throws Exception;

}
