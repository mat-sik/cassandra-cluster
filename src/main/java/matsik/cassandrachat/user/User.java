package matsik.cassandrachat.user;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import com.datastax.oss.driver.api.mapper.entity.naming.GetterStyle;

import java.util.UUID;

@Entity
@PropertyStrategy(mutable = false, getterStyle = GetterStyle.FLUENT)
@CqlName("users")
public record User(
        @PartitionKey UUID id,
        String name,
        String surname
) {
}
