package site.aoba.hotelmanagement.infrastructure.models;

import java.io.Serializable;
import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

/**
 * user_type
 * @author 
 */
@Data
public class UserTypeModel implements IEntityModel<Integer> {
    /**
     * 用户类型编号
     */
    private Integer userTypeId;

    /**
     * 用户类型名称
     */
    private String userTypeName;

    private static final long serialVersionUID = 1L;

    @Override
    public Integer getId() {
        return this.userTypeId;
    }

    @Override
    public void setId(Integer integer) {
        this.userTypeId = integer;
    }
}