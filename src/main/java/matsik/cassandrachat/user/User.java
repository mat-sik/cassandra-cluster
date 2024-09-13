package matsik.cassandrachat.user;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Value;

import java.util.UUID;

@Entity
@PropertyStrategy(mutable = false)
@CqlName("users")
@Value
public class User {
    @PartitionKey
    String email;
    UUID id;
    String password;
    String role;
    String name;
    String surname;
}
