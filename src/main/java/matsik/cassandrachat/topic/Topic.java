package matsik.cassandrachat.topic;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Entity
@PropertyStrategy(mutable = false)
@CqlName("topics_by_user_id")
@Value
public class Topic {
    @ClusteringColumn(1)
    UUID id;
    @PartitionKey
    UUID user_id;
    Set<UUID> user_ids;
    String name;
}
