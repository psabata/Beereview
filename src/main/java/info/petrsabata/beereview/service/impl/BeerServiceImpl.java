package info.petrsabata.beereview.service.impl;

import info.petrsabata.beereview.model.Beer;
import info.petrsabata.beereview.repository.BeerRepository;
import info.petrsabata.beereview.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;

    public BeerServiceImpl(
            @Autowired BeerRepository beerRepository
    ) {
        this.beerRepository = beerRepository;
    }

    @Override
    public List<Beer> getBeers() {
        return beerRepository.findAll();
    }
}
