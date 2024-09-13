package matsik.cassandrachat.mapper;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import matsik.cassandrachat.user.repository.UserByEmailRepository;
import matsik.cassandrachat.user.repository.UserByUsernameRepository;
import matsik.cassandrachat.user.repository.UserRepository;

@Mapper
public interface ChatMapper {
    @DaoFactory
    UserRepository userRepository();

    @DaoFactory
    UserByEmailRepository userByEmailRepository();

    @DaoFactory
    UserByUsernameRepository userByUsernameRepository();
}
