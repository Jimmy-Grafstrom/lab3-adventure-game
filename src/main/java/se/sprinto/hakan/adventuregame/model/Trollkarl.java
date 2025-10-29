package se.sprinto.hakan.adventuregame.model;

public class Trollkarl extends AbstractCharacter {
    public Trollkarl(String name, int health, int score, int strength) {
        super(name, health, score, strength);
    }

    @Override
    public void attack(AbstractCharacter target) {
        target.setHealth(target.getHealth() - this.getStrength());
    }
}

