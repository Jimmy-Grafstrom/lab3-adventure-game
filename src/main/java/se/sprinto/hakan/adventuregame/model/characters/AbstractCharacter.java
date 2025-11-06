package se.sprinto.hakan.adventuregame.model.characters;

public abstract class AbstractCharacter {
    private final String name;
    private int health;
    private int score;
    private int strength;

    public AbstractCharacter(String name, int health, int score, int strength) {
        this.name = name;
        this.health = health;
        this.score = score;
        this.strength = strength;
    }

    public abstract void attack(AbstractCharacter target);

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public int getScore() {
        return score;
    }

    public void addScore(int amount) {
        this.score += amount;
    }

    /**
     * Ser till att poängavdrag inte kan orsaka negativa poäng.
     * Detta är en hjälpmetod som används i loseScoreInfo där poängavdraget utförs.
     *
     * @param amount = antal poäng
     * @return true/false
     */
    private boolean loseScore(int amount) {
        return (this.score >= amount);
    }
    public String loseScoreInfo(int points) {
        if (loseScore(points)) {
            this.score -= points;
            return "Du förlorade " + points + " poäng\nScore: " + getScore();
        } else {
            return "Score: " + getScore();
        }
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isAlive() {
        return health > 0;
    }
}

