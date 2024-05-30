package com.example.wotstat.repository;

import com.example.wotstat.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByNicknameContainingIgnoreCase(String nickname); // підкапотна магія
}
