package se.sprinto.hakan.adventuregame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sprinto.hakan.adventuregame.view.FakeUi;

import static org.junit.jupiter.api.Assertions.*;

class TreasureRoomTest {

    private Player testPlayer;
    private FakeUi ui;
    private TreasureRoom testRoom;

    @BeforeEach
    void setUp() {
        testPlayer = new Player.Builder()
                .name("testPlayer")
                .health(100)
                .score(0)
                .strength(100)
                .build();
        ui = new FakeUi();
        testRoom = new TreasureRoom();
    }

    @Test
    void enterRoom_opensChestWithKey() {
        // Arrange
        testPlayer.setFoundKey(true);
        ui.setInput("ja");

        // Act
        testRoom.enterRoom(testPlayer, ui);

        // Assert
        assertTrue(testPlayer.hasOpenedChest());
        assertEquals(100, testPlayer.getScore());
    }

    @Test
    void enterRoom_chestStaysLockedWithoutKey() {
        // Arrange
        ui.setInput("ja");

        // Act
        testRoom.enterRoom(testPlayer, ui);

        // Assert
        assertFalse(testPlayer.hasOpenedChest());
    }
}