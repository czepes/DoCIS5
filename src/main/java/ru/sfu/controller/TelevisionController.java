package ru.sfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sfu.repository.TelevisionRepository;
import ru.sfu.entity.Television;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for Television CRUD operations
 * @author Agapchenko V.V.
 */
@Controller
@EnableMethodSecurity
@RequestMapping("/tvs")
public class TelevisionController {

    private final TelevisionRepository repository;

    /**
     * Repository dependency injection for data access
     * @param repository Television JPA repository
     */
    @Autowired
    public TelevisionController(TelevisionRepository repository) {
        this.repository = repository;
    }

    /**
     * 'Menu' GET handler
     * @return 'Menu' view path
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping()
    public String showMenu() {
        return "tvs/menu";
    }

    /**
     * 'Show televisions' GET handler
     * @return 'Show' view path
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/show")
    public String showAll(Model model) {
        model.addAttribute("tvs", repository.findAll());
        return "tvs/show";
    }

    /**
     * 'Show specific television' GET handler
     * @param id Television id path variable
     * @param model Model object
     * @return 'Television' view path
     */
    @GetMapping("/{id}")
    public String showTelevision(@PathVariable("id") int id, Model model) {
        Television tv = repository.findById(id).orElse(null);
        model.addAttribute("tv", tv);
        model.addAttribute("id", id);
        return "tvs/television";
    }

    /**
     * 'Create television' GET handler
     * @param tv Television object to fill in
     * @return 'New' view path
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/new")
    public String newTelevision(@ModelAttribute("tv") Television tv) {
        return "tvs/new";
    }

    /**
     * 'Create television' POST handler
     * @param tv Television object to save
     * @param bindingResult Result of validation
     * @return Redirect to 'Menu' if validation successful
     */
    @PostMapping("/new")
    public String saveTelevision(
            @ModelAttribute("tv") @Valid Television tv,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return "tvs/new";

        repository.save(tv);
        return "redirect:/tvs";
    }

    /**
     * 'Select television to edit' GET handler
     * @param model Model object
     * @return 'Select' view path
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/select")
    public String selectToEdit(Model model) {
        List<Television> tvs = repository.findAll();
        model.addAttribute("tvs", tvs);
        return "tvs/select";
    }

    /**
     * 'Edit television' GET handler
     * @param id Television id path variable
     * @param model Model object
     * @return 'Edit' view path
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/edit")
    public String editTelevision(@PathVariable("id") int id, Model model) {
        Television tv = repository.findById(id).orElse(null);
        model.addAttribute("tv", tv);
        model.addAttribute("id", id);
        if (tv == null)
            return "redirect:/tvs/" + id;
        return "tvs/edit";
    }

    /**
     * 'Edit television' POST handler
     * @param tv Television to update
     * @param bindingResult Validation result
     * @return Redirect to 'Menu' if validation successful
     */
    @PostMapping("/{id}/update")
    public String updateTelevision(
            @ModelAttribute("tv") @Valid Television tv,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return "tvs/edit";

        repository.save(tv);
        return "redirect:/tvs";
    }

    /**
     * 'Delete television' GET handler
     * @param model Model object
     * @return 'Delete' view path
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String selectToDelete(Model model) {
        model.addAttribute("tvs", repository.findAll());
        return "tvs/delete";
    }

    /**
     * 'Delete television' POST handler
     * @param id Television id path variable
     * @return Redirect to 'Menu'
     */
    @PostMapping("/{id}/delete")
    public String deleteTelevision(@PathVariable("id") int id) {
        repository.deleteById(id);
        return "redirect:/tvs";
    }

    /**
     * 'Find by id' GET handler
     * @param id ID parameter
     * @return Redirect to 'Show television'
     */
    @GetMapping("/find-by-id")
    public String findById(@RequestParam("id") int id) {
        return "redirect:/tvs/" + id;
    }

    /**
     * 'Find by width and height' GET handler
     * @return 'Find by width and height' view path
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/find-by-width-and-height")
    public String getDataToFind() {
        return "tvs/get-width-and-height";
    }

    /**
     * 'Find by width and height result' GET handler
     * @param width Width parameter
     * @param height Height parameter
     * @param model Model object
     * @return 'Show' view path
     */
    @GetMapping("/find-by-width-and-height-result")
    public String findByWidthAndHeight(
            @RequestParam("width") int width,
            @RequestParam("height") int height,
            Model model
    ) {
        model.addAttribute(
                "tvs",
                repository.findByWidthAndHeight(width, height)
        );
        return "tvs/show";
    }
}
