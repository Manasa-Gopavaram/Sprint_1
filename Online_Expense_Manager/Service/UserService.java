package service;

import java.util.List;
import entity.User;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);

    User updateUser(String userId, User updatedUser);

    String deleteUser(String userId);
}
