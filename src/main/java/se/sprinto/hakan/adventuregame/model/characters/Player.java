package se.sprinto.hakan.adventuregame.model.characters;

public class Player extends AbstractCharacter {

    /**
     * Lagt till en del metoder och variabler för att kontrollera när man vinner
     * och lite om t.ex butiksrummet
     */
    private boolean foundKey;
    private boolean defeatedEnemy;
    private boolean defeatedTrollkarl;
    private boolean openedChest;
    private boolean defeatedRiddare;
    private boolean GoodGuy;
    private boolean defeatedCashier;

    public Player(Builder builder) {
        super(builder.name, builder.health, builder.score, builder.strength);
    }

    public static class Builder {
        private String name;
        private int health;
        private int score;
        private int strength;
        public Builder() {
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder health (int health) {
            this.health = health;
            return this;
        }
        public Builder score(int score) {
            this.score = score;
            return this;
        }
        public Builder strength(int strength) {
            this.strength = strength;
            return this;
        }
        public Player build() {
            if(name == null || name.isBlank()) {
                throw new IllegalStateException("Name cannot be empty");
            }
            return new Player(this);
        }
    }
    public boolean hasFoundKey() {
        return foundKey;
    }
    public void setFoundKey(boolean foundKey) {
        this.foundKey = foundKey;
    }
    public boolean hasDefeatedEnemy() {
        return defeatedEnemy;
    }
    public void setDefeatedEnemy(boolean defeatedEnemy) {
        this.defeatedEnemy = defeatedEnemy;
    }
    public boolean hasDefeatedTrollkarl() {return defeatedTrollkarl;}
    public void setDefeatedTrollkarl(boolean defeatedTrollkarl) {
        this.defeatedTrollkarl = defeatedTrollkarl;
    }
    public boolean hasDefeatedRiddare() {
        return defeatedRiddare;
    }
    public void setDefeatedRiddare(boolean defeatedRiddare) {
        this.defeatedRiddare = defeatedRiddare;
    }
    public boolean hasOpenedChest() {
        return openedChest;
    }
    public void setOpenedChest(boolean openedChest) {
        this.openedChest = openedChest;
    }
    public boolean hasWon() {
        return foundKey && defeatedEnemy && defeatedTrollkarl && defeatedRiddare && openedChest;
    }
    public boolean isGoodGuy() {
        return GoodGuy;
    }
    public void setGoodGuy(boolean goodGuy) {
        GoodGuy = goodGuy;
    }
    public boolean hasDefeatedCashier() {
        return defeatedCashier;
    }
    public void setDefeatedCashier(boolean defeatedCashier) {
        this.defeatedCashier = defeatedCashier;
    }

    @Override
    public void attack(AbstractCharacter target) {
        target.setHealth(target.getHealth() - this.getStrength());

        if (!target.isAlive()) {
            addScore(50);
        }
    }
}