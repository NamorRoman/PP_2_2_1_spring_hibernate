package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      //add users ti db
      userService.addUserWithCar(new User("User1", "Lastname1", "user1@mail.ru")
              , new Car("VAZ", 2101));
      userService.addUserWithCar(new User("User2", "Lastname2", "user2@mail.ru")
              , new Car("BAZ", 2102));
      userService.addUserWithCar(new User("User1", "Lastname1", "user1@mail.ru")
              , new Car("GAZ", 2103));


      //get added users from db
      System.out.println(userService.get(1));
      System.out.println(userService.get(2));
      System.out.println(userService.get(3));


      //get user by car model and series
      System.out.println(userService.getUserByCar("GAZ", 2103));
      System.out.println(userService.getUserByCar("MAZ", 2222));

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//
//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();
//      }

      context.close();
   }
}
