package info.petrsabata.beereview.model;

import java.util.List;

public record UserDto(int id, String name, List<UserDto.Review> reviews) {

    public record Review(int beerId, String beerName, int rating, String comment) {
    }

}
