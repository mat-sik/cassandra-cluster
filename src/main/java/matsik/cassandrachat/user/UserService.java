package matsik.cassandrachat.user;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.DefaultBatchType;
import lombok.Value;
import matsik.cassandrachat.user.dtos.SaveUserRequest;
import matsik.cassandrachat.user.model.User;
import matsik.cassandrachat.user.model.UserByEmail;
import matsik.cassandrachat.user.model.UserByUsername;
import matsik.cassandrachat.user.repository.UserByEmailRepository;
import matsik.cassandrachat.user.repository.UserByUsernameRepository;
import matsik.cassandrachat.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Value
public class UserService {

    CqlSession session;

    UserRepository userRepository;
    UserByUsernameRepository userByUsernameRepository;
    UserByEmailRepository userByEmailRepository;

    // This is incorrect in case the application running this dies, before batch is executed.
    public void save(SaveUserRequest request) {
        var user = new User(request.username(), request.email(), request.name(), request.surname());
        boolean inserted = userRepository.saveIfNotExists(user);
        if (!inserted) {
            throw new RuntimeException("The unique constraints of user has been violated");
        }

        var userByUsername = new UserByUsername(request.username(), request.password(), request.role());
        BoundStatement createUserByUsername = userByUsernameRepository.save(userByUsername);

        var userByEmail = new UserByEmail(request.email(), request.password(), request.role());
        BoundStatement createUserByEmail = userByEmailRepository.save(userByEmail);

        BatchStatement batchStatement = BatchStatement.newInstance(
                DefaultBatchType.LOGGED,
                createUserByUsername,
                createUserByEmail
        );

        session.execute(batchStatement);
    }

}
