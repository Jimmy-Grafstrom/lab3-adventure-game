package se.sprinto.hakan.adventuregame.model;

import se.sprinto.hakan.adventuregame.view.UI;

public class MountainRoom implements Room{
    @Override
    public void enterRoom(Player player, UI ui) {
        ui.showMessage("Du står framför stora branta berg. Det ser livsfarligt ut.");
        ui.showMessage("Du borde nog vända om...");
        String choice = ui.getInput("Vill du klättra i bergen ja/nej");
        if (choice.equalsIgnoreCase("ja")) {
            ui.showMessage("Du börjar klättra, men du faller ner för ett stup");
            player.setHealth(0);
        } else {
            ui.showMessage("Klokt att vända om.");
        }
    }
}
