package site.aoba.hotelmanagement.architecture.domain.model;

public interface IEntity<TId> {
    TId getId();

    void setId(TId id);
}
