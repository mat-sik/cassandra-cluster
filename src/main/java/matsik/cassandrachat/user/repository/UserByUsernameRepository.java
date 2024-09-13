package matsik.cassandrachat.user.repository;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import matsik.cassandrachat.user.model.UserByUsername;

@Dao
public interface UserByUsernameRepository {

    @Select
    UserByUsername findByUsername(String username);

    @Insert
    BoundStatement save(UserByUsername user);

    @Delete(entityClass = UserByUsername.class)
    BoundStatement deleteByUsername(String username);
}
