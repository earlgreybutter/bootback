package com.example.bootback.repository;

import java.util.List;

import com.example.bootback.entity.UploadFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UploadFileInfoRepository extends JpaRepository<UploadFile, Long> {

    @Query("select f from UploadFile f where post_id = :id")
    List<UploadFile> findAllByPostId(Long id);

}