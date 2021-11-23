package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

import java.util.Date;

/**
 * book
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements IEntity<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 订房编号
     */
    private Long Id;
    /**
     * 订房顾客编号
     */
    private Integer bookCustomerId;
    /**
     * 订房操作用户编号
     */
    private Integer bookUserId;
    /**
     * 订房时间
     */
    private Date bookCreatedTime;
    /**
     * 订房起始时间
     */
    private Date bookStartTime;
    /**
     * 订房结束时间
     */
    private Date bookEndTime;
    /**
     * 订房入住
     */
    private Boolean bookIsCheckedIn;
}