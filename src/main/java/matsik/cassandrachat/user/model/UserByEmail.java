package matsik.cassandrachat.user.model;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import com.datastax.oss.driver.api.mapper.entity.naming.GetterStyle;

import java.util.UUID;

@Entity
@PropertyStrategy(mutable = false, getterStyle = GetterStyle.FLUENT)
@CqlName("users_by_email")
public record UserByEmail(
        @PartitionKey String email,
        @ClusteringColumn(1) UUID id,
        String password,
        String role
) {
}
