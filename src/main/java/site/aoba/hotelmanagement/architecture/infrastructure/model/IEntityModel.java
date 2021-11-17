package site.aoba.hotelmanagement.architecture.infrastructure.model;

public interface IEntityModel<TId> extends IModel {
    TId getId();
    void setId(TId id);
}
