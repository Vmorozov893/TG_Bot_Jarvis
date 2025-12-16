package pro.sky.telegrambot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer key;

    private long chatId;

    private long friendChatId;

    public Person(Long id, Integer key, long chatId, long friendChatId) {
        this.id = id;
        this.key = key;
        this.chatId = chatId;
        this.friendChatId = friendChatId;
    }

    public Person() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public void setFriendChatId(long friendChatId) {
        this.friendChatId = friendChatId;
    }

    public Long getId() {
        return id;
    }

    public Integer getKey() {
        return key;
    }

    public long getChatId() {
        return chatId;
    }

    public long getFriendChatId() {
        return friendChatId;
    }
}
