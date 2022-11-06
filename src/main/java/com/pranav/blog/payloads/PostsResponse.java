package com.pranav.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostsResponse {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private Boolean isLastPage;
    private Boolean isFirstPage;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
    List<PostDto> posts;
}
