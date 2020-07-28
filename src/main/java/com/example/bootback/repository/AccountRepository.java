package com.example.bootback.repository;

import com.example.bootback.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByUsername(String username);
    public Account findByUsernameAndPassword(String username, String password);
    // 스프링 데이터 jpa 규칙에 맞게 지어주면 해당 entity에 맞게 데이터를 조회/조작하는 코드 자동 생성 
}