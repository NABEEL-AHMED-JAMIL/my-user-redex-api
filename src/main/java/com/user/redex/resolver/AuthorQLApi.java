package com.user.redex.resolver;

import com.user.redex.business.dto.request.AuthorRequest;
import com.user.redex.business.dto.response.AuthorListResponse;
import com.user.redex.business.dto.response.GQLResponse;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api use to perform crud operation
 * @author Nabeel Ahmed
 * Implements IEntityQLApi<AuthorRequest, AuthorResponse> not worked due to this statment
 * [GraphQL exposes a single endpoint URL for all queries and mutations]
 * @author Nabeel Ahmed
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorQLApi {

    private Logger logger = LoggerFactory.getLogger(AuthorQLApi.class);

    @Autowired
    private AuthorService authorService;

    public AuthorQLApi() { }

    /**
     * QL method use to create the author
     * @param payload
     * return QLResponse<AuthorResponse>
     * */
    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public GQLResponse<AuthorResponse> createAuthor(@Argument() AuthorRequest payload) {
        try {
            return (GQLResponse<AuthorResponse>) this.authorService.createEntity(payload);
        } catch (Exception ex) {
            logger.error("An error occurred while createAuthor[AuthorResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * QL method use to update the author
     * @param payload
     * return QLResponse<AuthorResponse>
     * */
    @MutationMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public GQLResponse<AuthorResponse> updateAuthor(@Argument() AuthorRequest payload) {
        try {
            return (GQLResponse<AuthorResponse>) this.authorService.updateEntity(payload);
        } catch (Exception ex) {
            logger.error("An error occurred while updateAuthor[AuthorResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * QL method use to delete the author by id
     * @param id
     * return QLResponse<AuthorResponse>
     * */
    @MutationMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public GQLResponse<AuthorResponse> deleteAuthor(@Argument(value = "id") String id) {
        try {
            return (GQLResponse<AuthorResponse>) this.authorService.deleteEntity(id);
        } catch (Exception ex) {
            logger.error("An error occurred while deleteAuthor[AuthorResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * QL method use to fetch the author by id
     * @param id
     * return QLResponse<AuthorResponse>
     * */
    @QueryMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public GQLResponse<AuthorResponse> getAuthor(@Argument(value = "id") String id) {
        try {
            return (GQLResponse<AuthorResponse>) this.authorService.getEntity(id);
        } catch (Exception ex) {
            logger.error("An error occurred while getAuthor[AuthorResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * QL method use to fetch all the authors
     * return QLResponse<AuthorListResponse>
     * */
    @QueryMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public GQLResponse<AuthorListResponse> getAllAuthors() {
        try {
            return (GQLResponse<AuthorListResponse>) this.authorService.getAllEntities();
        } catch (Exception ex) {
            logger.error("An error occurred while getAllAuthors[AuthorListResponse] ",
                ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

    /**
     * QL method use to fetch all the authors by search
     * return QLResponse<AuthorListResponse>
     * */
    @QueryMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public GQLResponse<AuthorListResponse> getAllAuthoritySearch(@Argument(value = "search") String search) {
        try {
            return (GQLResponse<AuthorListResponse>) this.authorService.getAllAuthoritySearch(search);
        } catch (Exception ex) {
            logger.error("An error occurred while getAllAuthoritySearch[AuthorListResponse] ", ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

}
