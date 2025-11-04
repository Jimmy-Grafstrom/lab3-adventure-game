package se.sprinto.hakan.adventuregame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sprinto.hakan.adventuregame.view.FakeUi;

import static org.junit.jupiter.api.Assertions.*;

class IthsRoomTest {

    private IthsRoom ithsRoom;
    private Player player;
    private FakeUi ui;

    @BeforeEach
    void setUp() {
        player = new Player.Builder()
                .name("testPlayer")
                .health(100)
                .score(10)
                .strength(10)
                .build();
        ithsRoom = new IthsRoom();
        ui = new FakeUi();
    }
//TODO mock

//    @Test
//    void enterRoom_playerDefeatsKnightAndCanStudy() {
//        // Arrange
//        player.setStrength(100); // Make player strong enough to defeat the knight
//        ui.setInputs("a", "a", "a", "a", "a", "2", "nej"); // Attack knight, then study
//
//        // Act
//        ithsRoom.enterRoom(player, ui);
//
//        // Assert
//        assertTrue(player.hasDefeatedRiddare());
//        assertEquals(150, player.getHealth()); // 100 (start) - damage + 50 (study)
//    }

    @Test
    void enterRoom_playerTriesToEnterWithoutDefeatingKnight() {
        // Arrange
        ui.setInput("r"); // Retreat from knight

        // Act
        ithsRoom.enterRoom(player, ui);

        // Assert
        assertFalse(player.hasDefeatedRiddare());
    }
}