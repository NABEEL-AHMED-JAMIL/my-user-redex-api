package com.user.redex.business.repository;

import java.util.List;
import java.util.Optional;
import com.user.redex.business.document.Book;
import com.user.redex.business.enums.Status;
import org.springframework.stereotype.Repository;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface BookRepository extends EntityRepository<Book> {

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByIdAndStatusNot(String id, Status status);

    List<Book> findAllByStatusNotAndCoverImgNotNullAndBookUrlNotNull(Status status);
}
