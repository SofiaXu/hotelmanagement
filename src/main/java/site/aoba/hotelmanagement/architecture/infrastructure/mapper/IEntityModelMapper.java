package site.aoba.hotelmanagement.architecture.infrastructure.mapper;

import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

import java.util.List;

public interface IEntityModelMapper<TId, TEntityModel extends IEntityModel<TId>> extends IModelMapper<TEntityModel> {
    int deleteByPrimaryKey(TId id);
    int insert(TEntityModel record);
    TEntityModel selectByPrimaryKey(TId id);
    List<TEntityModel> selectAll();
    int updateByPrimaryKey(TEntityModel record);
}
