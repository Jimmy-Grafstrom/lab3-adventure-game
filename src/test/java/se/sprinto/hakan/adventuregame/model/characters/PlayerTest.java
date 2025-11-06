package se.sprinto.hakan.adventuregame.model.characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player testPlayer;
    private AbstractCharacter testEnemy;

    @BeforeEach
    void setUp() {
        testPlayer = new Player.Builder()
                .name("testPlayer")
                .health(100)
                .score(0)
                .strength(10)
                .build();
        testEnemy = new Enemy("testEnemy", 100,0, 10);
    }

    @Test
    void attack_WhenPlayerAttack_EnemyHealthMinusTen() {
        testPlayer.attack(testEnemy);

        assertEquals(90, testEnemy.getHealth());
    }
}