package se.sprinto.hakan.adventuregame.model.rooms;

import se.sprinto.hakan.adventuregame.model.characters.AbstractCharacter;
import se.sprinto.hakan.adventuregame.model.characters.Player;
import se.sprinto.hakan.adventuregame.model.characters.Trollkarl;
import se.sprinto.hakan.adventuregame.view.UI;

public class MountainRoom implements Room {

    private final AbstractCharacter trollkarl = new Trollkarl("Trollkarlen", 100, 0, 100);

    @Override
    public void enterRoom(Player player, UI ui) {
        ui.showMessage("Du står framför stora branta berg. Det ser livsfarligt ut.");
        if (player.hasDefeatedTrollkarl()) {
            ui.showMessage("Du har besegrat trollkarlen, det finns inget mer att göra här.");
            return;
        }
        ui.showMessage("Du borde nog vända om...");
        String choice = ui.getInput("Vill du klättra i bergen ja/nej");
        if (choice.equalsIgnoreCase("ja")) {
            ui.showMessage("Du börjar klättra, och du möter en trollkarl!");
            trollkarlIsAlive(player, ui);
        } else {
            ui.showMessage("Klokt att vända om.");
        }
    }

    /**
     * Hjälpmetod för interaktion med trollkarl,
     * healing eller ropar på stridmetod.
     */
    private void trollkarlIsAlive(Player player, UI ui) {
        String choice = ui.getInput("Vill du prata med trollkarlen? (ja/nej)");
        if (choice.equalsIgnoreCase("ja")) {
            if (player.getHealth() < 200) {
                player.setHealth(player.getHealth() + 100);
                ui.showMessage("Trollkarlen ger dig mer 100 mer health");
                ui.showMessage("Ditt HP: " + player.getHealth());
            } else {
                ui.showMessage("Du har redan blivit tillräckligt helad säger trollkarlen.");
                stridMedTrollkarlen(player, ui);
            }
        } else {
            ui.showMessage("Du ignorerar trollkarlen och går vidare.");
        }
    }

    private void stridMedTrollkarlen(Player player, UI ui) {
        trollkarl.attack(player);
        ui.showMessage("Trollkarlen attackerar dig! Ditt HP: " + player.getHealth());

        while (player.isAlive() && trollkarl.isAlive()) {
            String choice = ui.getInput("Vill du (a)ttackera eller (r)etirera?");
            if (choice.equalsIgnoreCase("a")) {
                player.attack(trollkarl);
                ui.showMessage("Du attackerar trollkarlen! Trollkarlens HP: " + trollkarl.getHealth());
                if (trollkarl.isAlive()) {
                    trollkarl.attack(player);
                    ui.showMessage("Trollkarlen attackerar dig! Ditt HP: " + player.getHealth());
                } else {
                    ui.showMessage("Du besegrade trollkarlen!");
                    player.setDefeatedTrollkarl(true);
                }
            } else if (choice.equalsIgnoreCase("r")) {
                ui.showMessage("Du springer därifrån!");
                break;
            }
        }
    }
}
