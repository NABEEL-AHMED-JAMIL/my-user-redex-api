package com.user.redex.resolver;

import com.user.redex.business.dto.response.AuthorListResponse;
import com.user.redex.business.dto.response.BookListResponse;
import com.user.redex.business.dto.response.QLResponse;
import com.user.redex.business.service.PublishBookService;
import com.user.redex.util.ExceptionUtil;
import com.user.redex.util.ReduxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api use to perform crud operation
 * @author Nabeel Ahmed
 */
@RestController
public class PublishBookQLApi {

    private Logger logger = LoggerFactory.getLogger(PublishBookQLApi.class);

    private PublishBookService publishBookService;

    /**
     * Api use to fetch the public books
     * QLResponse<List<BookResponse>>
     * */
    public QLResponse<BookListResponse> fetchPublicBooks() {
        try {
            return this.publishBookService.fetchPublicBooks();
        } catch (Exception ex) {
            logger.error("An error occurred while fetchPublicBooks[BookListResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * Api use to fetch the public authors
     * QLResponse<AuthorListResponse>
     * */
    public QLResponse<AuthorListResponse> fetchPublicAuthors() {
        try {
            return this.publishBookService.fetchPublicAuthors();
        } catch (Exception ex) {
            logger.error("An error occurred while fetchPublicAuthors[AuthorListResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }
}
