//*********************************************************************************//
//* Project: Null Recipes
//        * Assignment: Assignment #1
//        * Author(s): Nathan Power
//        * Student Number: 101247770
//        * Date: October 24th, 2021
//        * Description: This file is used in tandem with SecurityConfiguration to redirect users of the application
//        * based on their role (authorized or unauthorized) and utilizes Spring Security.
//*********************************************************************************//

package ca.gbc.comp3095.RecipeProject.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String redirectURL = request.getContextPath();
        response.sendRedirect(redirectURL);
    }
}