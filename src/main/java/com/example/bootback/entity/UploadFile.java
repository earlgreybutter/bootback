package com.example.bootback.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

// 파일 업로드/다운로드 정보 저장 
@Entity
@Getter
@Setter
public class UploadFile {
    
    @Id
    @GeneratedValue
    @Column(name="upload_file_d")
    private Long id;

    @Column
    private String fileName;

    @Column
    private String fileDownloadUri;

    @Column
    private String fileType;

    @Column
    private Long size;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    
}