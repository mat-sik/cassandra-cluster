package mat_sik.cassandra.chat;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.InetSocketAddress;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CqlSession cqlSession() {
        return CqlSession.builder()
                .addContactPoints(List.of(
                        new InetSocketAddress("localhost", 9042),
                        new InetSocketAddress("localhost", 9043),
                        new InetSocketAddress("localhost", 9044)
                ))
                .withLocalDatacenter("DC1")
                .build();
    }

    @Bean
    public CommandLineRunner commandLineRunner(CqlSession session) {
        return _ -> {
            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            System.out.println(row.getString("release_version"));
        };
    }

}
