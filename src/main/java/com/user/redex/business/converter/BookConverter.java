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
        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());
        book.setPrice(bookRequest.getPrice());
        book.setPublisher(bookRequest.getPublisher());
        book.setPublication(bookRequest.getPublication());
        book.setLanguage(bookRequest.getLanguage());
        book.setCategory(bookRequest.getCategory());
        book.setFormat(bookRequest.getFormat());
        book.setDescription(bookRequest.getDescription());
        book.setCoverImg(bookRequest.getCoverImg());
        book.setBookUrl(bookRequest.getBookUrl());
        book.setNote(bookRequest.getNote());
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
