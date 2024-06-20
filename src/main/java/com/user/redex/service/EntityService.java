package com.user.redex.service;

import com.user.redex.dto.BaseEntity;
import java.util.List;

/**
 * @author Nabeel Ahmed
 */
public interface EntityService<T extends BaseEntity> {

    /**
     * Create  entity
     * @param entity
     * @return T
     */
    T createEntity(T entity);

    /**
     * Update entity
     * @param entity
     * @param id
     * @return T
     */
    T updateEntity(Long id, T entity);

    /**
     * Delete entity
     * @param id
     * @return BasicEntity
     */
    void deleteEntity(Long id);


    /**
     * Display entity
     * @param id
     * @return T
     */
    T getEntity(Long id);


    /**
     * Return List of Entities of Type T.
     * @return List of type T.
     */
    List<T> getAllEntities();

}