package se.sprinto.hakan.adventuregame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sprinto.hakan.adventuregame.view.FakeUi;

import static org.junit.jupiter.api.Assertions.*;

class DungeonRoomTest {
    private Player testPlayer;
    private FakeUi ui;
    private DungeonRoom testRoom;

    @BeforeEach
    void setUp() {
        testPlayer = new Player.Builder()
                .name("testPlayer")
                .health(100)
                .score(0)
                .strength(10)
                .build();
        ui = new FakeUi();
        testRoom = new DungeonRoom();
    }

    @Test
    void enterRoom_WhenPlayerAttackEnemy_HasDefeatedEnemyIsTrue() {
        ui.setMultipleInputs("a", "a");
        testRoom.enterRoom(testPlayer, ui);
        assertTrue(testPlayer.hasDefeatedEnemy());
    }
}