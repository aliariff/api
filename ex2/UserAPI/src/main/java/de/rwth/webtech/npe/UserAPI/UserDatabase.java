package de.rwth.webtech.npe.UserAPI;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;

public class UserDatabase {
  private HashMap<Long, User> users = new HashMap<>();
  private static UserDatabase instance;

  private UserDatabase() {
    addUser(new User("Cristiano", "Ronaldo", "1234", "ronaldo@gmail.com"));
    addUser(new User("Lionel", "Messi", "1234", "messi@gmail.com"));
    addUser(new User("Alex", "Hunter", "1234", "alex.hunter@gmail.com"));
  }

  public static UserDatabase getInstance() {
    if (instance == null) {
      instance = new UserDatabase();
    }
    return instance;
  }

  public Optional<User> getUser(Long id) {
    User user = users.get(id);
    return (user == null) ? Optional.empty() : Optional.of(user);
  }

  public ArrayList<User> getUsers() {
    return new ArrayList<>(users.values());
  }

  public void addUser(User user) {
    users.put(user.getId(), user);
  }

  public boolean deleteUser(Long id) {
    return (users.remove(id) == null) ? false : true;
  }
}
