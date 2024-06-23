package com.user.redex.business.service;

import com.user.redex.business.dto.response.QLResponse;

/**
 * @author Nabeel Ahmed
 * R for Reqeust
 * T for Response
 */
public interface EntityService<R, T> {

    /**
     * Create  entity
     * @param entity
     * @return T
     */
    QLResponse<T> createEntity(R entity) throws Exception;

    /**
     * Update entity
     * @param entity
     * @return T
     */
    QLResponse<T> updateEntity(R entity) throws Exception;

    /**
     * Delete entity
     * @param id
     * @return BasicEntity
     */
    QLResponse<T> deleteEntity(String id) throws Exception;


    /**
     * Display entity
     * @param id
     * @return T
     */
    QLResponse<T> getEntity(String id) throws Exception;


    /**
     * Return List of Entities of Type T.
     * @return List of type T.
     */
    QLResponse<?> getAllEntities() throws Exception;

}