package com.user.redex.business.service.impl;

import com.user.redex.business.converter.AuthorConverter;
import com.user.redex.business.converter.BookConverter;
import com.user.redex.business.document.Book;
import com.user.redex.business.dto.request.BookRequest;
import com.user.redex.business.dto.response.*;
import com.user.redex.business.enums.Status;
import com.user.redex.business.repository.AuthorRepository;
import com.user.redex.business.repository.BookRepository;
import com.user.redex.business.service.BookService;
import com.user.redex.security.UserDetailsExt;
import com.user.redex.util.ReduxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public BookServiceImpl() {
    }

    /**
     * Method use to add the book
     * @param payload
     * @return QLResponse<BookResponse>
     * @throws Exception
     * */
    @Override
    public GQLResponse<BookResponse> createEntity(BookRequest payload) throws Exception {
        logger.info("Request For New Book :- " + payload);
        // get the user detail from authentication
        UserDetailsExt userDetails = (UserDetailsExt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    /**
     * Method use to update the book
     * @param payload
     * @return QLResponse<BookResponse>
     * @throws Exception
     * */
    @Override
    public GQLResponse<BookResponse> updateEntity(BookRequest payload) throws Exception {
        logger.info("Request For Update Book :- " + payload);
        // get the user detail from authentication
        UserDetailsExt userDetails = (UserDetailsExt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    /**
     * Method use to delete the book by id
     * @param id
     * @return QLResponse<BookResponse>
     * @throws Exception
     * */
    @Override
    public GQLResponse<BookResponse> deleteEntity(String id) throws Exception {
        logger.info("Request For Delete Book BY ID :- " + id);
        // get the user detail from authentication
        UserDetailsExt userDetails = (UserDetailsExt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    /**
     * Method use to get the book by id
     * @param id
     * @return QLResponse<BookResponse>
     * @throws Exception
     * */
    @Override
    public GQLResponse<BookResponse> getEntity(String id) throws Exception {
        logger.info("Request For Get Book BY ID :- " + id);
        // get the user detail from authentication
        UserDetailsExt userDetails = (UserDetailsExt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    /**
     * Method use to fetch all book
     * @return QLResponse<List<BookResponse>>
     * @throws Exception
     * */
    @Override
    public GQLResponse<BookListResponse> getAllEntities() throws Exception {
        logger.info("Request For Get All Books :- ");
        List<BookResponse> bookResponses = this.bookRepository.findAllByStatusNotAndCoverImgNotNullAndBookUrlNotNull(Status.DELETE)
            .stream().filter(author -> author.getStatus().equals(Status.ACTIVE))
            .map(book -> this.getBookResponse(book)).collect(Collectors.toList());
        return new GQLResponse("Books fetch successfully.", ReduxUtil.SUCCESS, new BookListResponse(bookResponses));
    }

    /**
     * Method use to get the book response
     * @param book
     * @return BookResponse
     * */
    private BookResponse getBookResponse(Book book) {
        BookResponse bookResponse = this.bookConverter.convertToBook(book);
        if (!ReduxUtil.isNull(book.getAuthor())) {
            bookResponse.setAuthor(this.authorConverter.convertToAuthor(book.getAuthor()));
        }
        return bookResponse;
    }

}
