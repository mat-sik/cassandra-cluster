package matsik.cassandrachat.user.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Value;

@Entity
@PropertyStrategy(mutable = false)
@CqlName("users_by_username")
@Value
public class UserByUsername {
    @PartitionKey
    String username;
    String password;
    String role;
}
