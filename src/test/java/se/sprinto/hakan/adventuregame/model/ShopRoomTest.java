package se.sprinto.hakan.adventuregame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.adventuregame.view.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShopRoomTest {

    private ShopRoom shopRoom;
    private Player player;
    @Mock
    private UI ui;


    @BeforeEach
    void setUp() {
        player = new Player.Builder()
                .name("testPlayer")
                .health(100)
                .score(0)
                .strength(10)
                .build();
        shopRoom = new ShopRoom();
    }

    @Test
    void enterRoom() {
        when(ui.getInput(anyString()))
                .thenReturn("nej")
                .thenReturn("1");

        shopRoom.enterRoom(player, ui);

        assertEquals(200, player.getHealth(), "Health is 200");
        assertTrue(player.isGoodGuy(), "Is good guy");
        verify(ui, times(2)).getInput(Mockito.anyString());
    }
}