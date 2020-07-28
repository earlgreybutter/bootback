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
public class Account {
    
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email; 
    private String password;

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if( o == null || getClass() != o.getClass() ){
            return false;
        }

        Account account = (Account) o;
        return id.equals(account.id) && username.equals(account.username)&& email.equals(account.email)&&password.equals(account.password);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, username, email, password );
    }
}