package com.user.redex.repository;

import com.user.redex.model.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface EntityRepository<T extends BaseEntity> extends MongoRepository<T, String> {

}