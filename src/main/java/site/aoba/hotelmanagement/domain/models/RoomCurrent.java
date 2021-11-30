package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.util.Date;

/**
 * room_current
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCurrent implements IEntity<Long> {

    /**
     * 房间入住编号
     */
    private Long Id;
    /**
     * 房间入住起始时间
     */
    private Date roomCurrentStartTime;
    /**
     * 房间入住结束时间
     */
    private Date roomCurrentEndTime;
    /**
     * 订房编号
     */
    private Long roomCurrentBookId;
    /**
     * 房间入住客户编号
     */
    private Integer roomCurrentCustomerId;
    /**
     * 房间入住操作时间
     */
    private Date roomCurrentCreatedTime;
    /**
     * 房间入住操作人
     */
    private Integer roomCurrentUserId;

}