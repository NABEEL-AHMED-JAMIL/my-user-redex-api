package com.user.redex.business.service.impl;

import com.user.redex.business.dto.request.AuthorRequest;
import com.user.redex.business.dto.response.QLResponse;
import com.user.redex.business.dto.response.AuthorResponse;
import com.user.redex.business.repository.AuthorRepository;
import com.user.redex.business.service.AuthorService;
import com.user.redex.util.ExceptionUtil;
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
    private AuthorRepository authorRepository;

    public AuthorServiceImpl() {
    }

    @Override
    public QLResponse<AuthorResponse> createEntity(AuthorRequest entity) {
        return null;
    }

    @Override
    public QLResponse<AuthorResponse> updateEntity(AuthorRequest entity) {
        return null;
    }

    @Override
    public QLResponse<AuthorResponse> deleteEntity(String id) {
        return null;
    }

    @Override
    public QLResponse<AuthorResponse> getEntity(String id) {
        return null;
    }

    @Override
    public QLResponse<List<AuthorResponse>> getAllEntities() {
        return new QLResponse("Helo pakistan", ReduxUtil.ERROR);
    }
}
