package pro.sky.telegrambot.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Person;

public interface Repository extends JpaRepository<Person, Long>{

}
