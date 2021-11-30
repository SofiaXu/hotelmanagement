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
import site.aoba.hotelmanagement.domain.models.Deposit;
import site.aoba.hotelmanagement.domain.repository.IDepositRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.DepositMapper;
import site.aoba.hotelmanagement.infrastructure.models.DepositModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepositRepository implements IDepositRepository {
    private final DepositMapper mapper;

    @Override
    public List<Deposit> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<Deposit> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Deposit getEntityById(Long id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public Deposit getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(Deposit entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(Deposit entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<Deposit> searchEntities(Predicate<Deposit> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<Deposit> searchEntities(Predicate<Deposit> entityPredicate, int pageSize, int pageIndex,
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

    private DepositModel toModel(Deposit entity) {
        val x = new DepositModel();
        x.setId(entity.getId());
        x.setDepositCustomerId(entity.getDepositCustomerId());
        x.setDepositBookId(entity.getDepositBookId());
        x.setDepositPrice(entity.getDepositPrice());
        x.setDepositCreatedTime(entity.getDepositCreatedTime());
        x.setDepositUserId(entity.getDepositUserId());

        return x;
    }

    private Deposit toEntity(DepositModel x) {
        return new Deposit(
                x.getId(),
                x.getDepositCustomerId(),
                x.getDepositBookId(),
                x.getDepositPrice(),
                x.getDepositCreatedTime(),
                x.getDepositUserId()

        );
    }
}
