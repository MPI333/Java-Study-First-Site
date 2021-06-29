package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.controller.representation.RoleRepresentation;
import project.service.RoleService;

import java.util.Optional;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String roles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("activePage", "Roles");
        return "roles";
    }

    @GetMapping("/add")
    public String addRole(Model model) {
        model.addAttribute("role", new RoleRepresentation());
        return "role_add";
    }

    @PostMapping("/add")
    public String addRole(
            @ModelAttribute RoleRepresentation role,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "role_add";
        }
        roleService.save(role);
        return "redirect:/roles/";
    }

    @GetMapping("/remove/{id}")
    public String removeRole(
            @PathVariable("id") Long id,
            Model model
    ) {
        roleService.removeRole(id);
        return "redirect:/roles/";
    }

    @GetMapping("/edit/{id}")
    public String editRole(@PathVariable Long id, Model model) {
        Optional<RoleRepresentation> role = roleService.findById(id);
        model.addAttribute("role", role);
        return "role_edit";
    }

    @PostMapping("/edit")
    public String editRole(@ModelAttribute RoleRepresentation role) {
        roleService.save(role);
        return "redirect:/roles/";
    }

}
