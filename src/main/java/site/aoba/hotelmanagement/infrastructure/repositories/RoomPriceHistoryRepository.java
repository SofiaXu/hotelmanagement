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
import site.aoba.hotelmanagement.domain.models.RoomPriceHistory;
import site.aoba.hotelmanagement.domain.repository.IRoomPriceHistoryRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.RoomPriceHistoryMapper;
import site.aoba.hotelmanagement.infrastructure.models.RoomPriceHistoryModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomPriceHistoryRepository implements IRoomPriceHistoryRepository {
    private final RoomPriceHistoryMapper mapper;

    @Override
    public List<RoomPriceHistory> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomPriceHistory> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public RoomPriceHistory getEntityById(Long id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public RoomPriceHistory getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(RoomPriceHistory entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(RoomPriceHistory entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<RoomPriceHistory> searchEntities(Predicate<RoomPriceHistory> entityPredicate, int pageSize,
            int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<RoomPriceHistory> searchEntities(Predicate<RoomPriceHistory> entityPredicate, int pageSize,
            int pageIndex, boolean refresh) {
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

    private RoomPriceHistoryModel toModel(RoomPriceHistory entity) {
        val x = new RoomPriceHistoryModel();
        x.setId(entity.getId());
        x.setRoomId(entity.getRoomId());
        x.setRoomPreviousPrice(entity.getRoomPreviousPrice());
        x.setRoomNowPrice(entity.getRoomNowPrice());
        x.setRoomPriceChangedTime(entity.getRoomPriceChangedTime());
        x.setRoomPriceChangedUserId(entity.getRoomPriceChangedUserId());

        return x;
    }

    private RoomPriceHistory toEntity(RoomPriceHistoryModel x) {
        return new RoomPriceHistory(
                x.getId(),
                x.getRoomId(),
                x.getRoomPreviousPrice(),
                x.getRoomNowPrice(),
                x.getRoomPriceChangedTime(),
                x.getRoomPriceChangedUserId()

        );
    }
}
