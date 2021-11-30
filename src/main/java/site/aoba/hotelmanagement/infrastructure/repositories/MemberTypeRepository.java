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
import site.aoba.hotelmanagement.domain.models.MemberType;
import site.aoba.hotelmanagement.domain.repository.IMemberTypeRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.MemberTypeMapper;
import site.aoba.hotelmanagement.infrastructure.models.MemberTypeModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberTypeRepository implements IMemberTypeRepository {
    private final MemberTypeMapper mapper;

    @Override
    public List<MemberType> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberType> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public MemberType getEntityById(Integer id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public MemberType getEntityById(Integer id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(MemberType entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(MemberType entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Integer id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<MemberType> searchEntities(Predicate<MemberType> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<MemberType> searchEntities(Predicate<MemberType> entityPredicate, int pageSize, int pageIndex,
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

    private MemberTypeModel toModel(MemberType entity) {
        val x = new MemberTypeModel();
        x.setId(entity.getId());
        x.setMemberTypeName(entity.getMemberTypeName());
        x.setMemberTypeDiscount(entity.getMemberTypeDiscount());

        return x;
    }

    private MemberType toEntity(MemberTypeModel x) {
        return new MemberType(
                x.getId(),
                x.getMemberTypeName(),
                x.getMemberTypeDiscount()

        );
    }
}
