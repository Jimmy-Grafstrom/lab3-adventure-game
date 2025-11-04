package se.sprinto.hakan.adventuregame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sprinto.hakan.adventuregame.view.FakeUi;

import static org.junit.jupiter.api.Assertions.*;

class MountainRoomTest {

    private MountainRoom mountainRoom;
    private Player player;
    private FakeUi ui;

    @BeforeEach
    void setUp() {
        player = new Player.Builder()
                .name("testPlayer")
                .health(100)
                .score(0)
                .strength(10)
                .build();
        mountainRoom = new MountainRoom();
        ui = new FakeUi();
    }

    @Test
    void enterRoom_climbAndTalkToWizard_getsHealed() {
        // Arrange
        ui.setInput("ja");

        // Act
        mountainRoom.enterRoom(player, ui);

        // Assert
        assertEquals(200, player.getHealth());
    }
//TODO mock

//    @Test
//    void enterRoom_climbAndFightWizard_playerWins() {
//        // Arrange
//        player.setHealth(200);
//        ui.setInputs("ja", "ja", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"); // Ja to climb, ja to talk, then attack until wizard is defeated
//
//        // Act
//        mountainRoom.enterRoom(player, ui);
//
//        // Assert
//        assertTrue(player.hasDefeatedTrollkarl());
//        assertTrue(player.isAlive());
//    }

}