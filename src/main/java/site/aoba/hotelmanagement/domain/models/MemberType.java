package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

/**
 * member_type
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberType implements IEntity<Integer> {
    private static final long serialVersionUID = 1L;
    /**
     * 会员类型编号
     */
    private Integer Id;
    /**
     * 会员类型名称
     */
    private String memberTypeName;
    /**
     * 会员类型折扣
     */
    private Double memberTypeDiscount;

}