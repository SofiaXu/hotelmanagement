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
import site.aoba.hotelmanagement.domain.models.Customer;
import site.aoba.hotelmanagement.domain.repository.ICustomerRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.CustomerMapper;
import site.aoba.hotelmanagement.infrastructure.models.CustomerModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerRepository implements ICustomerRepository {
    private final CustomerMapper mapper;

    @Override
    public List<Customer> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Customer getEntityById(Integer id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public Customer getEntityById(Integer id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(Customer entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(Customer entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Integer id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<Customer> searchEntities(Predicate<Customer> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<Customer> searchEntities(Predicate<Customer> entityPredicate, int pageSize, int pageIndex,
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

    private CustomerModel toModel(Customer entity) {
        val x = new CustomerModel();
        x.setId(entity.getId());
        x.setCustomerIdNumber(entity.getCustomerIdNumber());
        x.setCustomerName(entity.getCustomerName());
        x.setCustomerBirthDate(entity.getCustomerBirthDate());
        x.setCustomerGender(entity.getCustomerGender());
        x.setCustomerPhone(entity.getCustomerPhone());
        x.setCustomerEmail(entity.getCustomerEmail());
        x.setCustomerMemberTypeId(entity.getCustomerMemberTypeId());

        return x;
    }

    private Customer toEntity(CustomerModel x) {
        return new Customer(
                x.getId(),
                x.getCustomerIdNumber(),
                x.getCustomerName(),
                x.getCustomerBirthDate(),
                x.getCustomerGender(),
                x.getCustomerPhone(),
                x.getCustomerEmail(),
                x.getCustomerMemberTypeId()

        );
    }
}
