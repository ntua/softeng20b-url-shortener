package gr.ntua.ece.softeng20b.backend.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import gr.ntua.ece.softeng20b.backend.models.User;
import gr.ntua.ece.softeng20b.backend.models.UserRepository;

@RestController
class UserController {
  private final UserRepository repository;

  UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/users")
  List<User> all() {
    return repository.findAll();
  }

  @PostMapping("/users")
  User newUser(@RequestBody User newUser) {
    return repository.save(newUser);
  }

  @GetMapping("/users/{id}")
  User one(@PathVariable Integer id) {
    return repository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
  }

  @PutMapping("/users/{id}")
  User replaceUser(@RequestBody User newUser, @PathVariable Integer id) {
    return repository.findById(id)
      .map(user -> {
        user.setName(newUser.getName());
        return repository.save(user);
      })
      .orElseThrow(() -> new UserNotFoundException(id));
  }

  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Integer id) {
    repository.deleteById(id);
  }
}
