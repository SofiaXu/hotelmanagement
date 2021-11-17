package site.aoba.hotelmanagement.infrastructure.models;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

/**
 * good
 * @author 
 */
@Data
public class GoodModel implements IEntityModel<Long> {
    /**
     * 货物编号
     */
    private Long goodId;

    /**
     * 货物名称
     */
    private String goodName;

    /**
     * 货物数量
     */
    private BigDecimal goodCount;

    private static final long serialVersionUID = 1L;

    @Override
    public Long getId() {
        return goodId;
    }

    @Override
    public void setId(Long aLong) {
        this.goodId = aLong;
    }
}