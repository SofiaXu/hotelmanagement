package site.aoba.hotelmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.hotelmanagement.architecture.domain.model.IEntity;

/**
 * room_type
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomType implements IEntity<Integer> {
    private static final long serialVersionUID = 1L;
    /**
     * 房间类型编号
     */
    private Integer Id;
    /**
     * 房间类型名称
     */
    private String roomTypeName;

}