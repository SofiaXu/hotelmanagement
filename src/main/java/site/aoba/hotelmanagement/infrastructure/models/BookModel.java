package site.aoba.hotelmanagement.infrastructure.models;

import lombok.Data;
import site.aoba.hotelmanagement.architecture.infrastructure.model.IEntityModel;

import java.util.Date;

/**
 * book
 *
 * @author
 */
@Data
public class BookModel implements IEntityModel<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 订房编号
     */
    private Long bookId;
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

    @Override
    public Long getId() {
        return bookId;
    }

    @Override
    public void setId(Long o) {
        this.bookId = o;
    }
}