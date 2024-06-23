package com.user.redex.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.user.redex.business.dto.response.AuthorResponse;
import com.user.redex.business.dto.response.BookResponse;
import com.user.redex.business.dto.response.QLResponse;
import com.user.redex.business.service.AuthorService;
import com.user.redex.business.service.BookService;
import com.user.redex.business.service.PublishBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Nabeel Ahmed
 */
@Service
public class PublishBookServiceImpl implements PublishBookService {

    private Logger logger = LoggerFactory.getLogger(PublishBookServiceImpl.class);

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    /**
     * Method use to fetch the public book
     * @return QLResponse<List<BookResponse>>
     * @throws Exception
     * */
    public QLResponse<List<BookResponse>> fetchPublicBooks() throws Exception {
        logger.info("Request For Fetch List Books.");
        return this.bookService.getAllEntities();
    }

    /**
     * Method use to fetch the public authors
     * @return QLResponse<List<AuthorResponse>>
     * @throws Exception
     * */
    public QLResponse<List<AuthorResponse>> fetchPublicAuthors() throws Exception {
        logger.info("Request For Fetch List Authors.");
        return this.authorService.getAllEntities();
    }



}
