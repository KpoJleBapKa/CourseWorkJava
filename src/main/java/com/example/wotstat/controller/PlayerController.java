package com.example.wotstat.controller;

import com.example.wotstat.model.Player;
import com.example.wotstat.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/players")
    public List<Player> getPlayers(@RequestParam(required = false) String search) {
        if (search == null || search.isEmpty()) {
            return playerRepository.findAll();
        } else {
            return playerRepository.findByNicknameContainingIgnoreCase(search);
        }
    }
}
