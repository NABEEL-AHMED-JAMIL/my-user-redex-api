package com.user.redex.business.repository;

import java.util.List;
import com.user.redex.business.document.Book;
import com.user.redex.business.enums.Status;
import org.springframework.stereotype.Repository;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface BookRepository extends EntityRepository<Book> {

    List<Book> findAllByStatusNotAndCoverImgNotNullAndBookUrlNotNull(Status status);
}
