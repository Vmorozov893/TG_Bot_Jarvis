package pro.sky.telegrambot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JoinColumn(name = "Key")
    private Integer key;

    //@JoinColumn(name = "ChatId")
    private long chatId;

    //@JoinColumn(name = "FriendChatId")
    private long friendChatId;

    public Person(Integer key, long chatId, long friendChatId) {
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
