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
import site.aoba.hotelmanagement.domain.models.Book;
import site.aoba.hotelmanagement.domain.repository.IBookRepository;
import site.aoba.hotelmanagement.infrastructure.mappers.BookMapper;
import site.aoba.hotelmanagement.infrastructure.models.BookModel;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookRepository implements IBookRepository {
    private final BookMapper mapper;

    @Override
    public List<Book> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Book getEntityById(Long id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public Book getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity(Book entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity(Book entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<Book> searchEntities(Predicate<Book> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchEntities(Predicate<Book> entityPredicate, int pageSize, int pageIndex, boolean refresh) {
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

    private BookModel toModel(Book entity) {
        val x = new BookModel();
        x.setId(entity.getId());
        x.setBookCustomerId(entity.getBookCustomerId());
        x.setBookUserId(entity.getBookUserId());
        x.setBookCreatedTime(entity.getBookCreatedTime());
        x.setBookStartTime(entity.getBookStartTime());
        x.setBookEndTime(entity.getBookEndTime());
        x.setBookIsCheckedIn(entity.getBookIsCheckedIn());

        return x;
    }

    private Book toEntity(BookModel x) {
        return new Book(
                x.getId(),
                x.getBookCustomerId(),
                x.getBookUserId(),
                x.getBookCreatedTime(),
                x.getBookStartTime(),
                x.getBookEndTime(),
                x.getBookIsCheckedIn());
    }
}
