package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.math.BigDecimal;

/**
 * charge
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Charge implements IEntity<Long> {

    /**
     * 费用编号
     */
    private Long Id;
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

}