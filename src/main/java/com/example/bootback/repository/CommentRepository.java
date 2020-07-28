package com.example.bootback.repository;

import java.util.List;

import com.example.bootback.entity.Comment;
import com.example.bootback.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findCommentsByPost(Post post);
    
}