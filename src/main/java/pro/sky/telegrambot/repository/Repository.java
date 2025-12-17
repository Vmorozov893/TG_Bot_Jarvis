package pro.sky.telegrambot.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.telegrambot.model.Person;

import java.util.Optional;

public interface Repository extends JpaRepository<Person, Long>{
    Optional<Person> findByChatId(long chatId);
    Optional<Person> findByKey(Integer key);
    @Modifying
    @Transactional              // обязательно: модифицирующий запрос должен выполняться в транзакции
    @Query("UPDATE Person u SET u.friendChatId = :friendChatId WHERE u.key = :key")
    int updateFriendChatIdByKey(@Param("key") Integer key, @Param("friendChatId") long friendChatId);
    @Modifying
    @Transactional              // обязательно: модифицирующий запрос должен выполняться в транзакции
    @Query("UPDATE Person u SET u.friendChatId = 0 WHERE u.chatId = :chatId")
    int deleteFriend(@Param("chatId") long chatId);
}
