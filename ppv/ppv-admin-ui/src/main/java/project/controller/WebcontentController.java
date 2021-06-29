package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.controller.representation.WebcontentRepresentation;
import project.service.WebcontentService;

import java.util.Optional;

@Controller
@RequestMapping("/webcontent")
public class WebcontentController {

    private WebcontentService webcontentService;

    public WebcontentController(WebcontentService webcontentService) {
        this.webcontentService = webcontentService;
    }

    @GetMapping("/")
    public String webcontent(Model model) {
        model.addAttribute("webcontent", webcontentService.findAll());
        model.addAttribute("activePage", "Webcontent");
        return "webcontent";
    }

    @GetMapping("/add")
    public String addWebcontent(Model model) {
        model.addAttribute("webcontent", new WebcontentRepresentation());
        return "webcontent_add";
    }

    @PostMapping("/add")
    public String addWebcontent(
            @ModelAttribute WebcontentRepresentation webcontent,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "webcontent_add";
        }
        webcontentService.save(webcontent);
        return "redirect:/webcontent/";
    }

    @GetMapping("/remove/{id}")
    public String removeWebcontent(
            @PathVariable("id") Long id
    ) {
        webcontentService.removeWebcontent(id);
        return "redirect:/webcontent/";
    }

    @GetMapping("/edit/{id}")
    public String editWebcontent(@PathVariable Long id, Model model) {
        Optional<WebcontentRepresentation> webcontent = webcontentService.findById(id);
        model.addAttribute("webcontent", webcontent);
        return "webcontent_edit";
    }

    @PostMapping("/edit")
    public String editWebcontent(@ModelAttribute WebcontentRepresentation webcontent) {
        webcontentService.save(webcontent);
        return "redirect:/webcontent/";
    }

}
