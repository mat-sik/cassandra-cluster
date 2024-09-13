package matsik.cassandrachat.message;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

import java.util.UUID;

@Dao
public interface MessageRepository {

    @Insert
    void save(Message message);

    @Select(customWhereClause = "topic_id = :topicId AND added_year = :addedYear AND added_month = :addedMonth", limit = ":l")
    PagingIterable<Message> findTopByPrimaryKey(
            UUID topicId,
            int addedYear,
            int addedMonth,
            @CqlName("l") int limit
    );

    @Select(customWhereClause = "topic_id = :topicId AND added_year = :addedYear AND added_month = :addedMonth AND id < :id", limit = ":l")
    PagingIterable<Message> findNextByPrimaryKey(
            UUID topicId,
            int addedYear,
            int addedMonth,
            UUID id,
            @CqlName("l") int limit
    );

    @Select
    Message findByPrimaryKey(UUID topicId, int addedYear, int addedMonth, UUID id);

    @Delete(entityClass = Message.class)
    void deleteByPrimaryKey(UUID topic_id, int added_year, int added_month, UUID id);
}
