package site.aoba.hotelmanagement.architecture.infrastructure.mapper;

import org.apache.ibatis.annotations.Param;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

import java.util.List;

public interface IEntityMapper<TId, TEntityModel extends IEntityModel<TId>> extends IMapper<TEntityModel> {
    int deleteByPrimaryKey(TId id);
    int insert(TEntityModel record);
    TEntityModel selectByPrimaryKey(TId id);
    List<TEntityModel> selectAll();
    int updateByPrimaryKey(TEntityModel record);
}
