package se.sprinto.hakan.adventuregame.model.characters;

public class Enemy extends AbstractCharacter {

    public Enemy(String name, int health, int score, int strength) {
        super(name, health, score, strength);
    }

    @Override
    public void attack(AbstractCharacter target) {
        target.setHealth(target.getHealth() - this.getStrength());
    }
}
