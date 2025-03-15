package com.user.redex.business.converter;

import com.user.redex.business.document.Book;
import com.user.redex.business.dto.request.BookRequest;
import com.user.redex.business.dto.response.BookResponse;
import com.user.redex.business.dto.response.GEnum;
import com.user.redex.util.ReduxUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Nabeel Ahmed
 */
@Component
public class BookConverter {

    private Logger logger = LogManager.getLogger(BookConverter.class);

    /**
     * Method use to convert the book request to book
     * @param bookRequest
     * @param book
     * @return Author
     * */
    public Book convertToBook(BookRequest bookRequest, Book book) {
        logger.info("convertToBook => bookRequest to book.");
        if (!ReduxUtil.isNull(bookRequest.getIsbn())) {
            book.setIsbn(bookRequest.getIsbn());
        }
        if (!ReduxUtil.isNull(bookRequest.getTitle())) {
            book.setTitle(bookRequest.getTitle());
        }
        if (!ReduxUtil.isNull(bookRequest.getPrice())) {
            book.setPrice(bookRequest.getPrice());
        }
        if (!ReduxUtil.isNull(bookRequest.getPublisher())) {
            book.setPublisher(bookRequest.getPublisher());
        }
        if (!ReduxUtil.isNull(bookRequest.getPublication())) {
            book.setPublication(bookRequest.getPublication());
        }
        if (!ReduxUtil.isNull(bookRequest.getLanguage())) {
            book.setLanguage(bookRequest.getLanguage());
        }
        if (!ReduxUtil.isNull(bookRequest.getCategory())) {
            book.setCategory(bookRequest.getCategory());
        }
        if (!ReduxUtil.isNull(bookRequest.getFormat())) {
            book.setFormat(bookRequest.getFormat());
        }
        if (!ReduxUtil.isNull(bookRequest.getDescription())) {
            book.setDescription(bookRequest.getDescription());
        }
        if (!ReduxUtil.isNull(bookRequest.getNote())) {
            book.setNote(bookRequest.getNote());
        }
        return book;
    }

    /**
     * Method use to convert the book to book response
     * @param book
     * @return BookResponse
     * */
    public BookResponse convertToBook(Book book) {
        logger.info("convertToBook => book to bookResponse.");
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setIsbn(book.getIsbn());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setPublisher(book.getPublisher());
        bookResponse.setPublication(book.getPublication());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setCoverImg(book.getCoverImg());
        bookResponse.setBookUrl(book.getBookUrl());
        bookResponse.setNote(book.getNote());
        bookResponse.setCreatedAt(book.getCreatedAt());
        bookResponse.setUpdatedAt(book.getUpdatedAt());
        if (!ReduxUtil.isNull(book.getLanguage())) {
            GEnum language = new GEnum(book.getLanguage().getCode(),
                book.getLanguage().getName(), book.getLanguage());
            bookResponse.setLanguage(language);
        }
        if (!ReduxUtil.isNull(book.getCategory())) {
            GEnum category = new GEnum(book.getCategory().getCode(),
                book.getCategory().getName(), book.getCategory());
            bookResponse.setCategory(category);
        }
        if (!ReduxUtil.isNull(book.getFormat())) {
            GEnum format = new GEnum(book.getFormat().getCode(),
                book.getFormat().getName(), book.getFormat());
            bookResponse.setFormat(format);
        }
        if (!ReduxUtil.isNull(book.getStatus())) {
            GEnum status = new GEnum(book.getStatus().getCode(),
                book.getStatus().getName(), book.getStatus());
            bookResponse.setStatus(status);
        }
        return bookResponse;
    }

}
