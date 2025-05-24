package com.example.blog.dto;

import lombok.Data;
import java.util.List;

@Data
public class PageResponse<T> {
    private int total;
    private List<T> list;

    public PageResponse(int total, List<T> list) {
        this.total = total;
        this.list = list;
    }
}