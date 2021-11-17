package site.aoba.hotelmanagement.infrastructure.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

/**
 * deposit
 * @author 
 */
@Data
public class DepositModel implements IEntityModel<Long> {
    /**
     * 订金编号
     */
    private Long depositId;

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

    private static final long serialVersionUID = 1L;

    @Override
    public Long getId() {
        return depositId;
    }

    @Override
    public void setId(Long aLong) {
        this.depositId = aLong;
    }
}