package com.example.wotstat;

import com.example.wotstat.controller.ClanController;
import com.example.wotstat.model.Clan;
import com.example.wotstat.repository.ClanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClanControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClanRepository clanRepository;

    @InjectMocks
    private ClanController clanController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clanController).build();
    }

    @Test
    public void testGetClans() throws Exception {
        Clan clan = new Clan();
        when(clanRepository.findAll()).thenReturn(Arrays.asList(clan));

        mockMvc.perform(get("/api/clans")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());

        verify(clanRepository, times(1)).findAll();
    }

    @Test
    public void testSearchClans() throws Exception {
        Clan clan = new Clan();
        when(clanRepository.findByClanNameContainingIgnoreCase("test")).thenReturn(Arrays.asList(clan));

        mockMvc.perform(get("/api/clans?search=test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());

        verify(clanRepository, times(1)).findByClanNameContainingIgnoreCase("test");
    }
}
