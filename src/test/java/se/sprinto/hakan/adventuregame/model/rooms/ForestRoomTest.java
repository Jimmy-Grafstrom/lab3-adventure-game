package se.sprinto.hakan.adventuregame.model.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sprinto.hakan.adventuregame.model.characters.Player;
import se.sprinto.hakan.adventuregame.view.FakeUi;

import static org.junit.jupiter.api.Assertions.*;

class ForestRoomTest {

    private Player testPlayer;
    private FakeUi ui;
    private ForestRoom testRoom;
    @BeforeEach
    void setUp() {
        testPlayer = new Player.Builder()
                .name("testPlayer")
                .health(100)
                .score(0)
                .strength(10)
                .build();
        ui = new FakeUi();
        testRoom = new ForestRoom();
    }

    @Test
    void enterRoom_FindsKeyAndAddScore() {
        ui.setInput("ja");

        testRoom.enterRoom(testPlayer, ui);

        assertTrue(testPlayer.hasFoundKey());
        assertEquals(20, testPlayer.getScore());
    }
}