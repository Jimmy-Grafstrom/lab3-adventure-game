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
    void enterRoom_InputIsJa_PlayerIsDead() {
        ui.setInput("ja");
        mountainRoom.enterRoom(player, ui);
        assertEquals(0, player.getHealth());
        assertFalse(player.isAlive());
    }
}