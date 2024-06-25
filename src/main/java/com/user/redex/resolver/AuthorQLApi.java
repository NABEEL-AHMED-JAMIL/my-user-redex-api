package com.user.redex.resolver;

import com.google.gson.Gson;
import com.user.redex.business.dto.request.AuthorRequest;
import com.user.redex.business.dto.request.FileUploadRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * QL method use to create the author
     * @param payload
     * return QLResponse<AuthorResponse>
     * */
    @MutationMapping
    public GQLResponse<AuthorResponse> createAuthor(@Argument() AuthorRequest payload) {
        try {
            return this.authorService.createEntity(payload);
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
    public GQLResponse<AuthorResponse> updateAuthor(@Argument() AuthorRequest payload) {
        try {
            return this.authorService.updateEntity(payload);
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
    public GQLResponse<AuthorResponse> deleteAuthor(@Argument(value = "id") String id) {
        try {
            return this.authorService.deleteEntity(id);
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
    public GQLResponse<AuthorResponse> getAuthor(@Argument(value = "id") String id) {
        try {
            return this.authorService.getEntity(id);
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
     * Rest-API for upload the file.
     * QL not direct support to file upload for upload with ql you need to add extra dependency
     * so avoid this and using rest api
     * @param payload
     * return QLResponse<AuthorListResponse>
     * */
    @RequestMapping(value = "/uploadAuthorImage", method = RequestMethod.POST)
    public GQLResponse<AuthorResponse> uploadAuthorImage(FileUploadRequest payload) {
        try {
            if (ReduxUtil.isNull(payload.getFile())) {
                return new GQLResponse<>("Author file required.", ReduxUtil.ERROR);
            } else if (ReduxUtil.isNull(payload.getData())) {
                return new GQLResponse<>("Author payload required.", ReduxUtil.ERROR);
            }
            AuthorRequest authorRequest = new Gson().fromJson(payload.getData(), AuthorRequest.class);
            return this.authorService.uploadAuthorImage(payload.getFile(), authorRequest);
        } catch (Exception ex) {
            logger.error("An error occurred while uploadAuthorImage[AuthorResponse] ",
                ExceptionUtil.getRootCause(ex));
            return new GQLResponse(ExceptionUtil.getRootCauseMessage(ex), ReduxUtil.ERROR);
        }
    }

}
