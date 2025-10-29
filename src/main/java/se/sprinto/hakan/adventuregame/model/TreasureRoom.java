package se.sprinto.hakan.adventuregame.model;

import se.sprinto.hakan.adventuregame.view.UI;

public class TreasureRoom implements Room {

    private Riddare riddare = new Riddare("Riddaren", 100, 0, 20);
    @Override
    public void enterRoom(Player player, UI ui) {

        if (player.hasDefeatedRiddare()) {
            ui.showMessage("Riddaren är besegrad och du hittar en skattkista. Den verkar låst...");

            String choice = ui.getInput("Vill du försöka öppna kistan? (ja/nej)");
            if (choice.equalsIgnoreCase("ja")) {
                if (player.hasFoundKey()) {
                    ui.showMessage("Du öppnar kistan med din nyckel!");
                    player.setOpenedChest(true);
                    player.addScore(100);
                } else {
                    ui.showMessage("Kistan är låst, du saknar nyckeln...");
                }
            } else {
                ui.showMessage("Du lämnar kistan orörd.");
            }
        } else {
            stridMedRiddaren(player, ui);
        }
    }

    private void stridMedRiddaren(Player player, UI ui) {
        ui.showMessage("Du har visat dig värdig att slåss mot riddaren.");
        while (player.isAlive() && riddare.isAlive()) {
            String choice = ui.getInput("Vill du (a)ttackera eller (r)etirera?");
            if (choice.equalsIgnoreCase("a")) {
                player.attack(riddare);
                ui.showMessage("Du attackerar riddaren! Riddaren HP: " + riddare.getHealth());
                if (riddare.isAlive()) {
                    riddare.attack(player);
                    ui.showMessage("Riddaren attackerar dig! Ditt HP: " + player.getHealth());
                } else {
                    ui.showMessage("Du besegrade riddaren!");
                    player.setDefeatedRiddare(true);
                    enterRoom(player,ui);
                }
            } else if (choice.equalsIgnoreCase("r")) {
                ui.showMessage("Du springer därifrån!");
                break;
            }
        }
    }
}
