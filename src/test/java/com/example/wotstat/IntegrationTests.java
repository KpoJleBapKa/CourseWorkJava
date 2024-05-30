//package com.example.wotstat;
//
//import com.example.wotstat.model.Clan;
//import com.example.wotstat.model.Player;
//import com.example.wotstat.repository.ClanRepository;
//import com.example.wotstat.repository.PlayerRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class IntegrationTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private PlayerRepository playerRepository;
//
//    @Autowired
//    private ClanRepository clanRepository;
//
//    @BeforeEach
//    void setUp() {
//        playerRepository.deleteAll();
//        clanRepository.deleteAll();
//    }
//
//    @Test
//    public void testGetPlayerStatistics_Success() throws Exception {
//        Clan clan = new Clan();
//        clan.setClanName("Test Clan");
//        clan.setTotalMembers(10);
//        clan.setAverageBattles(200);
//        clan.setWinRate(60.0f);
//        clan.setClanRating(1500.0f);
//        clan.setAverageExp(500.0f);
//        clan.setAverageDamage(2000.0f);
//        clanRepository.save(clan);
//
//        Player player = new Player();
//        player.setNickname("existingPlayer");
//        player.setTotalBattles(100);
//        player.setWinRate(60.0f);
//        player.setAverageDamage(1500.0f);
//        player.setAverageExp(500.0f);
//        playerRepository.save(player);
//
//        mockMvc.perform(get("/api/players/statistics")
//                        .param("nickname", "existingPlayer"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.nickname").value("existingPlayer"))
//                .andExpect(jsonPath("$.clanName").value("Test Clan"))
//                .andExpect(jsonPath("$.totalBattles").value(100))
//                .andExpect(jsonPath("$.winRate").value(60.0))
//                .andExpect(jsonPath("$.averageDamage").value(1500.0))
//                .andExpect(jsonPath("$.averageExp").value(500.0));
//    }
//
//    @Test
//    public void testGetPlayerStatistics_PlayerNotFound() throws Exception {
//        mockMvc.perform(get("/api/players/statistics")
//                        .param("nickname", "nonExistingPlayer"))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message").value("Гравця не знайдено"));
//    }
//
//    @Test
//    public void testGetClanStatistics_Success() throws Exception {
//        Clan clan = new Clan();
//        clan.setClanName("existingClan");
//        clan.setTotalMembers(50);
//        clan.setAverageBattles(200);
//        clan.setWinRate(60.0f);
//        clan.setClanRating(1500.0f);
//        clan.setAverageExp(500.0f);
//        clan.setAverageDamage(2000.0f);
//        clanRepository.save(clan);
//
//        mockMvc.perform(get("/api/clans/statistics")
//                        .param("clanName", "existingClan"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.clanName").value("existingClan"))
//                .andExpect(jsonPath("$.totalMembers").value(50))
//                .andExpect(jsonPath("$.averageBattles").value(200))
//                .andExpect(jsonPath("$.winRate").value(60.0))
//                .andExpect(jsonPath("$.clanRating").value(1500.0))
//                .andExpect(jsonPath("$.averageExp").value(500.0))
//                .andExpect(jsonPath("$.averageDamage").value(2000.0));
//    }
//
//    @Test
//    public void testGetClanStatistics_ClanNotFound() throws Exception {
//        mockMvc.perform(get("/api/clans/statistics")
//                        .param("clanName", "nonExistingClan"))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message").value("Клан не знайдено"));
//    }
//
//    @Test
//    public void testCalculateBattles_Success() throws Exception {
//        mockMvc.perform(get("/api/battles/calculate")
//                        .param("menuOption", "1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.menuOption").value("1"))
//                .andExpect(jsonPath("$.totalBattles").value(100))
//                .andExpect(jsonPath("$.wins").value(60))
//                .andExpect(jsonPath("$.losses").value(40));
//    }
//
//    @Test
//    public void testCalculateBattles_InvalidMenuOption() throws Exception {
//        mockMvc.perform(get("/api/battles/calculate")
//                        .param("menuOption", "invalidOption"))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").value("Невірний пункт меню"));
//    }
//}
