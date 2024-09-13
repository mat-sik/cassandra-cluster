package matsik.cassandrachat;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import matsik.cassandrachat.message.Message;
import matsik.cassandrachat.message.MessageRepository;
import matsik.cassandrachat.topic.Topic;
import matsik.cassandrachat.topic.TopicRepository;
import matsik.cassandrachat.user.User;
import matsik.cassandrachat.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            TopicRepository topicRepository,
            MessageRepository messageRepository) {
        return _ -> {
//            insertData(userRepository, topicRepository, messageRepository);
            UUID topicId = UUID.fromString("9f5edfb0-71e1-11ef-bcc9-ed61c2074bc3");
            UUID messageId = UUID.fromString("a1410d30-71e1-11ef-bcc9-ed61c2074bc3");
            int addedYear = 2024;
            int addedMonth = 9;
            PagingIterable<Message> iterable = messageRepository.findNextByPrimaryKey(
                    topicId,
                    addedYear,
                    addedMonth,
                    messageId,
                    10
            );
            System.out.println("NEXT:");
            List<Message> messages = iterable.all();
            messages.forEach(System.out::println);
            System.out.println("TOP:");
            iterable = messageRepository.findTopByPrimaryKey(topicId, addedYear, addedMonth, 10);
            iterable.all().forEach(System.out::println);
            System.out.println("EXACT:");
            System.out.println(messageRepository.findByPrimaryKey(topicId, addedYear, addedMonth, messageId));
        };
    }

    private void insertData(
            UserRepository userRepository,
            TopicRepository topicRepository,
            MessageRepository messageRepository
    ) {
        UUID userId = Uuids.timeBased();
        var user = new User(
                "username_1",
                userId,
                "password_1",
                "admin",
                "name_1",
                "surname_1"
        );
        userRepository.saveIfNotExists(user);

        UUID topicId = Uuids.timeBased();
        var topic = new Topic(
                userId,
                topicId,
                null,
                "new_topic_1"
        );
        topicRepository.save(topic);

        int currentYear = 2024;
        int currentMonth = 9;
        for (int i = 0; i < 100; i++) {
            var message = new Message(
                    topicId,
                    currentYear,
                    currentMonth,
                    Uuids.timeBased(),
                    Instant.now(),
                    "message_" + i
            );
            messageRepository.save(message);
        }
    }

}
