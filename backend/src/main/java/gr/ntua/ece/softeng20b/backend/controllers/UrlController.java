package gr.ntua.ece.softeng20b.backend.controllers;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import gr.ntua.ece.softeng20b.backend.models.Url;
import gr.ntua.ece.softeng20b.backend.models.UrlRepository;

@RestController
class UrlController {
  private final UrlRepository repository;
  private static final Logger log =
    LoggerFactory.getLogger(UrlController.class);

  UrlController(UrlRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/urls")
  List<Url> all() {
    return repository.findAll();
  }

  @PostMapping("/urls")
  Url newUrl(@RequestBody Url newUrl) {
    log.info("POST /urls " + newUrl.toString());
    newUrl.initialize();
    return repository.save(newUrl);
  }

  @GetMapping("/urls/{id}")
  Url one(@PathVariable String id,
          @RequestParam(required=false, defaultValue="false") Boolean click) {
    log.info("GET /urls/" + id + " " + click.toString());
    return repository.findById(id)
      .map(url -> {
        if (click) { url.incUsed(); return repository.save(url); }
        else return url;
      })
      .orElseThrow(() -> new UrlNotFoundException(id));
  }

  @PutMapping("/urls/{id}")
  Url replaceUrl(@RequestBody Url newUrl, @PathVariable String id) {
    log.info("PUT /urls/" + id + " " + newUrl.toString());
    return repository.findById(id)
      .map(url -> {
        url.setTarget(newUrl.getTarget());
        url.setUsed(0);
        if (newUrl.getUser() != null)
          url.setUser(newUrl.getUser());
        return repository.save(url);
      })
      .orElseThrow(() -> new UrlNotFoundException(id));
  }

  @DeleteMapping("/urls/{id}")
  void deleteUrl(@PathVariable String id) {
    log.info("DEL /urls/" + id);
    repository.deleteById(id);
  }
}
