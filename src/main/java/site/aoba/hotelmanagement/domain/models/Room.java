package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.math.BigDecimal;

/**
 * room
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room implements IEntity<Integer> {
    private static final long serialVersionUID = 1L;
    /**
     * 房间编号
     */
    private Integer Id;
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

}