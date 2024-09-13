package matsik.cassandrachat.mapper;

import com.datastax.oss.driver.api.core.CqlSession;
import matsik.cassandrachat.user.repository.UserByEmailRepository;
import matsik.cassandrachat.user.repository.UserByUsernameRepository;
import matsik.cassandrachat.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMapperConfiguration {

    @Bean
    public ChatMapper chatMapper(CqlSession session) {
        return new ChatMapperBuilder(session).build();
    }

    @Bean
    public UserRepository userRepository(ChatMapper mapper) {
        return mapper.userRepository();
    }

    @Bean
    public UserByEmailRepository userByEmailRepository(ChatMapper mapper) {
        return mapper.userByEmailRepository();
    }

    @Bean
    public UserByUsernameRepository userByUsernameRepository(ChatMapper mapper) {
        return mapper.userByUsernameRepository();
    }

}
