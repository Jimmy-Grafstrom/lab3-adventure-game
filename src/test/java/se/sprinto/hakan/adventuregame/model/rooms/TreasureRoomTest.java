package se.sprinto.hakan.adventuregame.model.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sprinto.hakan.adventuregame.model.characters.Player;
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
        testRoom = new TreasureRoom();
        ui = new FakeUi();
    }

    @Test
    void enterRoom_HasOpenedChest_IsTrue() {
        ui.setInput("ja");
        testPlayer.setFoundKey(true);
        testRoom.enterRoom(testPlayer, ui);
        assertTrue(testPlayer.hasOpenedChest());
    }
}