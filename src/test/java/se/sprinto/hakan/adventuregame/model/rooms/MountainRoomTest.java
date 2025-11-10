package se.sprinto.hakan.adventuregame.model.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.adventuregame.model.characters.Player;
import se.sprinto.hakan.adventuregame.view.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
/**
 * Använder Mock för att hantera flera olika inputs
 * och verify för att kontrollera användning av mockUi
 */
@ExtendWith(MockitoExtension.class)
class MountainRoomTest {

    private MountainRoom mountainRoom;
    private Player player;
    @Mock
    private UI mockUi;

    @BeforeEach
    void setUp() {
        player = new Player.Builder()
                .name("testPlayer")
                .health(100)
                .score(0)
                .strength(100)
                .build();
        mountainRoom = new MountainRoom();
    }

    @Test
    void enterRoom_climbAndFightWizard_playerWins() {
        when(mockUi.getInput(anyString())).thenReturn("ja", "ja", "a");
        player.setHealth(200);

        mountainRoom.enterRoom(player, mockUi);

        assertTrue(player.hasDefeatedTrollkarl());
        assertTrue(player.isAlive());
        verify(mockUi, times(3)).getInput(anyString());
    }
}