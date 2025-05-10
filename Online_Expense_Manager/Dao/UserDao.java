package Dao;

import entity.User;
import java.util.List;

public interface UserDao {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);  // Change the userId type to String

    User updateUser(String userId, User updatedUser);  // Change the userId type to String

    String deleteUser(String userId);
}
