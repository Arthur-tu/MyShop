package shop.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.example.shop.domain.User;
import shop.example.shop.dto.UserDto;
import shop.example.shop.service.UserService;
import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDto());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDto dto, Model model){
        if(userService.save(dto)){
            return "redirect:/users";
        }
        else {
            model.addAttribute("user", dto);
            return "user";
        }
    }

    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("You are not authorized");
        }
        UserDto userDto = userService.findDtoByName(principal.getName());
        model.addAttribute("user", userDto);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(UserDto dto, Model model, Principal principal) {
        if (principal == null || !Objects.equals(principal.getName(), dto.getName())) {
            throw new RuntimeException("You are not authorized");
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()
                && !Objects.equals(dto.getPassword(), dto.getMatchingPassword())) {
            model.addAttribute("user", dto);
            return "/users/profile";
        }
        userService.updateProfile(dto);
        return "redirect:/users/profile";
    }
}