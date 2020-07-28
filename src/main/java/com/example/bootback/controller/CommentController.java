package com.example.bootback.controller;

import java.util.List;
import java.util.Optional;

import com.example.bootback.entity.Comment;
import com.example.bootback.entity.Post;
import com.example.bootback.repository.CommentRepository;
import com.example.bootback.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/post/{id}/comment")
    public List<Comment> getPostComments(@PathVariable Long id, @RequestBody Comment comment){
        Post post = postRepository.findById(id).get();
        return commentRepository.findCommentsByPost(post);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/post/{id}/comment")
    public Comment createComment(@PathVariable Long id, @RequestBody Comment comment ){
        Optional<Post> postItem = postRepository.findById(id);
        comment.setPost(postItem.get());
        commentRepository.save(comment);
        
        return comment;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/post/{id}/comment/{commentID}")
    public Comment updateComment(@PathVariable Long id, @PathVariable Long commentID, @RequestBody Comment comment){

        Optional<Post> postItem = postRepository.findById(id);
        comment.setPost(postItem.get());
        Comment newComment = commentRepository.findById(commentID).get();

        newComment.setTitle(comment.getTitle());
        newComment.setContent(comment.getContent());
        newComment.setWriter(comment.getWriter());

        return newComment;
    }
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/post/{id}/comment/{commentID}")
    public String deleteComment(@PathVariable Long id, @PathVariable Long commentID){
        commentRepository.deleteById(commentID);
        return "Comment Delete Success";
    }
    
}