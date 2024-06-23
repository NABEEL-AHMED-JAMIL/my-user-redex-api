package com.user.redex.business.repository;

import com.user.redex.business.document.Author;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface AuthorRepository extends EntityRepository<Author> {

    Optional<Author> findByEmail(String email);

    Optional<Author> findByUsername(String email);

}
