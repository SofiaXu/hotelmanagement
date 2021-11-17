package site.aoba.hotelmanagement.infrastructure.models;

import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

/**
 * room_type
 *
 * @author
 */
@Data
public class RoomTypeModel implements IEntityModel<Integer> {
    private static final long serialVersionUID = 1L;
    /**
     * 房间类型编号
     */
    private Integer roomTypeId;
    /**
     * 房间类型名称
     */
    private String roomTypeName;

    @Override
    public Integer getId() {
        return this.roomTypeId;
    }

    @Override
    public void setId(Integer integer) {
        this.roomTypeId = integer;
    }
}