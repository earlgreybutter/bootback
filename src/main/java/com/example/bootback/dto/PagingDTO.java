package com.example.bootback.dto;

import java.time.LocalDateTime;

import lombok.Data;

// Entity 를 그대로 넘길 수도 있지만 
// Entity 가 변경되는 경우, API 스펙이 바뀌므로 사용자에게 혼란을 줄 수 있다. 
@Data
public class PagingDTO {

    private Long id;
    private String title;
    private String createdBy;
    private LocalDateTime createdTime;

    public PagingDTO(Long id, String title, String createdBy, LocalDateTime createdTime){
        this.id = id;
        this.title = title;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }
    
}