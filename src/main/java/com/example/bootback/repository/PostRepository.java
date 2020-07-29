package com.example.bootback.repository;

import com.example.bootback.entity.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    Page<Post> findAll(Pageable pageable); // Paging 하는 함수

    @Query(
        value = " SELECT p FROM Post p WHERE p.title LIKE %:title% OR p.content LIKE %:content%", 
        countQuery = "SELECT COUNT(p.id) FROM Post p WHERE p.title LIKE %:title% OR p.content LIKE %:content%"
    )
    Page<Post> findAllSearch(String title, String content, Pageable pageable);
}