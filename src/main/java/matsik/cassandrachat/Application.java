package matsik.cassandrachat;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import matsik.cassandrachat.user.User;
import matsik.cassandrachat.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository
    ) {
        return _ -> {
            var newUser = new User(
                    "username_1",
                    Uuids.timeBased(),
                    "name_1",
                    "surename_1",
                    "password_1",
                    "admin"
            );
            var inserted = userRepository.saveIfNotExists(newUser);
            System.out.println(inserted);
        };
    }

}
