package site.aoba.hotelmanagement.architecture.domain.repository;

import org.springframework.stereotype.Repository;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.util.List;
import java.util.function.Predicate;

@Repository
public interface IRepository<TId, TEntity extends IEntity<TId>> {
    List<TEntity> getEntities(int pageSize, int pageIndex);

    List<TEntity> getEntities(int pageSize, int pageIndex, boolean refresh);

    TEntity getEntityById(TId id);

    TEntity getEntityById(TId id, boolean refresh);

    void updateEntity(TEntity entity);

    void createEntity(TEntity entity);

    void deleteEntity(TId id);

    List<TEntity> searchEntities(Predicate<TEntity> entityPredicate, int pageSize, int pageIndex);

    List<TEntity> searchEntities(Predicate<TEntity> entityPredicate, int pageSize, int pageIndex, boolean refresh);

    void clearItemCache(TId id);

    void clearItemsCache();
}
