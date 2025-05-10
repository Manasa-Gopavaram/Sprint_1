package serviceimpl;

import java.util.List;

import Dao.UserDao;  // Make sure to import the correct package for UserDao
import daoimp.UserDaoImpl;  // Correct import for the UserDaoImpl class
import entity.User;
import service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();  // Manually instantiating UserDaoImpl

    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User updateUser(String userId, User updatedUser) {
        return userDao.updateUser(userId, updatedUser);
    }

    @Override
    public String deleteUser(String userId) {
        return userDao.deleteUser(userId);
    }
}
