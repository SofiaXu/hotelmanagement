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
import site.aoba.hotelmanagement.domain.models.RoomCurrent;
import site.aoba.hotelmanagement.domain.repository.IRoomCurrentRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.RoomCurrentMapper;
import site.aoba.hotelmanagement.infrastructure.models.RoomCurrentModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomCurrentRepository implements IRoomCurrentRepository {
    private final RoomCurrentMapper mapper;

    @Override
    public List<RoomCurrent> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomCurrent> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public RoomCurrent getEntityById(Long id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public RoomCurrent getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(RoomCurrent entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(RoomCurrent entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<RoomCurrent> searchEntities(Predicate<RoomCurrent> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<RoomCurrent> searchEntities(Predicate<RoomCurrent> entityPredicate, int pageSize, int pageIndex,
            boolean refresh) {
        if (refresh)
            clearItemsCache();
        return searchEntities(entityPredicate, pageSize, pageIndex);
    }

    @Override
    @CacheEvict(key = "#id")
    public void clearItemCache(Long id) {
    }

    @Override
    @CacheEvict(allEntries = true)
    public void clearItemsCache() {
    }

    private RoomCurrentModel toModel(RoomCurrent entity) {
        val x = new RoomCurrentModel();
        x.setId(entity.getId());
        x.setRoomCurrentStartTime(entity.getRoomCurrentStartTime());
        x.setRoomCurrentEndTime(entity.getRoomCurrentEndTime());
        x.setRoomCurrentBookId(entity.getRoomCurrentBookId());
        x.setRoomCurrentCustomerId(entity.getRoomCurrentCustomerId());
        x.setRoomCurrentCreatedTime(entity.getRoomCurrentCreatedTime());
        x.setRoomCurrentUserId(entity.getRoomCurrentUserId());

        return x;
    }

    private RoomCurrent toEntity(RoomCurrentModel x) {
        return new RoomCurrent(
                x.getId(),
                x.getRoomCurrentStartTime(),
                x.getRoomCurrentEndTime(),
                x.getRoomCurrentBookId(),
                x.getRoomCurrentCustomerId(),
                x.getRoomCurrentCreatedTime(),
                x.getRoomCurrentUserId()

        );
    }
}
