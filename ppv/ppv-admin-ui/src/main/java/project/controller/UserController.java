package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.controller.representation.UserRepresentation;
import project.service.RoleService;
import project.service.UserService;

import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    private RoleService roleService;

    public UserController(UserService userService,
                          RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("activePage", "Users");
        return "users";
    }

    @GetMapping("/users/add")
    public String addUser(Model model) {
        model.addAttribute("user", new UserRepresentation());
        model.addAttribute("roles", roleService.findAll());
        return "user_add";
    }

    @PostMapping("/users/add")
    public String addUser(
            @ModelAttribute UserRepresentation user,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "user_add";
        }
        userService.save(user);
        return "redirect:/users/";
    }

    @GetMapping("/users/remove/{id}")
    public String removeUser(
            @PathVariable("id") Long id
    ) {
        userService.removeUser(id);
        return "redirect:/users/";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Optional<UserRepresentation> user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "user_edit";
    }

    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute UserRepresentation user) {
        userService.save(user);
        return "redirect:/users/";
    }

}
