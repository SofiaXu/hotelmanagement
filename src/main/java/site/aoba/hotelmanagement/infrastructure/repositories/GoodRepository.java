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
import site.aoba.hotelmanagement.domain.models.Good;
import site.aoba.hotelmanagement.domain.repository.IGoodRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.GoodMapper;
import site.aoba.hotelmanagement.infrastructure.models.GoodModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodRepository implements IGoodRepository {
    private final GoodMapper mapper;

    @Override
    public List<Good> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<Good> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Good getEntityById(Long id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public Good getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(Good entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(Good entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<Good> searchEntities(Predicate<Good> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<Good> searchEntities(Predicate<Good> entityPredicate, int pageSize, int pageIndex, boolean refresh) {
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

    private GoodModel toModel(Good entity) {
        val x = new GoodModel();
        x.setId(entity.getId());
        x.setGoodName(entity.getGoodName());
        x.setGoodCount(entity.getGoodCount());

        return x;
    }

    private Good toEntity(GoodModel x) {
        return new Good(
                x.getId(),
                x.getGoodName(),
                x.getGoodCount()

        );
    }
}
