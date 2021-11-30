package site.aoba.hotelmanagement.infrastructure.models;

import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

/**
 * user
 *
 * @author
 */
@Data
public class UserModel implements IEntityModel<Integer> {

    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户真实姓名
     */
    private String userRealName;
    /**
     * 用户类型编号
     */
    private Integer userTypeId;
    /**
     * 用户年龄
     */
    private Integer userAge;
    /**
     * 用户性别
     */
    private Boolean userGender;
    /**
     * 用户密码哈希值
     */
    private String userPasswordHash;

    @Override
    public Integer getId() {
        return this.userId;
    }

    @Override
    public void setId(Integer integer) {
        this.userId = integer;
    }
}