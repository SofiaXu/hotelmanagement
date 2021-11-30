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
import site.aoba.hotelmanagement.domain.models.StockIn;
import site.aoba.hotelmanagement.domain.repository.IStockInRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.StockInMapper;
import site.aoba.hotelmanagement.infrastructure.models.StockInModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockInRepository implements IStockInRepository {
    private final StockInMapper mapper;

    @Override
    public List<StockIn> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<StockIn> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public StockIn getEntityById(Long id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public StockIn getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(StockIn entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(StockIn entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<StockIn> searchEntities(Predicate<StockIn> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<StockIn> searchEntities(Predicate<StockIn> entityPredicate, int pageSize, int pageIndex,
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

    private StockInModel toModel(StockIn entity) {
        val x = new StockInModel();
        x.setId(entity.getId());
        x.setStockInContractId(entity.getStockInContractId());
        x.setStockInGoodId(entity.getStockInGoodId());
        x.setStockInTime(entity.getStockInTime());
        x.setStockInCount(entity.getStockInCount());
        x.setStockInPrice(entity.getStockInPrice());
        x.setStockInReceiptId(entity.getStockInReceiptId());
        x.setStockInUserId(entity.getStockInUserId());

        return x;
    }

    private StockIn toEntity(StockInModel x) {
        return new StockIn(
                x.getId(),
                x.getStockInContractId(),
                x.getStockInGoodId(),
                x.getStockInTime(),
                x.getStockInCount(),
                x.getStockInPrice(),
                x.getStockInReceiptId(),
                x.getStockInUserId()

        );
    }
}
