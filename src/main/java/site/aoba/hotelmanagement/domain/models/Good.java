package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.math.BigDecimal;

/**
 * good
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good implements IEntity<Long> {

    /**
     * 货物编号
     */
    private Long Id;
    /**
     * 货物名称
     */
    private String goodName;
    /**
     * 货物数量
     */
    private BigDecimal goodCount;

}