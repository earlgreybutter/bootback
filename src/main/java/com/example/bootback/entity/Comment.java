package com.example.bootback.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
    
    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long Id;
    private String title;
    private String content;
    private String writer;
    
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }

        Comment comment = (Comment) o;
        return Objects.equals(Id, comment.Id)&&Objects.equals(title, comment.title)&&Objects.equals(content, comment.content)&&Objects.equals(writer, comment.writer);
    }

    @Override
    public int hashCode(){
        return Objects.hash(Id, title, content, writer);
    }

}