package daoimp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ResourceNotFoundException.ResourceNotFoundException;

import Dao.UserDao;
import entity.User;
import util.HibernateUtil;

public class UserDaoImpl implements UserDao {

    @Override
    public User createUser(User user) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(user);  // Save the user entity
            session.getTransaction().commit();
            return user;
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            List<User> userList = query.list();
            return userList;
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public User getUserById(String userId) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(User.class, userId);  // Retrieve user by ID
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public User updateUser(String userId, User updatedUser) {
        try (Session session = HibernateUtil.getSession()) {
            User user = session.get(User.class, userId);
            if (user != null) {
                session.beginTransaction();

                // Update fields
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                user.setPassword(updatedUser.getPassword());

                session.saveOrUpdate(user);  // Save updated user
                session.getTransaction().commit();
                return user;
            } else {
                // Throw exception if user is not found
                throw new ResourceNotFoundException("User not found");
            }
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String deleteUser(String userId) {
        String message = null;
        try (Session session = HibernateUtil.getSession()) {
            User user = session.get(User.class, userId);
            if (user != null) {
                session.beginTransaction();
                session.delete(user);  // Delete user from DB
                session.getTransaction().commit();
                message = "User deleted successfully";
            } else {
                message = "User not found";
            }
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return message;  // Error: You're returning String but the method expects an int
    }
}