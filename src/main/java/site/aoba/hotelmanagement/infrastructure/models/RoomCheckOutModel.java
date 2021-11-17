package site.aoba.hotelmanagement.infrastructure.models;

import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * room_check_out
 *
 * @author
 */
@Data
public class RoomCheckOutModel implements IEntityModel<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 退房编号
     */
    private Long roomCheckOutId;
    /**
     * 退房时间
     */
    private Date roomCheckOutTime;
    /**
     * 退房应付价格
     */
    private BigDecimal roomCheckOutRemainPrice;
    /**
     * 退房操作用户编号
     */
    private Integer roomCheckOutUserId;
    /**
     * 退房操作时间
     */
    private Date roomCheckOutCreatedTime;
    /**
     * 退房入住信息编号
     */
    private Long roomCheckOutRoomCurrentId;

    @Override
    public Long getId() {
        return this.roomCheckOutId;
    }

    @Override
    public void setId(Long aLong) {
        this.roomCheckOutId = aLong;
    }
}