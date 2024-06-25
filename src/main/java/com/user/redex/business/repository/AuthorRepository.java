package com.user.redex.business.repository;

import com.user.redex.business.document.Author;
import com.user.redex.business.enums.Status;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface AuthorRepository extends EntityRepository<Author> {

    Optional<Author> findByUsernameAndStatus(String username, Status status);

    Optional<Author> findByIdAndStatusNot(String id, Status status);

    List<Author> findAllByStatusNot(Status status);

    Optional<Author> findByEmail(String email);

    Optional<Author> findByEmailAndStatusNot(String email, Status status);

    Optional<Author> findByUsername(String email);

}
