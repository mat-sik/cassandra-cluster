package matsik.cassandrachat.client;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.List;

@Configuration
public class CassandraClient {

    @Bean
    public CqlSession cqlSession(CassandraClientProperties properties) {
        List<InetSocketAddress> contactPoints = getContactPoints(properties.contactPoints());
        String localDatacenter = properties.localDatacenter();

        return CqlSession.builder()
                .addContactPoints(contactPoints)
                .withLocalDatacenter(localDatacenter)
                .withKeyspace(properties.keyspace())
                .build();
    }

    private List<InetSocketAddress> getContactPoints(List<String> contactPointsStrings) {
        return contactPointsStrings.stream()
                .map(contactPointString -> {
                    String[] contactPointStringSegments = contactPointString.split(":");
                    if (contactPointStringSegments.length != 2) {
                        throw new RuntimeException("Invalid contactPoint format in application.yaml");
                    }

                    String host = contactPointStringSegments[0];
                    int port = Integer.parseInt(contactPointStringSegments[1]);

                    return new InetSocketAddress(host, port);
                })
                .toList();
    }

}
