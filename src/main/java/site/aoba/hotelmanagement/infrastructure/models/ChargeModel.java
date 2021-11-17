package site.aoba.hotelmanagement.infrastructure.models;

import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

import java.math.BigDecimal;

/**
 * charge
 *
 * @author
 */
@Data
public class ChargeModel implements IEntityModel<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 费用编号
     */
    private Long chargeId;
    /**
     * 费用房间入住编号
     */
    private Long chargeRoomCurrentId;
    /**
     * 费用客户编号
     */
    private Integer chargeCustomerId;
    /**
     * 费用金额
     */
    private BigDecimal chargePrice;
    /**
     * 费用摘要
     */
    private String chargeSummary;
    /**
     * 费用备注
     */
    private String chargeComment;

    @Override
    public Long getId() {
        return chargeId;
    }

    @Override
    public void setId(Long aLong) {
        this.chargeId = aLong;
    }
}