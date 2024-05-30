package com.example.wotstat;

import com.example.wotstat.model.Clan;
import com.example.wotstat.repository.ClanRepository;
import com.example.wotstat.service.ClanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClanServiceTest {

    @Mock
    private ClanRepository clanRepository;

    @InjectMocks
    private ClanService clanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllClans() {
        Clan clan1 = new Clan();
        Clan clan2 = new Clan();
        when(clanRepository.findAll()).thenReturn(Arrays.asList(clan1, clan2));

        List<Clan> clans = clanService.getAllClans();
        assertEquals(2, clans.size());
        verify(clanRepository, times(1)).findAll();
    }

    @Test
    public void testSearchClansByName() {
        Clan clan = new Clan();
        when(clanRepository.findByClanNameContainingIgnoreCase("test")).thenReturn(Arrays.asList(clan));

        List<Clan> clans = clanService.searchClansByName("test");
        assertEquals(1, clans.size());
        verify(clanRepository, times(1)).findByClanNameContainingIgnoreCase("test");
    }

    @Test
    public void testGetClanByName() {
        Clan clan = new Clan();
        when(clanRepository.findByClanNameContainingIgnoreCase("test")).thenReturn(Arrays.asList(clan));

        Clan foundClan = clanService.getClanByName("test");
        assertNotNull(foundClan);
        verify(clanRepository, times(1)).findByClanNameContainingIgnoreCase("test");
    }

    @Test
    public void testSaveClan() {
        Clan clan = new Clan();
        clanService.saveClan(clan);
        verify(clanRepository, times(1)).save(clan);
    }

    @Test
    public void testDeleteClan() {
        clanService.deleteClan(1L);
        verify(clanRepository, times(1)).deleteById(1L);
    }
}
