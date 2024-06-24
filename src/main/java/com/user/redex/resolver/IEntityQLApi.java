package com.user.redex.resolver;

import com.user.redex.business.dto.response.GQLResponse;
import java.util.List;

/**
 * @author Nabeel Ahmed
 * R for Reqeust
 * T for Response
 * in graph QL as we know we have single endpont
 * we need to create the unique method so this will not work
 */
public interface IEntityQLApi<R, T> {

    /**
     * Create  entity
     * @param entity
     * @return T
     */
    GQLResponse<T> createEntity(R entity);

    /**
     * Update entity
     * @param entity
     * @return T
     */
    GQLResponse<T> updateEntity(R entity);

    /**
     * Delete entity
     * @param id
     * @return BasicEntity
     */
    GQLResponse<T> deleteEntity(String id);


    /**
     * Display entity
     * @param id
     * @return T
     */
    GQLResponse<T> getEntity(String id);


    /**
     * Return List of Entities of Type T.
     * @return List of type T.
     */
    GQLResponse<List<T>> getAllEntities();

}