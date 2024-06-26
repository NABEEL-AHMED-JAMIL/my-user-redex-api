package com.user.redex.resolver;

import com.user.redex.business.dto.request.BookRequest;
import com.user.redex.business.dto.response.BookListResponse;
import com.user.redex.business.dto.response.GQLResponse;
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
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * Method use to create the book for login author
     * @param payload
     * @return GQLResponse<BookResponse>
     * */
    @MutationMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public GQLResponse<BookResponse> createBook(@Argument() BookRequest payload) {
        try {
            return this.bookService.createEntity(payload);
        } catch (Exception ex) {
            logger.error("An error occurred while createBook[BookResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * Method use to update the book for login author
     * @param payload
     * @return GQLResponse<BookResponse>
     * */
    @MutationMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public GQLResponse<BookResponse> updateBook(@Argument() BookRequest payload) {
        try {
            return this.bookService.updateEntity(payload);
        } catch (Exception ex) {
            logger.error("An error occurred while updateBook[BookResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * Method use to delete the book for login author
     * @param id
     * @return GQLResponse<BookResponse>
     * */
    @MutationMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public GQLResponse<BookResponse> deleteBook(@Argument(value = "id") String id) {
        try {
            return this.bookService.deleteEntity(id);
        } catch (Exception ex) {
            logger.error("An error occurred while deleteBook[BookResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * Method use to get the book for login author
     * @param id
     * @return GQLResponse<BookResponse>
     * */
    @QueryMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public GQLResponse<BookResponse> getBook(@Argument(value = "id") String id) {
        try {
            return this.bookService.getEntity(id);
        } catch (Exception ex) {
            logger.error("An error occurred while getBook[BookResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * Method use to get all books [public api also use same api]
     * @return GQLResponse<BookListResponse>
     * */
    @QueryMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public GQLResponse<BookListResponse> getAllBooks() {
        try {
            return (GQLResponse<BookListResponse>) this.bookService.getAllEntities();
        } catch (Exception ex) {
            logger.error("An error occurred while getAllBooks[BookListResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

}
