package site.aoba.hotelmanagement.infrastructure.models;

import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * stock_out
 *
 * @author
 */
@Data
public class StockOutModel implements IEntityModel<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 出库编号
     */
    private Long stockOutId;
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

    @Override
    public Long getId() {
        return this.stockOutId;
    }

    @Override
    public void setId(Long aLong) {
        this.stockOutId = aLong;
    }
}