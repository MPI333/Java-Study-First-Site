package project.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.controller.representation.UserRepresentation;
import project.service.RoleService;
import project.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    private RoleService roleService;

    public UserController(UserService userService,
                          RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping({"/login"})
    public String loginPage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("activePage", "Other");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "home";
    }

    @GetMapping({"/registration"})
    public String registrationPage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("activePage", "Other");
        model.addAttribute("user", new UserRepresentation());
        model.addAttribute("roles", roleService.findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "registration";
        }
        return "home";
    }

    @PostMapping({"/registration"})
    public String registerUser(@ModelAttribute UserRepresentation user,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping({"/private"})
    public String privatePage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("activePage", "Other");
        return "private";
    }

    @PostMapping("/private/withdrawal/{id}")
    public String doWithdrawal(@PathVariable Long id) {
        userService.withdrawal(userService.findById(id).get());
        return "redirect:/success";
    }

}
