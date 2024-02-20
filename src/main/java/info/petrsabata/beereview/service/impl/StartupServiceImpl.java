package info.petrsabata.beereview.service.impl;

import info.petrsabata.beereview.model.Beer;
import info.petrsabata.beereview.repository.BeerRepository;
import info.petrsabata.beereview.repository.UserRepository;
import info.petrsabata.beereview.service.StartupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class StartupServiceImpl implements StartupService {

    private static final String URL = "https://random-data-api.com/api/v2/beers?size=5";

    private final RestTemplate restTemplate;
    private final BeerRepository beerRepository;
    private final UserRepository userRepository;
    private final Logger logger;

    public StartupServiceImpl(
            @Autowired RestTemplateBuilder restTemplateBuilder,
            @Autowired BeerRepository beerRepository,
            @Autowired UserRepository userRepository
    ) {
        this.restTemplate = restTemplateBuilder.build();
        this.beerRepository = beerRepository;
        this.userRepository = userRepository;
        this.logger = LoggerFactory.getLogger(StartupServiceImpl.class);
    }

    @Override
    public void initResourcesOnStartup() {
        initBeers();
        initUsers();
    }

    public void initBeers() {
        List<Beer> beers;

        try {
            Beer[] response = restTemplate.getForObject(URL, Beer[].class);
            beers = response != null ? asList(response) : Collections.emptyList();
            logger.info("Resources downloaded: {}", beers);
        } catch (Exception exception) {
            logger.error("Error downloading resources: {}", exception.toString());
            beers = Collections.emptyList();
        }

        beerRepository.deleteAll();
        beerRepository.saveAll(beers);
    }

    public void initUsers() {
        userRepository.deleteAll();
    }

}
