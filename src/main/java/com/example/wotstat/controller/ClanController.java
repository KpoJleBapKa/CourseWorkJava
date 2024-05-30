//package com.example.wotstat.controller;
//
//import com.example.wotstat.model.Clan;
//import com.example.wotstat.repository.ClanRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class ClanController {
//
//    @Autowired
//    private ClanRepository clanRepository;
//
//    @CrossOrigin(origins = "http://localhost:8080")
//    @GetMapping("/api/clans")
//    public List<Clan> getClans(@RequestParam(required = false) String search) {
//        if (search == null || search.isEmpty()) {
//            return clanRepository.findAll();
//        } else {
//            return clanRepository.findByClanNameContainingIgnoreCase(search);
//        }
//    }
//}

package com.example.wotstat.controller;

import com.example.wotstat.model.Clan;
import com.example.wotstat.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/clans")
public class ClanController {

    @Autowired
    private ClanRepository clanRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List<Clan> getClans(@RequestParam(required = false) String search) {
        if (search == null || search.isEmpty()) {
            return clanRepository.findAll();
        } else {
            return clanRepository.findByClanNameContainingIgnoreCase(search);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public Clan createClan(@RequestBody Clan clan) {
        return clanRepository.save(clan);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/{id}")
    public Clan updateClan(@PathVariable Long id, @RequestBody Clan clanDetails) {
        Optional<Clan> optionalClan = clanRepository.findById(id);
        if (optionalClan.isPresent()) {
            Clan clan = optionalClan.get();
            clan.setClanName(clanDetails.getClanName());
            clan.setTotalMembers(clanDetails.getTotalMembers());
            clan.setAverageBattles(clanDetails.getAverageBattles());
            clan.setWinRate(clanDetails.getWinRate());
            clan.setClanRating(clanDetails.getClanRating());
            clan.setAverageExp(clanDetails.getAverageExp());
            clan.setAverageDamage(clanDetails.getAverageDamage());
            return clanRepository.save(clan);
        } else {
            throw new RuntimeException("Clan not found with id " + id);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PatchMapping("/{id}")
    public Clan partiallyUpdateClan(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Clan> optionalClan = clanRepository.findById(id);
        if (optionalClan.isPresent()) {
            Clan clan = optionalClan.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "clanName":
                        clan.setClanName((String) value);
                        break;
                    case "totalMembers":
                        clan.setTotalMembers((Integer) value);
                        break;
                    case "averageBattles":
                        clan.setAverageBattles((Integer) value);
                        break;
                    case "winRate":
                        clan.setWinRate((Float) value);
                        break;
                    case "clanRating":
                        clan.setClanRating((Float) value);
                        break;
                    case "averageExp":
                        clan.setAverageExp((Float) value);
                        break;
                    case "averageDamage":
                        clan.setAverageDamage((Float) value);
                        break;
                }
            });
            return clanRepository.save(clan);
        } else {
            throw new RuntimeException("Clan not found with id " + id);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/{id}")
    public void deleteClan(@PathVariable Long id) {
        clanRepository.deleteById(id);
    }
}
