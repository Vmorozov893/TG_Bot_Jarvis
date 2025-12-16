package pro.sky.telegrambot.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Integer, Long>{

}
