package matsik.cassandrachat.user.repository;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import matsik.cassandrachat.user.model.UserByEmail;

@Dao
public interface UserByEmailRepository {

    @Select
    UserByEmail findByEmail(String email);

    @Insert
    BoundStatement save(UserByEmail user);

    @Delete(entityClass = UserByEmail.class)
    BoundStatement deleteByEmail(String email);
}
