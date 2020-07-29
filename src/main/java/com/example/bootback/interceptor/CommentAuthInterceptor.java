package com.example.bootback.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bootback.entity.Comment;
import com.example.bootback.repository.CommentRepository;
import com.example.bootback.util.Sessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

// Comment Authorization 
@Component
public class CommentAuthInterceptor implements HandlerInterceptor {
    
    @Autowired
    CommentRepository commentRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        String httpMethod = request.getMethod();

        if(httpMethod.equals("POST")||httpMethod.equals("DELETE")){
            String sessionItem = (String)request.getSession().getAttribute(Sessions.SESSION_ID);
            Map<?,?> pathVariables = (Map<?,?>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
            Long id = Long.parseLong((String)pathVariables.get("id"));

            Comment comment = commentRepository.findById(id).get();
            String commentWriter = comment.getCreatedBy();

            if(!commentWriter.equals(sessionItem)){
                response.getOutputStream().println("NOT AUTHORIZE!!");
                return false;
            }
        }

        return true;
        
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}