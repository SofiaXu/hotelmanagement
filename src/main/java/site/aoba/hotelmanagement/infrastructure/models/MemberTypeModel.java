package site.aoba.hotelmanagement.infrastructure.models;

import java.io.Serializable;
import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

/**
 * member_type
 * @author 
 */
@Data
public class MemberTypeModel implements IEntityModel<Integer> {
    /**
     * 会员类型编号
     */
    private Integer memberTypeId;

    /**
     * 会员类型名称
     */
    private String memberTypeName;

    /**
     * 会员类型折扣
     */
    private Double memberTypeDiscount;

    private static final long serialVersionUID = 1L;

    @Override
    public Integer getId() {
        return memberTypeId;
    }

    @Override
    public void setId(Integer integer) {
        this.memberTypeId = integer;
    }
}