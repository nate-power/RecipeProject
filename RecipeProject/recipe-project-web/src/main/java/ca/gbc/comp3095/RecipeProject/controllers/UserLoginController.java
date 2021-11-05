//*********************************************************************************//
//* Project: Null Recipes
//        * Assignment: Assignment #1
//        * Author(s): Nathan Power
//        * Student Number: 101247770
//        * Date: October 24th, 2021
//        * Description: This controller handles only login routing so if an authenticated user tries to access the
//        * login page again, it will redirect them to the index page of recipes (All Created Recipes). If the user
//        * has not logged in yet, it will redirect them to the login page.
//*********************************************************************************//

package ca.gbc.comp3095.RecipeProject.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class UserLoginController {

    @GetMapping
    public String checkUserAuthLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/recipes";
        }
        return "/login";
    }
}