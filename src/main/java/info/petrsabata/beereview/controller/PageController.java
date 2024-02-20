package info.petrsabata.beereview.controller;

import info.petrsabata.beereview.service.BeerService;
import info.petrsabata.beereview.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    private static final String LANDING_PAGE = "index";
    private final BeerService beerService;
    private final UserService userService;

    public PageController(
            BeerService beerService,
            UserService userService
    ) {
        this.beerService = beerService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String landingPage(Model model) {
        model.addAttribute("beers", beerService.getBeers());
        model.addAttribute("users", userService.getUsersDto());
        return LANDING_PAGE;
    }
}
