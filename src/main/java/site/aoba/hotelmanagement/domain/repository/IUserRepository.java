package site.aoba.hotelmanagement.domain.repository;

import site.aoba.hotelmanagement.architecture.domain.repository.IRepository;
import site.aoba.hotelmanagement.domain.models.User;

public interface IUserRepository extends IRepository<Integer, User> {
    public void changePassword(User user);
}
