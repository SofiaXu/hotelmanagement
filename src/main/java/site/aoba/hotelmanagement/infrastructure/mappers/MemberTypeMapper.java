package site.aoba.hotelmanagement.infrastructure.mappers;

import org.apache.ibatis.annotations.Mapper;
import site.aoba.hotelmanagement.architecture.infrastructure.mapper.IEntityModelMapper;
import site.aoba.hotelmanagement.infrastructure.models.MemberTypeModel;

@Mapper
public interface MemberTypeMapper extends IEntityModelMapper<Integer, MemberTypeModel> {
}