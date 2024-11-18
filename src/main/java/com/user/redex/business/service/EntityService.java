package com.user.redex.business.service;

import com.user.redex.business.dto.response.GQLResponse;

/**
 * @author Nabeel Ahmed
 * R for Reqeust
 * T for Response
 */
public interface EntityService<R> {

    /**
     * Create  entity
     * @param payload
     * @return T
     */
    GQLResponse<?> createEntity(R payload) throws Exception;

    /**
     * Update entity
     * @param payload
     * @return T
     */
    GQLResponse<?> updateEntity(R payload) throws Exception;

    /**
     * Delete entity
     * @param id
     * @return BasicEntity
     */
    GQLResponse<?> deleteEntity(String id) throws Exception;


    /**
     * Display entity
     * @param id
     * @return T
     */
    GQLResponse<?> getEntity(String id) throws Exception;


    /**
     * Return List of Entities of Type T.
     * @return List of type T.
     */
    GQLResponse<?> getAllEntities() throws Exception;

}