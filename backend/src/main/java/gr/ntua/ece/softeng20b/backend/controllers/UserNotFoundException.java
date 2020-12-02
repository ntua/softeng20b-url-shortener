package gr.ntua.ece.softeng20b.backend.controllers;

class UserNotFoundException extends RuntimeException {
  UserNotFoundException(Integer id) {
    super("Could not find user " + id);
  }
}
