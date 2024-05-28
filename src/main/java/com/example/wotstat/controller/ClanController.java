package com.example.wotstat.controller;

import com.example.wotstat.model.Clan;
import com.example.wotstat.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClanController {

    @Autowired
    private ClanRepository clanRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/clans")
    public List<Clan> getClans(@RequestParam(required = false) String search) {
        if (search == null || search.isEmpty()) {
            return clanRepository.findAll();
        } else {
            return clanRepository.findByClanNameContainingIgnoreCase(search);
        }
    }
}
