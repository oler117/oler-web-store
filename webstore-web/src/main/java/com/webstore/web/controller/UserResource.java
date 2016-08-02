package com.webstore.web.controller;

import com.google.common.collect.Lists;
import com.webstore.common.model.auth.Role;
import com.webstore.common.model.auth.User;
import com.webstore.common.model.userprofile.UserProfile;
import com.webstore.common.repository.jpa.CountryRepository;
import com.webstore.common.repository.jpa.auth.RoleRepository;
import com.webstore.common.service.auth.UserService;
import com.webstore.web.controller.dto.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by oles on 7/26/2016.
 */
@RestController
@RequestMapping("/user")
public class UserResource {

    private static Role roleUser;

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initRoles() {
        roleUser = roleRepository.findByName("ROLE_USER");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Principal getAuthenticatedUser(Principal user) {
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody @Valid NewUserDTO newUser,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity("Invalid form data!", HttpStatus.BAD_REQUEST);
        }

        final UserProfile newUserProfile = new UserProfile(
                countryRepository.findOne(newUser.getCountryId()),
                newUser.getFirstName(),
                newUser.getLastName()
        );
        final User user = new User(newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()),
                Lists.newArrayList(roleUser),
                newUserProfile);

        userService.createNewUser(user);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration(@RequestParam("uid") Integer userId,
                                            @RequestParam("key") String confirmationToken,
                                            Model model, RedirectAttributes redirectAttributes) {

//        if (!userService.activateAccount(userId, confirmationToken)) {
//            return "Activating account error!";
//        }
        redirectAttributes.addFlashAttribute("activation_success", "Account activation success! You can use your credentials to log in");
//        return "redirect:/app/index.html";

        final ModelAndView modelAndView = new ModelAndView("/");
        modelAndView.getModelMap().addAttribute("activation_success", "Account activation success! You can use your credentials to log in");
        return modelAndView;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity test() {

        return ResponseEntity.ok("Test");
    }

//    @RequestMapping(value="/logout", method = RequestMethod.POST)
//    public String logout (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/";
//    }

}