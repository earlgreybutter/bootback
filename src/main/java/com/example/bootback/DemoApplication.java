package com.example.bootback;

import java.util.Optional;

import com.example.bootback.util.Sessions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@EnableJpaAuditing
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorProvider(){
		return() -> {
			ServletRequestAttributes attr 
				= (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

			String currentUser = (String)attr.getRequest().getSession().getAttribute(Sessions.SESSION_ID);

			if(currentUser != null){
				return Optional.of(currentUser);
			}
			else{
				return Optional.of("Anonymous");
			}
		};
	}
	// RequestContextHolder 이용해서 Servlet 요청 객체 가져올 수 있음 
	// Servlet 요청 내에 있는 session 값을 가져올 수 있는 것 
	// Spring Security 쓰는 경우, SecurityContext 를 통해 세션 정보들을 가져와서 넣어주면 됨 

}
