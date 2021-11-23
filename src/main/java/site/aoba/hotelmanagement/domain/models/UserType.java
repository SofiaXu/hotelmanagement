package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

/**
 * user_type
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserType implements IEntity<Integer> {
    private static final long serialVersionUID = 1L;
    /**
     * 用户类型编号
     */
    private Integer Id;
    /**
     * 用户类型名称
     */
    private String userTypeName;

}