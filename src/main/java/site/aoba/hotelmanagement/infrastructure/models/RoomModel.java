package site.aoba.hotelmanagement.infrastructure.models;

import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

import java.math.BigDecimal;

/**
 * room
 *
 * @author
 */
@Data
public class RoomModel implements IEntityModel<Integer> {

    /**
     * 房间编号
     */
    private Integer roomId;
    /**
     * 房间楼层号
     */
    private Integer roomLevel;
    /**
     * 房间号
     */
    private Integer roomNumber;
    /**
     * 房间价格
     */
    private BigDecimal roomPrice;
    /**
     * 房间类型编号
     */
    private Integer roomTypeId;

    @Override
    public Integer getId() {
        return roomId;
    }

    @Override
    public void setId(Integer integer) {
        this.roomId = integer;
    }
}