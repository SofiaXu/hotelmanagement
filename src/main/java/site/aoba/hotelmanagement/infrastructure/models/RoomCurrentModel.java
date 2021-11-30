package site.aoba.hotelmanagement.infrastructure.models;

import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

import java.util.Date;

/**
 * room_current
 *
 * @author
 */
@Data
public class RoomCurrentModel implements IEntityModel<Long> {

    /**
     * 房间入住编号
     */
    private Long roomCurrentId;
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

    @Override
    public Long getId() {
        return this.roomCurrentId;
    }

    @Override
    public void setId(Long aLong) {
        this.roomCurrentId = aLong;
    }
}