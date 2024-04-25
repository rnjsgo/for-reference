package com.thecommerce.app.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
public class PaginationInfo {

    private int currentPage; // 현재 페이지
    private int totalPages; // 전체 페이지 수
    private long totalElements; // 전체 데이터 수
    private int pageSize; // 페이지당 데이터 수
    private boolean hasPrevious; // 이전 페이지 존재 여부
    private boolean hasNext; // 다음 페이지 존재 여부

    public static PaginationInfo fromPage(final Page<?> page) {
        return PaginationInfo.builder()
                .currentPage(page.getNumber() + 1)  // Page는 0부터 시작하므로 1을 더함
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .pageSize(page.getSize())
                .hasPrevious(page.hasPrevious())
                .hasNext(page.hasNext())
                .build();
    }
}
