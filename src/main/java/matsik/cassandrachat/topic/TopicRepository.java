package matsik.cassandrachat.topic;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

import java.util.UUID;

@Dao
public interface TopicRepository {

    @Select
    Topic findByUserIdAndId(UUID userId, UUID id);

    @Insert
    void save(Topic topic);

    @Delete(entityClass = Topic.class)
    void delete(UUID userId, UUID id);
}
