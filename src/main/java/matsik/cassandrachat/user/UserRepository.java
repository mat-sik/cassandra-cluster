package matsik.cassandrachat.user;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface UserRepository {

    @Select
    User findByEmail(String email);

    @Insert(ifNotExists = true)
    boolean saveIfNotExists(User user);

    @Delete(entityClass = User.class)
    BoundStatement deleteByEmail(String email);
}
