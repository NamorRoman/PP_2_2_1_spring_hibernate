package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User get(long id) {
       return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User getUserByCar(String model, int series) {
       Query query = sessionFactory.getCurrentSession().
               createQuery("from User as user " +
                       "inner join fetch user.UsersCar WHERE user.UsersCar.model = :model and user.UsersCar.series = :series");
       query.setParameter("model", model);
       query.setParameter("series", series);

       try {
           return (User) query.getSingleResult();
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return null;
       }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void addUserWithCar(User user, Car car) {
        user.setUsersCar(car);
        System.out.println(user);
        sessionFactory.getCurrentSession().save(user);
    }

}
