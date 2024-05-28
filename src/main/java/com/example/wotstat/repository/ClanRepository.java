package com.example.wotstat.repository;

import com.example.wotstat.model.Clan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClanRepository extends JpaRepository<Clan, Long> {
    List<Clan> findByClanNameContainingIgnoreCase(String clanName);
}
