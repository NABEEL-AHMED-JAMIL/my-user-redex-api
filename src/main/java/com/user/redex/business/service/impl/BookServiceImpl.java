package com.user.redex.business.service.impl;

import com.user.redex.business.converter.AuthorConverter;
import com.user.redex.business.converter.BookConverter;
import com.user.redex.business.dto.request.BookRequest;
import com.user.redex.business.dto.response.QLResponse;
import com.user.redex.business.dto.response.BookResponse;
import com.user.redex.business.repository.AuthorRepository;
import com.user.redex.business.repository.BookRepository;
import com.user.redex.business.service.BookService;
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
@Resource(name="bookService")
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private AuthorConverter authorConverter;
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private BookRepository patientRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public BookServiceImpl() {
    }

    @Override
    public QLResponse<BookResponse> createEntity(BookRequest entity) throws Exception {
        logger.info("Request For New Book :- " + entity);
        return null;
    }

    @Override
    public QLResponse<BookResponse> updateEntity(BookRequest entity) throws Exception {
        logger.info("Request For Update Book :- " + entity);
        return null;
    }

    @Override
    public QLResponse<BookResponse> deleteEntity(String id) throws Exception {
        logger.info("Request For Delete Book BY ID :- " + id);
        return null;
    }

    @Override
    public QLResponse<BookResponse> getEntity(String id) throws Exception {
        logger.info("Request For Get Book BY ID :- " + id);
        return null;
    }

    @Override
    public QLResponse<List<BookResponse>> getAllEntities() throws Exception {
        logger.info("Request For Get All Books :- ");
        return null;
    }

}
