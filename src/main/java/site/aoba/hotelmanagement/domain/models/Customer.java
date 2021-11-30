package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.util.Date;

/**
 * customer
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements IEntity<Integer> {

    /**
     * 客户编号
     */
    private Integer Id;
    /**
     * 客户身份证号
     */
    private String customerIdNumber;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 客户生日
     */
    private Date customerBirthDate;
    /**
     * 客户性别
     */
    private Boolean customerGender;
    /**
     * 客户手机
     */
    private String customerPhone;
    /**
     * 客户邮箱
     */
    private String customerEmail;
    /**
     * 客户会员类型
     */
    private Integer customerMemberTypeId;

}