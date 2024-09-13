package matsik.cassandrachat.user.repository;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import matsik.cassandrachat.user.model.User;

@Dao
public interface UserRepository {

    @Select
    User findByPrimaryKey(String username, String email);

    @Insert(ifNotExists = true)
    boolean save(User user);

    @Delete(entityClass = User.class)
    void deleteByPrimaryKey(String username, String email);
}
