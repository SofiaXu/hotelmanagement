package site.aoba.hotelmanagement.infrastructure.repositories;

import java.util.List;
import java.util.function.Predicate;

import site.aoba.hotelmanagement.domain.models.User;
import site.aoba.hotelmanagement.domain.repository.IUserRepository;


public class UserRepository implements IUserRepository {

    @Override
    public List<User> getEntities(long pageSize, long pageIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getEntities(long pageSize, long pageIndex, boolean refresh) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getEntityById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getEntityById(Integer id, boolean refresh) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateEntity(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createEntity(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteEntity(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<User> searchEntities(Predicate<User> entityPredicate, long pageSize, long pageIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> searchEntities(Predicate<User> entityPredicate, long pageSize, long pageIndex, boolean refresh) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clearItemCache(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clearItemsCache() {
        // TODO Auto-generated method stub
        
    }
    
}
