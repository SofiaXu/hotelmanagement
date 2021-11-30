package site.aoba.hotelmanagement.infrastructure.repositories;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import lombok.RequiredArgsConstructor;
import lombok.val;
import site.aoba.hotelmanagement.domain.models.Room;
import site.aoba.hotelmanagement.domain.repository.IRoomRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.RoomMapper;
import site.aoba.hotelmanagement.infrastructure.models.RoomModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomRepository implements IRoomRepository {
    private final RoomMapper mapper;

    @Override
    public List<Room> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Room getEntityById(Integer id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public Room getEntityById(Integer id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(Room entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(Room entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Integer id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<Room> searchEntities(Predicate<Room> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<Room> searchEntities(Predicate<Room> entityPredicate, int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return searchEntities(entityPredicate, pageSize, pageIndex);
    }

    @Override
    @CacheEvict(key = "#id")
    public void clearItemCache(Integer id) {
    }

    @Override
    @CacheEvict(allEntries = true)
    public void clearItemsCache() {
    }

    private RoomModel toModel(Room entity) {
        val x = new RoomModel();
        x.setId(entity.getId());
        x.setRoomLevel(entity.getRoomLevel());
        x.setRoomNumber(entity.getRoomNumber());
        x.setRoomPrice(entity.getRoomPrice());
        x.setRoomTypeId(entity.getRoomTypeId());

        return x;
    }

    private Room toEntity(RoomModel x) {
        return new Room(
                x.getId(),
                x.getRoomLevel(),
                x.getRoomNumber(),
                x.getRoomPrice(),
                x.getRoomTypeId()

        );
    }
}
