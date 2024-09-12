package matsik.cassandrachat.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "cassandra")
public record CassandraClientProperties(String localDatacenter, List<String> contactPoints, String keyspace) {
}
