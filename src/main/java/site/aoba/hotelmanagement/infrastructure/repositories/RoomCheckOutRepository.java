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
import site.aoba.hotelmanagement.domain.models.RoomCheckOut;
import site.aoba.hotelmanagement.domain.repository.IRoomCheckOutRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.RoomCheckOutMapper;
import site.aoba.hotelmanagement.infrastructure.models.RoomCheckOutModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomCheckOutRepository implements IRoomCheckOutRepository {
    private final RoomCheckOutMapper mapper;

    @Override
    public List<RoomCheckOut> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomCheckOut> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public RoomCheckOut getEntityById(Long id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public RoomCheckOut getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(RoomCheckOut entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(RoomCheckOut entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<RoomCheckOut> searchEntities(Predicate<RoomCheckOut> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<RoomCheckOut> searchEntities(Predicate<RoomCheckOut> entityPredicate, int pageSize, int pageIndex,
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

    private RoomCheckOutModel toModel(RoomCheckOut entity) {
        val x = new RoomCheckOutModel();
        x.setId(entity.getId());
        x.setRoomCheckOutTime(entity.getRoomCheckOutTime());
        x.setRoomCheckOutRemainPrice(entity.getRoomCheckOutRemainPrice());
        x.setRoomCheckOutUserId(entity.getRoomCheckOutUserId());
        x.setRoomCheckOutCreatedTime(entity.getRoomCheckOutCreatedTime());
        x.setRoomCheckOutRoomCurrentId(entity.getRoomCheckOutRoomCurrentId());

        return x;
    }

    private RoomCheckOut toEntity(RoomCheckOutModel x) {
        return new RoomCheckOut(
                x.getId(),
                x.getRoomCheckOutTime(),
                x.getRoomCheckOutRemainPrice(),
                x.getRoomCheckOutUserId(),
                x.getRoomCheckOutCreatedTime(),
                x.getRoomCheckOutRoomCurrentId()

        );
    }
}
