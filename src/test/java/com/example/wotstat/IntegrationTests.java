package com.example.wotstat;

import com.example.wotstat.model.Clan;
import com.example.wotstat.model.Player;
import com.example.wotstat.repository.ClanRepository;
import com.example.wotstat.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClanRepository clanRepository;

    @MockBean
    private PlayerRepository playerRepository;

    private Clan testClan;
    private Player testPlayer;

    @BeforeEach
    public void setup() {
        testClan = new Clan();
        testClan.setClanName("TestClan");
        testClan.setTotalMembers(10);
        testClan.setAverageBattles(20);
        testClan.setWinRate(50.0f);
        testClan.setClanRating(100.0f);
        testClan.setAverageExp(200.0f);
        testClan.setAverageDamage(300.0f);

        testPlayer = new Player();
        testPlayer.setNickname("TestPlayer");
        testPlayer.setClanName("TEST");
        testPlayer.setTotalBattles(100);
        testPlayer.setWinRate(60);
        testPlayer.setAverageExp(5000);
        testPlayer.setAverageDamage(7000);

        Mockito.when(clanRepository.findByClanNameContainingIgnoreCase("TestClan")).thenReturn(List.of(testClan));
        Mockito.when(clanRepository.findByClanNameContainingIgnoreCase("NonExistentClan")).thenReturn(Collections.emptyList());

        Mockito.when(playerRepository.findByNicknameContainingIgnoreCase("TestPlayer")).thenReturn(List.of(testPlayer));
        Mockito.when(playerRepository.findByNicknameContainingIgnoreCase("NonExistentPlayer")).thenReturn(Collections.emptyList());
    }

    // User Story 1
    @Test
    @WithMockUser(username = "toadkillergamer@gmail.com", roles = "USER")
    public void testPlayerStatisticsFound() throws Exception {
        mockMvc.perform(get("/api/players")
                        .param("search", "TestPlayer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nickname").value("TestPlayer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalBattles").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].winRate").value(60))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].averageExp").value(5000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].averageDamage").value(7000));
    }

    @Test
    @WithMockUser(username = "toadkillergamer@gmail.com", roles = "USER")
    public void testPlayerStatisticsNotFound() throws Exception {
        mockMvc.perform(get("/api/players")
                        .param("search", "NonExistentPlayer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    // User Story 2
    @Test
    @WithMockUser(username = "toadkillergamer@gmail.com", roles = "USER")
    public void testClanStatisticsFound() throws Exception {
        mockMvc.perform(get("/api/clans")
                        .param("search", "TestClan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].clanName").value("TestClan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalMembers").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].averageBattles").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].winRate").value(50.0f))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].clanRating").value(100.0f))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].averageExp").value(200.0f))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].averageDamage").value(300.0f));
    }

    @Test
    @WithMockUser(username = "toadkillergamer@gmail.com", roles = "USER")
    public void testClanStatisticsNotFound() throws Exception {
        mockMvc.perform(get("/api/clans")
                        .param("search", "NonExistentClan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }
}
