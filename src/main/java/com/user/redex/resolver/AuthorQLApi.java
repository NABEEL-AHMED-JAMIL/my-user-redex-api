package com.user.redex.resolver;

import com.user.redex.business.dto.request.AuthorRequest;
import com.user.redex.business.dto.response.QLResponse;
import com.user.redex.business.dto.response.AuthorResponse;
import com.user.redex.util.ExceptionUtil;
import com.user.redex.util.ReduxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.user.redex.business.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Api use to perform crud operation
 * @author Nabeel Ahmed
 * Implements IEntityQLApi<AuthorRequest, AuthorResponse> not worked due to this statment
 * [GraphQL exposes a single endpoint URL for all queries and mutations]
 */
@RestController
public class AuthorQLApi {

    private Logger logger = LoggerFactory.getLogger(AuthorQLApi.class);

    @Autowired
    private AuthorService authorService;

    @MutationMapping
    public QLResponse<AuthorResponse> createAuthor(@Argument() AuthorRequest author) {
        try {
            return this.authorService.createEntity(author);
        } catch (Exception ex) {
            logger.error("An error occurred while createAuthor[AuthorResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    @MutationMapping
    public QLResponse<AuthorResponse> updateAuthor(@Argument() AuthorRequest author) {
        try {
            return this.authorService.updateEntity(author);
        } catch (Exception ex) {
            logger.error("An error occurred while updateAuthor[AuthorResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    @MutationMapping
    public QLResponse<AuthorResponse> deleteAuthor(@Argument(value = "id") String id) {
        try {
            return this.authorService.deleteEntity(id);
        } catch (Exception ex) {
            logger.error("An error occurred while deleteAuthor[AuthorResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    @QueryMapping
    public QLResponse<AuthorResponse> getAuthor(@Argument(value = "id") String id) {
        try {
            return this.authorService.getEntity(id);
        } catch (Exception ex) {
            logger.error("An error occurred while getAuthor[AuthorResponse] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    @QueryMapping
    public QLResponse<List<AuthorResponse>> getAllAuthors() {
        try {
            return this.authorService.getAllEntities();
        } catch (Exception ex) {
            logger.error("An error occurred while getAllAuthors[List<AuthorResponse>] ", ExceptionUtil.getRootCause(ex));
            return new QLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

}
