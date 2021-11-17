package site.aoba.hotelmanagement.infrastructure.models;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

/**
 * customer
 * @author 
 */
@Data
public class CustomerModel implements IEntityModel<Integer> {
    /**
     * 客户编号
     */
    private Integer customerId;

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

    private static final long serialVersionUID = 1L;

    @Override
    public Integer getId() {
        return customerId;
    }

    @Override
    public void setId(Integer integer) {
        this.customerId = integer;
    }
}