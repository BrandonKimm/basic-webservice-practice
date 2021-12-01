package com.brandon.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String contents;

    @Builder
    public PostUpdateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
