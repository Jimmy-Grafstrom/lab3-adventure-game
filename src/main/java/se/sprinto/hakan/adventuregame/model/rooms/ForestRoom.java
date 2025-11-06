package se.sprinto.hakan.adventuregame.model.rooms;

import se.sprinto.hakan.adventuregame.model.characters.Player;
import se.sprinto.hakan.adventuregame.view.UI;

public class ForestRoom implements Room {

    @Override
    public void enterRoom(Player player, UI ui) {
        ui.showMessage("Du kliver in i en mörk skog.");
        if (player.hasFoundKey()) {
            ui.showMessage("Det finns inget att göra i skogen.");
        } else {
            ui.showMessage("Något glimmar i mossan...");
            notFoundKey(player, ui);
        }
    }

    /**
     * Hjälpmetod för att interagera med nyckeln
     */
    private void notFoundKey(Player player, UI ui) {
        String choice = ui.getInput("vill du undersöka glittret? (ja/nej)");
        if (choice.equalsIgnoreCase("ja")) {
            ui.showMessage("Du hittar en nyckel!");
            player.setFoundKey(true);
            player.addScore(20);
        } else {
            ui.showMessage("Du ignorerar glittret och går vidare.");
        }
    }
}

