package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * room_check_out
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCheckOut implements IEntity<Long> {

    /**
     * 退房编号
     */
    private Long Id;
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

}