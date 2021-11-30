package site.aoba.hotelmanagement.infrastructure.mappers;

import org.apache.ibatis.annotations.Mapper;
import site.aoba.hotelmanagement.architecture.infrastructure.mapper.IEntityModelMapper;
import site.aoba.hotelmanagement.infrastructure.models.RoomTypeModel;

@Mapper
public interface RoomTypeMapper extends IEntityModelMapper<Integer, RoomTypeModel> {
}