package com.user.redex.business.service.impl;

import com.user.redex.business.converter.AuthorConverter;
import com.user.redex.business.converter.BookConverter;
import com.user.redex.business.document.Author;
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
import java.util.Optional;
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
        Optional<Author> author = this.authorRepository.findByUsernameAndStatus(userDetails.getUsername(), Status.ACTIVE);
        if (!author.isPresent()) {
            return new GQLResponse("Author not found.", ReduxUtil.ERROR);
        }
        if (ReduxUtil.isNull(payload.getIsbn())) {
            return new GQLResponse("Book isbn required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getTitle())) {
            return new GQLResponse("Book title required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getPrice())) {
            return new GQLResponse("Book price required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getPublisher())) {
            return new GQLResponse("Book publisher required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getLanguage())) {
            return new GQLResponse("Book language required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getCategory())) {
            return new GQLResponse("Book category required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getFormat())) {
            return new GQLResponse("Book format required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getDescription())) {
            return new GQLResponse("Book description required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getNote())) {
            return new GQLResponse("Book note required.", ReduxUtil.ERROR);
        }
        // db check isbn exist or not
        if (this.bookRepository.findByIsbn(payload.getIsbn()).isPresent()) {
            return new GQLResponse("Book already exist with isbn.", ReduxUtil.ERROR);
        }
        // book save but not show to the public user until the cover image and book upload using reset api file upload
        Book book = this.bookConverter.convertToBook(payload, new Book());
        book.setStatus(Status.ACTIVE);
        book.setAuthor(author.get());
        this.bookRepository.save(book);
        BookResponse bookResponse = this.bookConverter.convertToBook(book);
        return new GQLResponse("Book detail save successfully.", ReduxUtil.SUCCESS, bookResponse);
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
        Optional<Book> book = this.bookRepository.findByIsbn(payload.getIsbn());
        if (!book.isPresent()) {
            return new GQLResponse("Book not found with isbn.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getTitle())) {
            return new GQLResponse("Book title required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getPrice())) {
            return new GQLResponse("Book price required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getPublisher())) {
            return new GQLResponse("Book publisher required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getLanguage())) {
            return new GQLResponse("Book language required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getCategory())) {
            return new GQLResponse("Book category required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getFormat())) {
            return new GQLResponse("Book format required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getDescription())) {
            return new GQLResponse("Book description required.", ReduxUtil.ERROR);
        } else if (ReduxUtil.isNull(payload.getNote())) {
            return new GQLResponse("Book note required.", ReduxUtil.ERROR);
        }
        // book save but not show to the public user until the cover image and book upload using reset api file upload
        this.bookConverter.convertToBook(payload, book.get());
        if (!ReduxUtil.isNull(payload.getStatus())) {
            book.get().setStatus(payload.getStatus());
        }
        this.bookRepository.save(book.get());
        BookResponse bookResponse = this.bookConverter.convertToBook(book.get());
        return new GQLResponse("Book detail update successfully.", ReduxUtil.SUCCESS, bookResponse);
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
        Optional<Book> book = this.bookRepository.findByIdAndStatusNot(id, Status.DELETE);
        if (!book.isPresent()) {
            return new GQLResponse("Book not found.", ReduxUtil.ERROR);
        }
        book.get().setStatus(Status.DELETE);
        this.bookRepository.save(book.get());
        return new GQLResponse("Book delete successfully.", ReduxUtil.SUCCESS);
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
        Optional<Book> book = this.bookRepository.findByIdAndStatusNot(id, Status.DELETE);
        if (!book.isPresent()) {
            return new GQLResponse("Book not found.", ReduxUtil.ERROR);
        }
        return new GQLResponse("Book fetch successfully.", ReduxUtil.SUCCESS, this.getBookResponse(book.get()));
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
