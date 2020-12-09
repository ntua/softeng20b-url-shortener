package gr.ntua.ece.softeng20b.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gr.ntua.ece.softeng20b.backend.models.User;
import gr.ntua.ece.softeng20b.backend.models.UserRepository;

@Configuration
class LoadDatabase {
  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {
    return args -> {
      if (!repository.existsById(1)) {
        log.info("Preloading " + repository.save(new User("<anonymous>")));
      }
    };
  }
}
