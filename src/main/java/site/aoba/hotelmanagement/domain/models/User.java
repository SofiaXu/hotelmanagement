package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

/**
 * user
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements IEntity<Integer> {
    private static final long serialVersionUID = 1L;
    /**
     * 用户编号
     */
    private Integer Id;
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

}