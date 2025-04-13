package edu.vwcc.guestbook.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@Order(2)  // Lower priority than the API handler
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Get the status code
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            
            // 404 error
            if (statusCode == 404) {
                // Add any additional attributes for the error template
                model.addAttribute("path", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
                model.addAttribute("timestamp", new java.util.Date());
                model.addAttribute("message", "The page you requested was not found");              
                return "error/404";
            }         
            // Handle other error codes if needed
        }
        
        // Default error page
        return "error/error";
    }
}