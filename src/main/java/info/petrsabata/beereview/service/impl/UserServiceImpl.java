package info.petrsabata.beereview.service.impl;

import info.petrsabata.beereview.model.Beer;
import info.petrsabata.beereview.model.User;
import info.petrsabata.beereview.model.UserDto;
import info.petrsabata.beereview.repository.BeerRepository;
import info.petrsabata.beereview.repository.UserRepository;
import info.petrsabata.beereview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BeerRepository beerRepository;

    public UserServiceImpl(
            @Autowired UserRepository userRepository,
            @Autowired BeerRepository beerRepository
    ) {

        this.userRepository = userRepository;
        this.beerRepository = beerRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDto> getUsersDto() {
        List<Beer> beers = beerRepository.findAll();

        return userRepository.findAll().stream().map(user ->
                new UserDto(
                        user.id(),
                        user.name(),
                        user.reviews().stream().map(review ->
                        {
                            Optional<Beer> optional = beers.stream().filter(beer -> beer.id() == review.beerId()).findFirst();
                            String beerName = optional.isPresent() ? optional.get().name() : "";

                            return new UserDto.Review(
                                    review.beerId(),
                                    beerName,
                                    review.rating(),
                                    review.comment()
                            );
                        }).toList()
                )).toList();
    }

    @Override
    public User saveOrUpdate(User.Review review) {
        User user = userRepository.findById(1).orElseGet(() -> new User(1, "default", Collections.emptyList()));
        List<User.Review> updatedReviews = updateOrAppend(user.reviews(), review);

        User updatedUser = new User(
                user.id(),
                user.name(),
                updatedReviews
        );
        userRepository.save(updatedUser);
        return updatedUser;
    }

    private List<User.Review> updateOrAppend(List<User.Review> reviews, User.Review review) {
        List<User.Review> result = new ArrayList<>(reviews);

        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).beerId() == review.beerId()) {
                result.set(i, review);
                return result;
            }
        }

        result.add(review);
        return result;
    }


}
