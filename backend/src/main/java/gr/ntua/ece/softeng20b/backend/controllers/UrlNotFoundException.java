package gr.ntua.ece.softeng20b.backend.controllers;

class UrlNotFoundException extends RuntimeException {
  UrlNotFoundException(String id) {
    super("Could not find URL " + id);
  }
}
