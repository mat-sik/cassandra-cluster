package matsik.cassandrachat.message;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;

import java.util.UUID;

@Dao
public interface MessageRepository {

    @Insert
    void save(Message message);

    @Delete(entityClass = Message.class)
    void deleteByPrimaryKey(UUID topic_id, int added_year, int added_month, UUID id);
}
