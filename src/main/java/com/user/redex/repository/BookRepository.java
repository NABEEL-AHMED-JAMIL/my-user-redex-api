package com.user.redex.repository;

import com.user.redex.model.Book;
import org.springframework.stereotype.Repository;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface BookRepository extends EntityRepository<Book> {
}
