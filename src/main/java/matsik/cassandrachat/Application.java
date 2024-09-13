package matsik.cassandrachat;

import matsik.cassandrachat.user.UserService;
import matsik.cassandrachat.user.dtos.SaveUserRequest;
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
            UserService userService
    ) {
        return _ -> {
            var saveUserRequest = new SaveUserRequest(
                    "username_1",
                    "email_1@mail.com",
                    "name_1",
                    "surename_1",
                    "password_1",
                    "admin"
            );
            userService.save(saveUserRequest);
        };
    }

}
