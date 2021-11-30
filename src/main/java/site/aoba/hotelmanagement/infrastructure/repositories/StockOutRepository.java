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
import site.aoba.hotelmanagement.domain.models.StockOut;
import site.aoba.hotelmanagement.domain.repository.IStockOutRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.StockOutMapper;
import site.aoba.hotelmanagement.infrastructure.models.StockOutModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockOutRepository implements IStockOutRepository {
    private final StockOutMapper mapper;

    @Override
    public List<StockOut> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<StockOut> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public StockOut getEntityById(Long id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public StockOut getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(StockOut entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(StockOut entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<StockOut> searchEntities(Predicate<StockOut> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<StockOut> searchEntities(Predicate<StockOut> entityPredicate, int pageSize, int pageIndex,
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

    private StockOutModel toModel(StockOut entity) {
        val x = new StockOutModel();
        x.setId(entity.getId());
        x.setStockOutGoodId(entity.getStockOutGoodId());
        x.setStockOutTime(entity.getStockOutTime());
        x.setStockOutUserId(entity.getStockOutUserId());
        x.setStockOutStockInId(entity.getStockOutStockInId());
        x.setStockOutDestination(entity.getStockOutDestination());
        x.setStockOutCount(entity.getStockOutCount());

        return x;
    }

    private StockOut toEntity(StockOutModel x) {
        return new StockOut(
                x.getId(),
                x.getStockOutGoodId(),
                x.getStockOutTime(),
                x.getStockOutUserId(),
                x.getStockOutStockInId(),
                x.getStockOutDestination(),
                x.getStockOutCount()

        );
    }
}
