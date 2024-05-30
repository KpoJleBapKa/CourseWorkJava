package com.example.wotstat.service;

import com.example.wotstat.model.Clan;
import com.example.wotstat.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClanService {

    @Autowired
    private ClanRepository clanRepository;

    public List<Clan> getAllClans() {
        return clanRepository.findAll();
    }

    public List<Clan> searchClansByName(String clanName) {
        return clanRepository.findByClanNameContainingIgnoreCase(clanName);
    }

    public Clan getClanByName(String clanName) {
        List<Clan> clans = clanRepository.findByClanNameContainingIgnoreCase(clanName);
        return clans.isEmpty() ? null : clans.get(0);
    }

    public void saveClan(Clan clan) {
        clanRepository.save(clan);
    }

    public void deleteClan(Long id) {
        clanRepository.deleteById(id);
    }
}
