package se.sprinto.hakan.adventuregame.model.rooms;

import se.sprinto.hakan.adventuregame.model.characters.Player;
import se.sprinto.hakan.adventuregame.model.characters.Riddare;
import se.sprinto.hakan.adventuregame.view.UI;

public class IthsRoom implements Room {
    private int timesStudied;
    private final Riddare riddare = new Riddare("Riddaren", 100, 0, 20);

    @Override
    public void enterRoom(Player player, UI ui) {
        if (player.hasDefeatedRiddare()) {
            ui.showMessage("Du går in i plugget.");
            String choice = ui.getInput("""
                    vad vill du göra?
                    1: spela pingis
                    2: Öva på att programmera""");
            if (choice.equals("1")) {
                ui.showMessage("Du spelar pingis och får en pingisboll i ansiktet");
                player.setHealth(player.getHealth() - 10);
                ui.showMessage("Du förlorar 10 HP, din HP: " + player.getHealth());
                ui.showMessage(player.loseScoreInfo(10));

            } else if (choice.equals("2")) {
                boolean studying = true;
                while (studying) {
                    if (timesStudied >= 3) {
                        ui.showMessage("Du har övat för mycket och glömt äta, så du svälter ihjäl");
                        player.setHealth(0);
                        return;
                    }
                    player.setHealth(player.getHealth() + 50);
                    player.setStrength(player.getStrength() + 50);
                    ui.showMessage("Du övar på att programmera och får extra 50 HP och 50 styrka HP: " + player.getHealth() + ", Styrka: " + player.getStrength());
                    timesStudied++;
                    String keepStudying = ui.getInput("Fortsätta öva på att programmera? ja/nej");
                    if (!keepStudying.equalsIgnoreCase("ja")) {
                        studying = false;
                        ui.showMessage("Du lämnar skolan");
                    }
                }
            }
        } else {
            stridMedRiddaren(player, ui);
        }
    }

    /**
     * Hjälpmetod för att strida med riddaren
     */
    private void stridMedRiddaren(Player player, UI ui) {
        ui.showMessage("En riddare står i vägen! Du måste besegra honom för att komma in.");
        while (player.isAlive() && riddare.isAlive()) {
            String choice = ui.getInput("Vill du (a)ttackera eller (r)etirera?");
            if (choice.equalsIgnoreCase("a")) {
                player.attack(riddare);
                ui.showMessage("Du attackerar riddaren! Riddarens HP: " + riddare.getHealth());
                if (riddare.isAlive()) {
                    riddare.attack(player);
                    ui.showMessage("Riddaren attackerar dig! Ditt HP: " + player.getHealth());
                } else {
                    ui.showMessage("Du besegrade riddaren!");
                    player.setDefeatedRiddare(true);
                    enterRoom(player, ui);
                }
            } else if (choice.equalsIgnoreCase("r")) {
                ui.showMessage("Du springer därifrån!");
                break;
            }
        }
    }
}
