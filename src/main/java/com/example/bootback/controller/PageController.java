package com.example.bootback.controller;

import com.example.bootback.dto.PagingDTO;
import com.example.bootback.entity.Post;
import com.example.bootback.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {
    
    @Autowired
    PostRepository postRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/post/page")
    public Page<PagingDTO> paging(@PageableDefault(size=5, sort="createdTime") Pageable pageRequest){   // paging

        Page<Post> postList = postRepository.findAll(pageRequest);

        Page<PagingDTO> pagingList = postList.map(
            post -> new PagingDTO(post.getId() , post.getTitle() , post.getCreatedBy() , post.getCreatedTime())
        );
        return pagingList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/post/page/search")
    public Page<PagingDTO> searchPaging(    // search
        @RequestParam String title, 
        @RequestParam String content, 
        @PageableDefault(size = 5, sort = "createdTime") Pageable pageRequest){

            Page<Post> postList = postRepository.findAllSearch(title, content, pageRequest);

            Page<PagingDTO> pagingList = postList.map(
              post -> new PagingDTO(post.getId(), post.getTitle(), post.getCreatedBy(), post.getCreatedTime() )  
            );

            return pagingList;
        }
    
}