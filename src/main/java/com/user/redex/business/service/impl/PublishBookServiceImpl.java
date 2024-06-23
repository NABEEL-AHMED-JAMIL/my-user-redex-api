package com.user.redex.business.service.impl;

import com.user.redex.business.dto.response.AuthorListResponse;
import com.user.redex.business.dto.response.BookListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.user.redex.business.dto.response.QLResponse;
import com.user.redex.business.service.AuthorService;
import com.user.redex.business.service.BookService;
import com.user.redex.business.service.PublishBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @return QLResponse<BookListResponse>
     * @throws Exception
     * */
    public QLResponse<BookListResponse> fetchPublicBooks() throws Exception {
        logger.info("Request For Fetch List Books.");
        return (QLResponse<BookListResponse>) this.bookService.getAllEntities();
    }

    /**
     * Method use to fetch the public authors
     * @return QLResponse<AuthorListResponse>
     * @throws Exception
     * */
    public QLResponse<AuthorListResponse> fetchPublicAuthors() throws Exception {
        logger.info("Request For Fetch List Authors.");
        return (QLResponse<AuthorListResponse>) this.authorService.getAllEntities();
    }

}
