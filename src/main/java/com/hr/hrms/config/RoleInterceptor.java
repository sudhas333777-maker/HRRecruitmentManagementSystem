package com.hr.hrms.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class RoleInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {


        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");

        String url = request.getRequestURI();


        // Block recruiter from Offers
        if(url.startsWith("/offers")) {

            if("RECRUITER".equalsIgnoreCase(role)) {

                response.sendRedirect("/dashboard");

                return false;
            }
        }


        // Block recruiter from Jobs
        if(url.startsWith("/jobs")) {

            if("RECRUITER".equalsIgnoreCase(role)) {

                response.sendRedirect("/dashboard");

                return false;
            }
        }


        return true;
    }
}