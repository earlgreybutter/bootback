package com.example.bootback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadFileResponseDTO {
    
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size; 


}