package info.petrsabata.beereview.controller;

import info.petrsabata.beereview.model.User;
import info.petrsabata.beereview.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final Logger logger;

    public UserController(
            @Autowired UserService userService
    ) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    @GetMapping("/users")
    public List<User> users() {
        return userService.getUsers();
    }

    @PostMapping("/review")
    public User review(@RequestBody User.Review review) {
        return userService.saveOrUpdate(review);
    }

    @PostMapping(path = "/reviewForm", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public void review(User.Review review, HttpServletResponse response) {
        userService.saveOrUpdate(review);
        try {
            response.sendRedirect("/");
        } catch (IOException exception) {
            logger.error("Redirection to landing page failed: {}", exception.toString());
        }
    }

}
