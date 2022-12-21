package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    User get(long id);

    User getUserByCar(String model, int series) ;

    List<User> listUsers();

    void addUserWithCar(User user, Car car);
}
