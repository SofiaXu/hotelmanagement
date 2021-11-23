package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * room_price_history
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomPriceHistory implements IEntity<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 房间价格历史编号
     */
    private Long Id;
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

}