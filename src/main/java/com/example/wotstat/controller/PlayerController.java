//package com.example.wotstat.controller;
//
//import com.example.wotstat.model.Player;
//import com.example.wotstat.repository.PlayerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class PlayerController {
//
//    @Autowired
//    private PlayerRepository playerRepository;
//
//    @CrossOrigin(origins = "http://localhost:8080")
//    @GetMapping("/api/players")
//    public List<Player> getPlayers(@RequestParam(required = false) String search) {
//        if (search == null || search.isEmpty()) {
//            return playerRepository.findAll();
//        } else {
//            return playerRepository.findByNicknameContainingIgnoreCase(search);
//        }
//    }
//}

package com.example.wotstat.controller;

import com.example.wotstat.model.Player;
import com.example.wotstat.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List<Player> getPlayers(@RequestParam(required = false) String search) {
        if (search == null || search.isEmpty()) {
            return playerRepository.findAll();
        } else {
            return playerRepository.findByNicknameContainingIgnoreCase(search);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setNickname(playerDetails.getNickname());
            player.setTotalBattles(playerDetails.getTotalBattles());
            player.setWinRate(playerDetails.getWinRate());
            player.setAverageDamage(playerDetails.getAverageDamage());
            player.setAverageExp(playerDetails.getAverageExp());
            player.setClanName(playerDetails.getClanName());
            return playerRepository.save(player);
        } else {
            throw new RuntimeException("Player not found with id " + id);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PatchMapping("/{id}")
    public Player partiallyUpdatePlayer(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nickname":
                        player.setNickname((String) value);
                        break;
                    case "totalBattles":
                        player.setTotalBattles((Integer) value);
                        break;
                    case "winRate":
                        player.setWinRate((Float) value);
                        break;
                    case "averageDamage":
                        player.setAverageDamage((Float) value);
                        break;
                    case "averageExp":
                        player.setAverageExp((Float) value);
                        break;
                    case "clanName":
                        player.setClanName((String) value);
                        break;
                }
            });
            return playerRepository.save(player);
        } else {
            throw new RuntimeException("Player not found with id " + id);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerRepository.deleteById(id);
    }
}
