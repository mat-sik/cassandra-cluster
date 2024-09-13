package matsik.cassandrachat.user;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

import java.util.UUID;

@Dao
public interface UserRepository {

    @Select
    User findById(UUID id);

    @Insert
    BoundStatement save(User user);

    @Delete(entityClass = User.class)
    BoundStatement deleteById(UUID id);
}
