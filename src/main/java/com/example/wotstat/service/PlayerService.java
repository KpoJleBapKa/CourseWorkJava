package com.example.wotstat.service;

import com.example.wotstat.model.Player;
import com.example.wotstat.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> searchPlayersByNickname(String nickname) {
        return playerRepository.findByNicknameContainingIgnoreCase(nickname);
    }

    public Player getPlayerByNickname(String nickname) {
        List<Player> players = playerRepository.findByNicknameContainingIgnoreCase(nickname);
        return players.isEmpty() ? null : players.get(0);
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
