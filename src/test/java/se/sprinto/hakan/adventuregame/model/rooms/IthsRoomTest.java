package se.sprinto.hakan.adventuregame.model.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.adventuregame.model.characters.Player;
import se.sprinto.hakan.adventuregame.view.UI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Använder Mock för att hantera flera olika inputs
 * och verify för att kontrollera användning av mockUi
 */
@ExtendWith(MockitoExtension.class)
class IthsRoomTest {

    private IthsRoom ithsRoom;
    private Player player;
    @Mock
    private UI mockUi;

    @BeforeEach
    void setUp() {
        player = new Player.Builder()
                .name("testPlayer")
                .health(100)
                .score(10)
                .strength(200)
                .build();
        ithsRoom = new IthsRoom();

    }

    @Test
    void enterRoom_playerDefeatsKnightAndCanStudy() {
        when(mockUi.getInput(anyString())).thenReturn("a", "2", "nej");

        ithsRoom.enterRoom(player, mockUi);

        assertTrue(player.hasDefeatedRiddare());
        assertEquals(150, player.getHealth());
        verify(mockUi, times(3)).getInput(anyString());
    }
}