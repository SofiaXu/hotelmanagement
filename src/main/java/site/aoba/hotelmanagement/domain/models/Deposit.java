package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * deposit
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposit implements IEntity<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 订金编号
     */
    private Long Id;
    /**
     * 订金交款客户编号
     */
    private Integer depositCustomerId;
    /**
     * 订金预定编号
     */
    private Long depositBookId;
    /**
     * 订金价格
     */
    private BigDecimal depositPrice;
    /**
     * 订金创建时间
     */
    private Date depositCreatedTime;
    /**
     * 订金操作员编号
     */
    private Integer depositUserId;

}