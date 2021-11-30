package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * stock_out
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockOut implements IEntity<Long> {

    /**
     * 出库编号
     */
    private Long Id;
    /**
     * 出库货物编号
     */
    private Long stockOutGoodId;
    /**
     * 出库时间
     */
    private Date stockOutTime;
    /**
     * 出库人
     */
    private Integer stockOutUserId;
    /**
     * 入库编号
     */
    private Long stockOutStockInId;
    /**
     * 出库目的地
     */
    private String stockOutDestination;
    /**
     * 出库数量
     */
    private BigDecimal stockOutCount;

}