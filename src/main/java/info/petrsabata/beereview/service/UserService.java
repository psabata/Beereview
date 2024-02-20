package info.petrsabata.beereview.service;

import info.petrsabata.beereview.model.User;
import info.petrsabata.beereview.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsersDto();

    List<User> getUsers();

    User saveOrUpdate(User.Review review);
}
