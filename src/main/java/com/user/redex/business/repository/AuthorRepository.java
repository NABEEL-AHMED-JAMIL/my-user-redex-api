package com.user.redex.business.repository;

import com.user.redex.business.document.Author;
import com.user.redex.business.enums.Status;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface AuthorRepository extends EntityRepository<Author> {

    public Optional<Author> findByUsernameAndStatus(String username, Status status);

    public Optional<Author> findByIdAndStatusNot(String id, Status status);

    public List<Author> findAllByStatusNot(Status status);

    public Optional<Author> findByEmail(String email);

    public Optional<Author> findByEmailAndStatusNot(String email, Status status);

    public Optional<Author> findByUsername(String email);

    @Query("{'$text': {'$search': ?0}}")
    public List<Author> searchByText(String text);

}
