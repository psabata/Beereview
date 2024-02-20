package info.petrsabata.beereview.model;

import java.util.List;

public record User(int id, String name, List<Review> reviews) {

    public record Review(int beerId, int rating, String comment) {
    }

}
