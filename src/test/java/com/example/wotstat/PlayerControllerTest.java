package com.example.wotstat;

import com.example.wotstat.controller.PlayerController;
import com.example.wotstat.model.Player;
import com.example.wotstat.repository.PlayerRepository;
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

public class PlayerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    @Test
    public void testGetPlayers() throws Exception {
        Player player = new Player();
        when(playerRepository.findAll()).thenReturn(Arrays.asList(player));

        mockMvc.perform(get("/api/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());

        verify(playerRepository, times(1)).findAll();
    }

    @Test
    public void testSearchPlayers() throws Exception {
        Player player = new Player();
        when(playerRepository.findByNicknameContainingIgnoreCase("test")).thenReturn(Arrays.asList(player));

        mockMvc.perform(get("/api/players?search=test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());

        verify(playerRepository, times(1)).findByNicknameContainingIgnoreCase("test");
    }
}
