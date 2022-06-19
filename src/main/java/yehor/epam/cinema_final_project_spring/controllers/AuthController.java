package yehor.epam.cinema_final_project_spring.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yehor.epam.cinema_final_project_spring.dto.UserSignUpDTO;
import yehor.epam.cinema_final_project_spring.exceptions.UserAlreadyExistException;
import yehor.epam.cinema_final_project_spring.services.UserService;
import yehor.epam.cinema_final_project_spring.utils.constants.HtmlFileConstants;

import javax.validation.Valid;

@Controller
@Slf4j
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return HtmlFileConstants.LOGIN_PAGE;
        } else {
            log.info("Authorized user tried to get login page");
            throw new AccessDeniedException("Authorized user tried to get login page");
        }
    }

    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("user", new UserSignUpDTO());
        return HtmlFileConstants.SIGN_UP_PAGE;
    }

    @PostMapping("/signup")
    public String signingUp(@ModelAttribute("user") @Valid UserSignUpDTO userSignUpDTO, BindingResult bindingResult, Model model) {
        if (hasError(userSignUpDTO, bindingResult)) {
            return HtmlFileConstants.SIGN_UP_PAGE;
        }
        try {
            userService.save(userSignUpDTO);
        } catch (UserAlreadyExistException e) {
            model.addAttribute("userExists", true);
            return HtmlFileConstants.SIGN_UP_PAGE;
        }
        return "redirect:/user/profile";
    }

    private boolean hasError(UserSignUpDTO userSignUpDTO, BindingResult bindingResult) {
        if (!userSignUpDTO.getPassword().equals(userSignUpDTO.getPasswordRepeat())) {
            bindingResult.addError(new FieldError("user", "passwordRepeat", "Passwords aren't equal"));
        }
        return bindingResult.hasErrors();
    }

}