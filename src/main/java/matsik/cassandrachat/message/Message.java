package matsik.cassandrachat.message;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Entity
@PropertyStrategy(mutable = false)
@CqlName("messages_by_topic_id_year_month")
@Value
public class Message {
    @PartitionKey
    UUID topic_id;
    @PartitionKey(1)
    int added_year;
    @PartitionKey(2)
    int added_month;
    @ClusteringColumn(3)
    UUID id;
    Instant added_time;
    String content;
}
