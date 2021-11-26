package site.aoba.hotelmanagement.infrastructure.mappers;

import org.apache.ibatis.annotations.Mapper;
import site.aoba.hotelmanagement.architecture.infrastructure.mapper.IEntityModelMapper;
import site.aoba.hotelmanagement.infrastructure.models.DepositModel;

@Mapper
public interface DepositMapper extends IEntityModelMapper<Long, DepositModel> {
}