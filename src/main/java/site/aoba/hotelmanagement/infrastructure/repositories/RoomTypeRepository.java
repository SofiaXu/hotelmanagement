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
import site.aoba.hotelmanagement.domain.models.RoomType;
import site.aoba.hotelmanagement.domain.repository.IRoomTypeRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.RoomTypeMapper;
import site.aoba.hotelmanagement.infrastructure.models.RoomTypeModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomTypeRepository implements IRoomTypeRepository {
    private final RoomTypeMapper mapper;

    @Override
    public List<RoomType> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomType> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public RoomType getEntityById(Integer id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public RoomType getEntityById(Integer id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(RoomType entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(RoomType entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Integer id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<RoomType> searchEntities(Predicate<RoomType> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<RoomType> searchEntities(Predicate<RoomType> entityPredicate, int pageSize, int pageIndex,
            boolean refresh) {
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

    private RoomTypeModel toModel(RoomType entity) {
        val x = new RoomTypeModel();
        x.setId(entity.getId());
        x.setRoomTypeName(entity.getRoomTypeName());

        return x;
    }

    private RoomType toEntity(RoomTypeModel x) {
        return new RoomType(
                x.getId(),
                x.getRoomTypeName()

        );
    }
}
