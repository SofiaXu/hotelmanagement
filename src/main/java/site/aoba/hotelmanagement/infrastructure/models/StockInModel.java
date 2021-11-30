package site.aoba.hotelmanagement.infrastructure.models;

import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * stock_in
 *
 * @author
 */
@Data
public class StockInModel implements IEntityModel<Long> {

    /**
     * 入库编号
     */
    private Long stockInId;
    /**
     * 入库合同号
     */
    private Long stockInContractId;
    /**
     * 入库货物编号
     */
    private Long stockInGoodId;
    /**
     * 入库时间
     */
    private Date stockInTime;
    /**
     * 入库数量
     */
    private BigDecimal stockInCount;
    /**
     * 入库价格
     */
    private BigDecimal stockInPrice;
    /**
     * 入库发票号
     */
    private String stockInReceiptId;
    /**
     * 入库人
     */
    private Integer stockInUserId;

    @Override
    public Long getId() {
        return this.stockInId;
    }

    @Override
    public void setId(Long aLong) {
        this.stockInId = aLong;
    }
}