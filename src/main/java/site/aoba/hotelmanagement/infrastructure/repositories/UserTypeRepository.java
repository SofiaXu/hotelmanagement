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
import site.aoba.hotelmanagement.domain.models.UserType;
import site.aoba.hotelmanagement.domain.repository.IUserTypeRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.UserTypeMapper;
import site.aoba.hotelmanagement.infrastructure.models.UserTypeModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserTypeRepository implements IUserTypeRepository {
    private final UserTypeMapper mapper;

    @Override
    public List<UserType> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserType> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public UserType getEntityById(Integer id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public UserType getEntityById(Integer id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(UserType entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(UserType entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Integer id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<UserType> searchEntities(Predicate<UserType> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<UserType> searchEntities(Predicate<UserType> entityPredicate, int pageSize, int pageIndex,
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

    private UserTypeModel toModel(UserType entity) {
        val x = new UserTypeModel();
        x.setId(entity.getId());
        x.setUserTypeName(entity.getUserTypeName());

        return x;
    }

    private UserType toEntity(UserTypeModel x) {
        return new UserType(
                x.getId(),
                x.getUserTypeName()

        );
    }
}
