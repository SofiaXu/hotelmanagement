package site.aoba.hotelmanagement.architecture.infrastructure.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IModel;

@Mapper
public interface IModelMapper<TModel extends IModel> {

}
