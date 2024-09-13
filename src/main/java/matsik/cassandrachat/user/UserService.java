package matsik.cassandrachat.user;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.DefaultBatchType;
import lombok.Value;
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

    // This method has potential to create data inconsistencies in the database if the application dies, before
    //  fully executing ensureUserSisterTable method.
    // To combat this, if system notices that the record with given username, email already exists, the system
    //  should try to run ensureUserSisterTables.
    // Now to make this consistent the request should be read from message queue and message should be consumed
    //  only if the operation was performed entirely.
    public void save(User user) {
        boolean inserted = userRepository.saveIfNotExists(user);
        if (!inserted) {
            var persistedUser = userRepository.findByPrimaryKey(user.getUsername(), user.getEmail());
            ensureUserSisterTables(persistedUser);
            throw new RuntimeException("The unique constraints of user has been violated");
        }
        ensureUserSisterTables(user);
    }

    private void ensureUserSisterTables(User user) {
        var userByUsername = new UserByUsername(user.getUsername(), user.getPassword(), user.getRole());
        BoundStatement createUserByUsername = userByUsernameRepository.save(userByUsername);

        var userByEmail = new UserByEmail(user.getEmail(), user.getPassword(), user.getRole());
        BoundStatement createUserByEmail = userByEmailRepository.save(userByEmail);

        BatchStatement batchStatement = BatchStatement.newInstance(
                DefaultBatchType.LOGGED,
                createUserByUsername,
                createUserByEmail
        );

        session.execute(batchStatement);
    }

}
