package info.petrsabata.beereview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Beer(int id, String brand, String name, String alcohol) {
}
