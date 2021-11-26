package site.aoba.hotelmanagement.architecture.ui.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PagedContent<TEntity> {
    private final int pageIndex;
    private final int pageSize;
    private final int totalPage;
    private final List<TEntity> items;
}
