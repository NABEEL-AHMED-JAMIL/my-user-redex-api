package com.user.redex.resolver;

import com.user.redex.business.dto.request.BookRequest;
import com.user.redex.business.dto.response.BookListResponse;
import com.user.redex.business.dto.response.QLResponse;
import com.user.redex.business.dto.response.BookResponse;
import com.user.redex.business.service.BookService;
import com.user.redex.util.ExceptionUtil;
import com.user.redex.util.ReduxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api use to perform crud operation
 * @author Nabeel Ahmed
 * Implements IEntityQLApi<BookRequest, BookResponse> not worked due to this statment
 * [GraphQL exposes a single endpoint URL for all queries and mutations]
 */
@RestController
public class BookQLApi {

    private Logger logger = LoggerFactory.getLogger(BookQLApi.class);

    @Autowired
    private BookService bookService;

    @MutationMapping
    public QLResponse<BookResponse> createBook(@Argument() BookRequest book) {
        try {
            return this.bookService.createEntity(book);
        } catch (Exception ex) {
            logger.error("An error occurred while createBook[BookResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    @MutationMapping
    public QLResponse<BookResponse> updateBook(@Argument() BookRequest book) {
        try {
            return this.bookService.updateEntity(book);
        } catch (Exception ex) {
            logger.error("An error occurred while updateBook[BookResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    @MutationMapping
    public QLResponse<BookResponse> deleteBook(@Argument(value = "id") String id) {
        try {
            return this.bookService.deleteEntity(id);
        } catch (Exception ex) {
            logger.error("An error occurred while deleteBook[BookResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    @QueryMapping
    public QLResponse<BookResponse> getBook(@Argument(value = "id") String id) {
        try {
            return this.bookService.getEntity(id);
        } catch (Exception ex) {
            logger.error("An error occurred while getBook[BookResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    @QueryMapping
    public QLResponse<BookListResponse> getAllBooks() {
        try {
            return (QLResponse<BookListResponse>) this.bookService.getAllEntities();
        } catch (Exception ex) {
            logger.error("An error occurred while getAllBooks[BookResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

}
