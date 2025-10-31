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

    @Test
    void enterRoom_WhenPlayPingis_LoosePointsAndHealth() {
        ui.setInput("1");
        ithsRoom.enterRoom(player, ui);
        assertEquals(0, player.getScore());
        assertEquals(90, player.getHealth());
    }

    @Test
    void enterRoom_WhenPlayPingis_DontLoosePointsIfLessThan10() {
        player = new Player.Builder()
                .name("testPlayer")
                .health(100)
                .score(5)
                .strength(10)
                .build();
        ui.setInput("1");
        ithsRoom.enterRoom(player, ui);
        assertEquals(5, player.getScore());
        assertEquals(90, player.getHealth());
    }
}