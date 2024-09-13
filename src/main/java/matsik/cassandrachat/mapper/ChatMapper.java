package matsik.cassandrachat.mapper;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import matsik.cassandrachat.user.UserRepository;

@Mapper
public interface ChatMapper {
    @DaoFactory
    UserRepository userRepository();
}
