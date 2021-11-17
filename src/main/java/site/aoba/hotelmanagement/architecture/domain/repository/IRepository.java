package site.aoba.hotelmanagement.architecture.domain.repository;

import site.aoba.hotelmanagement.architecture.domain.model.IAggregateRoot;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.util.List;

public interface IRepository<TEntity extends IEntity, TId> {
    List<TEntity> getEntities(long pageSize, long pageIndex);
    List<TEntity> getEntities(long pageSize, long pageIndex, boolean refresh);
    TEntity getEntityById(TId id);
    TEntity getEntityById(TId id, boolean refresh);
    void updateEntity(TEntity entity);
    void createEntity(TEntity entity);
    void deleteEntity(TId id);
    List<TEntity> searchEntities(long pageSize, long pageIndex);
    List<TEntity> searchEntities(long pageSize, long pageIndex, boolean refresh);
    void clearItemCache(TId id);
    void clearItemsCache();
}