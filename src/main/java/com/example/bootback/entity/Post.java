package com.example.bootback.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {
    
    @Id
    @GeneratedValue
    private Long Id;
    private String title; 
    private String content;

    @Override
    public boolean equals(Object o){
        
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(Id, post.Id)&&Objects.equals(title, post.title)&&Objects.equals(content, post.content);

    }

    @Override
    public int hashCode(){
        return Objects.hash(Id, title, content);
    }


}