package de.rwth.webtech.npe.UserAPI;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserDatabase database = UserDatabase.getInstance();

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<List<User>>(database.getUsers(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
    Optional<User> user = database.getUser(id);

    if (user.isPresent()) {
      return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user id " + id + " not found");
    }
  }

  @PostMapping
  public ResponseEntity<User> addUser(@RequestBody User input) {
    User user =
        new User(input.getFirstName(), input.getLastName(), input.getPassword(), input.getEmail());
    database.addUser(user);
    return new ResponseEntity<User>(user, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User input) {
    Optional<User> user = database.getUser(id);

    if (user.isPresent()) {
      user.get().setFirstName(input.getFirstName());
      user.get().setLastName(input.getLastName());
      user.get().setPassword(input.getPassword());
      user.get().setEmail(input.getEmail());
      return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user id " + id + " not found");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
    if (database.deleteUser(id)) {
      return ResponseEntity.status(HttpStatus.OK).body("user id " + id + " deleted");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user id " + id + " not found");
    }
  }
}
