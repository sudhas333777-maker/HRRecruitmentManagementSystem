package com.hr.hrms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import com.hr.hrms.entity.User;
import com.hr.hrms.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String loginPage() {
	    return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {
		
		Optional<User> user = userService.findByEmail(email);
		
		if (user.isPresent()) {

		    User u = user.get();

		    if (u.getPassword().equals(password)) {

		        // Store logged-in user details in session
		        session.setAttribute("user", u);
		        session.setAttribute("role", u.getRole());

		        if (u.getRole().equalsIgnoreCase("ADMIN")) {
		            return "redirect:/dashboard";
		        }

		        else if (u.getRole().equalsIgnoreCase("HR")) {
		            return "redirect:/dashboard";
		        }

		        else if (u.getRole().equalsIgnoreCase("RECRUITER")) {
		            return "redirect:/dashboard";
		        }
		    }
		}
		
		model.addAttribute("error","Invalid Email or Password");
		
		return "login";
	}
}
