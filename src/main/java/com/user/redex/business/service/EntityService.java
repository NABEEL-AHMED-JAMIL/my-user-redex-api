package com.user.redex.business.service;

import com.user.redex.business.dto.response.GQLResponse;

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
    GQLResponse<T> createEntity(R entity) throws Exception;

    /**
     * Update entity
     * @param entity
     * @return T
     */
    GQLResponse<T> updateEntity(R entity) throws Exception;

    /**
     * Delete entity
     * @param id
     * @return BasicEntity
     */
    GQLResponse<T> deleteEntity(String id) throws Exception;


    /**
     * Display entity
     * @param id
     * @return T
     */
    GQLResponse<T> getEntity(String id) throws Exception;


    /**
     * Return List of Entities of Type T.
     * @return List of type T.
     */
    GQLResponse<?> getAllEntities() throws Exception;

}