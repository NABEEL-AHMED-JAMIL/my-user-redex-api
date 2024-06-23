package com.user.redex.business.service.impl;

import com.user.redex.business.converter.AuthorConverter;
import com.user.redex.business.converter.BookConverter;
import com.user.redex.business.document.Author;
import com.user.redex.business.dto.request.AuthorRequest;
import com.user.redex.business.dto.response.QLResponse;
import com.user.redex.business.dto.response.AuthorResponse;
import com.user.redex.business.enums.Status;
import com.user.redex.business.repository.AuthorRepository;
import com.user.redex.business.service.AuthorService;
import com.user.redex.util.ReduxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Nabeel Ahmed
 */
@Service
@Resource(name="authorService")
public class AuthorServiceImpl implements AuthorService {

    private Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorConverter authorConverter;
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorServiceImpl() {
    }

    /**
     * Method use to create the author/register
     * Basic validation on ui/form side here double check
     * @param request
     * @return QLResponse<AuthorResponse>
     * @throws Exception
     * */
    @Override
    public QLResponse<AuthorResponse> createEntity(AuthorRequest request) throws Exception {
        logger.info("Request For New Author :- " + request);
        if (ReduxUtil.isNull(request.getFirstName())) {
            return new QLResponse("Author firstName required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getLastName())) {
            return new QLResponse("Author lastName required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getEmail())) {
            return new QLResponse("Author email required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getUsername())) {
            return new QLResponse("Author username required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getPassword())) {
            return new QLResponse("Author password required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getBiography())) {
            return new QLResponse("Author biography required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getNationality())) {
            return new QLResponse("Author nationality required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getExpertise())) {
            return new QLResponse("Author expertise required.", ReduxUtil.ERROR);
        }
        // db check author email and username
        if (ReduxUtil.isNull(request.getEmail())) {
            return new QLResponse("Author email already exist.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(request.getUsername())) {
            return new QLResponse("Author username already exist.", ReduxUtil.ERROR);
        }
        Author author = this.authorConverter.convertToAuthor(request, new Author());
        author.setStatus(Status.ACTIVE);
        author = this.authorRepository.save(author);
        AuthorResponse authorResponse = this.authorConverter.convertToAuthor(author);
        return new QLResponse("Author save successfully.", ReduxUtil.SUCCESS, authorResponse);
    }

    /**
     * Method use to update the author
     * @param entity
     * @return QLResponse<AuthorResponse>
     * @throws Exception
     * */
    @Override
    public QLResponse<AuthorResponse> updateEntity(AuthorRequest entity) throws Exception {
        logger.info("Request For Update Author :- " + entity);
        return null;
    }

    /**
     * Method use to delete the author by id
     * @param id
     * @return QLResponse<AuthorResponse>
     * @throws Exception
     * */
    @Override
    public QLResponse<AuthorResponse> deleteEntity(String id) throws Exception {
        logger.info("Request For Delete Author BY ID :- " + id);
        return null;
    }

    /**
     * Method use to get the author by id
     * @param id
     * @return QLResponse<AuthorResponse>
     * @throws Exception
     * */
    @Override
    public QLResponse<AuthorResponse> getEntity(String id) throws Exception {
        logger.info("Request For Get Author BY ID :- " + id);
        return null;
    }

    /**
     * Method use to get all author
     * @return QLResponse<List<AuthorResponse>>
     * @throws Exception
     * */
    @Override
    public QLResponse<List<AuthorResponse>> getAllEntities() throws Exception {
        logger.info("Request For Get All Authors :- ");
        return new QLResponse("Helo pakistan", ReduxUtil.ERROR);
    }
}
