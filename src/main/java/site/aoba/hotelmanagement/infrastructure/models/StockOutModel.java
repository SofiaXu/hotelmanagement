package site.aoba.hotelmanagement.infrastructure.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

/**
 * stock_out
 * @author 
 */
@Data
public class StockOutModel implements IEntityModel<Long> {
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

    private static final long serialVersionUID = 1L;

    @Override
    public Long getId() {
        return this.stockOutId;
    }

    @Override
    public void setId(Long aLong) {
        this.stockOutId = aLong;
    }
}