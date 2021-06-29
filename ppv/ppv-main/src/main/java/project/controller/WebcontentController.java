package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.controller.representation.UserRepresentation;
import project.controller.representation.WebcontentRepresentation;
import project.service.UserService;
import project.service.WebcontentService;

@Controller
public class WebcontentController {

    private UserService userService;

    private WebcontentService webcontentService;

    public WebcontentController(UserService userService,
                                WebcontentService webcontentService) {
        this.userService = userService;
        this.webcontentService = webcontentService;
    }

    @GetMapping({"/webcontent"})
    public String webcontentPage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("webcontent", webcontentService.findAll());
        model.addAttribute("activePage", "Webcontent");
        return "webcontent";
    }

    @PostMapping({"/webcontent/{content}/{viewer}"})
    public String viewContent(@PathVariable Long content, @PathVariable Long viewer) {
        WebcontentRepresentation webcontent = webcontentService.findById(content).get();
        UserRepresentation user = userService.findById(viewer).get();
        webcontentService.view(webcontent);
        userService.view(webcontent, user);
        return "redirect:/success";
    }

}
