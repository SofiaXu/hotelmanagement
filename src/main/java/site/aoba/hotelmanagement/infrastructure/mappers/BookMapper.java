package site.aoba.hotelmanagement.infrastructure.mappers;

import org.apache.ibatis.annotations.Mapper;
import site.aoba.hotelmanagement.architecture.infrastructure.mapper.IEntityModelMapper;
import site.aoba.hotelmanagement.infrastructure.models.BookModel;

@Mapper
public interface BookMapper extends IEntityModelMapper<Long, BookModel> {
}