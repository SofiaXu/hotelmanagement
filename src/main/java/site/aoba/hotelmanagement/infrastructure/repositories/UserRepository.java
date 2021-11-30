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
import site.aoba.hotelmanagement.domain.models.User;
import site.aoba.hotelmanagement.domain.repository.IUserRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.UserMapper;
import site.aoba.hotelmanagement.infrastructure.models.UserModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepository implements IUserRepository {
    private final UserMapper mapper;

    @Override
    public List<User> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public User getEntityById(Integer id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public User getEntityById(Integer id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(User entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(User entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Integer id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<User> searchEntities(Predicate<User> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<User> searchEntities(Predicate<User> entityPredicate, int pageSize, int pageIndex, boolean refresh) {
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

    @Override
    public void changePassword(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserPasswordHash(user.getUserPasswordHash());
        userModel.setUserId(user.getId());
        mapper.updateByPrimaryKey(userModel);
    }

    private UserModel toModel(User entity) {
        val x = new UserModel();
        x.setId(entity.getId());
        x.setUserName(entity.getUserName());
        x.setUserRealName(entity.getUserRealName());
        x.setUserTypeId(entity.getUserTypeId());
        x.setUserAge(entity.getUserAge());
        x.setUserGender(entity.getUserGender());
        return x;
    }

    private User toEntity(UserModel x) {
        return new User(x.getId(), x.getUserName(), x.getUserRealName(),
        x.getUserTypeId(), x.getUserAge(), x.getUserGender(), x.getUserPasswordHash());
    }
}
