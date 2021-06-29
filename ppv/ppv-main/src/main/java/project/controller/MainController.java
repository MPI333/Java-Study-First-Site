package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import project.service.UserService;

@Controller
public class MainController {

    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/home", "/", "/index", "/main"})
    public String homePage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("activePage", "Home");
        return "home";
    }

    @RequestMapping({"/faq"})
    public String faqPage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("activePage", "Faq");
        return "faq";
    }

    @RequestMapping({"/support"})
    public String supportPage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("activePage", "Support");
        return "support";
    }

    @RequestMapping({"/success"})
    public String successPage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("activePage", "Other");
        return "success";
    }

}
