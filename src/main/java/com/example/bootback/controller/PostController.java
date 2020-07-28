package com.example.bootback.controller;

import java.util.List;
import java.util.Optional;

import com.example.bootback.entity.Post;
import com.example.bootback.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    
    @Autowired
    PostRepository postRepository; 

    @GetMapping("/")
    public String hello(){
        return "Main Page!";
    }

    @GetMapping("/post")
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    @GetMapping("/post/{id}")
    public Post getPost(@PathVariable String id){
        Long postID = Long.parseLong(id);

        Optional<Post> post = postRepository.findById(postID);

        return post.get();
    }

    // findById() 로 수정할 post 가져와서 setter 이용 데이터 수정, 다시 반환 
    @PostMapping("/post/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post newPost){
        Long postID = Long.parseLong(id);

        Optional<Post> post = postRepository.findById(postID);

        post.get().setTitle(newPost.getTitle());
        post.get().setContent(newPost.getContent());

        postRepository.save(post.get());

        return post.get();
    }

    // save() 로 json 으로 넘겨받은 post 저장 
    @PutMapping("/post")
    public Post createPost(@RequestBody Post post){
        Post newPost = postRepository.save(post);
        return newPost;
    }

    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable String id){
        Long postID = Long.parseLong(id);
        postRepository.deleteById(postID);

        return "Delete Success!";
    }

}