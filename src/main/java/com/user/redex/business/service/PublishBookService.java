package com.user.redex.business.service;

import com.user.redex.business.dto.response.AuthorResponse;
import com.user.redex.business.dto.response.BookResponse;
import com.user.redex.business.dto.response.QLResponse;
import java.util.List;

/**
 * @author Nabeel Ahmed
 */
public interface PublishBookService {

    QLResponse<List<BookResponse>> fetchPublicBooks() throws Exception;

    QLResponse<List<AuthorResponse>> fetchPublicAuthors() throws Exception;

}
