package site.aoba.hotelmanagement.infrastructure.models;

import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * room_price_history
 *
 * @author
 */
@Data
public class RoomPriceHistoryModel implements IEntityModel<Long> {

    /**
     * 房间价格历史编号
     */
    private Long roomPriceHistoryId;
    /**
     * 房间编号
     */
    private Integer roomId;
    /**
     * 房间修改前价格
     */
    private BigDecimal roomPreviousPrice;
    /**
     * 房间修改后价格
     */
    private BigDecimal roomNowPrice;
    /**
     * 房间价格变更时间
     */
    private Date roomPriceChangedTime;
    /**
     * 房间变更操作人编号
     */
    private Integer roomPriceChangedUserId;

    @Override
    public Long getId() {
        return this.roomPriceHistoryId;
    }

    @Override
    public void setId(Long aLong) {
        this.roomPriceHistoryId = aLong;
    }
}