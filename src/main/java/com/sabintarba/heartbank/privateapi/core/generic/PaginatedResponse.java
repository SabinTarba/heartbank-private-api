package com.sabintarba.heartbank.privateapi.core.generic;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginatedResponse<T> {
    private List<T> data;
    private PaginationMeta pagination;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class PaginationMeta {
        private int page;
        private int size;
        private int totalPages;
        private long totalElements;
        private boolean hasNext;
        private boolean hasPrevious;
    }
} 
