package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * stock_in
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockIn implements IEntity<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 入库编号
     */
    private Long Id;
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

}