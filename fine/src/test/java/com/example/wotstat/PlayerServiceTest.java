package com.example.wotstat;

import com.example.wotstat.model.Player;
import com.example.wotstat.repository.PlayerRepository;
import com.example.wotstat.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPlayers() {
        Player player1 = new Player();
        Player player2 = new Player();
        when(playerRepository.findAll()).thenReturn(Arrays.asList(player1, player2));

        List<Player> players = playerService.getAllPlayers();
        assertEquals(2, players.size());
        verify(playerRepository, times(1)).findAll();
    }

    @Test
    public void testSearchPlayersByNickname() {
        Player player = new Player();
        when(playerRepository.findByNicknameContainingIgnoreCase("test")).thenReturn(Arrays.asList(player));

        List<Player> players = playerService.searchPlayersByNickname("test");
        assertEquals(1, players.size());
        verify(playerRepository, times(1)).findByNicknameContainingIgnoreCase("test");
    }

    @Test
    public void testGetPlayerByNickname() {
        Player player = new Player();
        when(playerRepository.findByNicknameContainingIgnoreCase("test")).thenReturn(Arrays.asList(player));

        Player foundPlayer = playerService.getPlayerByNickname("test");
        assertNotNull(foundPlayer);
        verify(playerRepository, times(1)).findByNicknameContainingIgnoreCase("test");
    }

    @Test
    public void testSavePlayer() {
        Player player = new Player();
        playerService.savePlayer(player);
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    public void testDeletePlayer() {
        playerService.deletePlayer(1L);
        verify(playerRepository, times(1)).deleteById(1L);
    }
}
