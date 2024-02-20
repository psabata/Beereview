package info.petrsabata.beereview.controller;

import info.petrsabata.beereview.model.Beer;
import info.petrsabata.beereview.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(
            @Autowired BeerService berBeerService
    ) {
        this.beerService = berBeerService;
    }

    @GetMapping("/beers")
    public List<Beer> beers() {
        return beerService.getBeers();
    }

}
