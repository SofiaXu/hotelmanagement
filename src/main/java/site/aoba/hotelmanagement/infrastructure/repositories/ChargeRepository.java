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
import site.aoba.hotelmanagement.domain.models.Charge;
import site.aoba.hotelmanagement.domain.repository.IChargeRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.ChargeMapper;
import site.aoba.hotelmanagement.infrastructure.models.ChargeModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChargeRepository implements IChargeRepository {
    private final ChargeMapper mapper;

    @Override
    public List<Charge> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<Charge> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Charge getEntityById(Long id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public Charge getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(Charge entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(Charge entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<Charge> searchEntities(Predicate<Charge> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<Charge> searchEntities(Predicate<Charge> entityPredicate, int pageSize, int pageIndex,
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

    private ChargeModel toModel(Charge entity) {
        val x = new ChargeModel();
        x.setId(entity.getId());
        x.setChargeRoomCurrentId(entity.getChargeRoomCurrentId());
        x.setChargeCustomerId(entity.getChargeCustomerId());
        x.setChargePrice(entity.getChargePrice());
        x.setChargeSummary(entity.getChargeSummary());
        x.setChargeComment(entity.getChargeComment());

        return x;
    }

    private Charge toEntity(ChargeModel x) {
        return new Charge(
                x.getId(),
                x.getChargeRoomCurrentId(),
                x.getChargeCustomerId(),
                x.getChargePrice(),
                x.getChargeSummary(),
                x.getChargeComment()

        );
    }
}
