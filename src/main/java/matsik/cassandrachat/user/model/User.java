package matsik.cassandrachat.user.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Value;

@Entity
@PropertyStrategy(mutable = false)
@CqlName("users")
@Value
public class User {
    @PartitionKey
    String username;
    @PartitionKey(1)
    String email;
    String name;
    String surname;
}
