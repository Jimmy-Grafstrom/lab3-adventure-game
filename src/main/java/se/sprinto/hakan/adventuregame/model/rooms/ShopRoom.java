package se.sprinto.hakan.adventuregame.model.rooms;

import se.sprinto.hakan.adventuregame.model.characters.Player;
import se.sprinto.hakan.adventuregame.view.UI;

public class ShopRoom implements Room {

    @Override
    public void enterRoom(Player player, UI ui) {
        if (player.hasDefeatedCashier()) {
            ui.showMessage("Butiken är stängd");
            return;
        }
        if (player.isGoodGuy()) { // False by default
            shop(player, ui);
        } else {
            String attackCashier = ui.getInput("Det sitter en expedit i kassan, vill du attackera? ja/nej");
            if (attackCashier.equalsIgnoreCase("ja")) {
                ui.showMessage("Du besegrade expediten med ett slag, nu kan du inte handla.");
                ui.showMessage("Expediten var ingen fiende...");
                ui.showMessage(player.loseScoreInfo(20));
                player.setDefeatedCashier(true);
            } else if (attackCashier.equalsIgnoreCase("nej")) {
                player.setGoodGuy(true); // Ändrar isGoodGuy till true
                ui.showMessage("Du har visat att du är en good guy och har fri tillgång till butiken.");
                shop(player, ui);
            } else {
                ui.showMessage("Du lämnar butiken");
            }
        }
    }

    /**
     * Shop interaktioner nedan.
     * Hantera affären och egenskaper hos produkter.
     */
    private void shop(Player player, UI ui) {
        ui.showMessage("Välkommen till butiken.");
        String choice = ui.getInput("""
                Vad vill du handla?
                1: Health potion
                2: Kaffe""");
        if (choice.equals("1")) {
            shopHealthPotion(player, ui);
        } else if (choice.equals("2")) {
            shopCoffee(player, ui);
        } else {
            ui.showMessage("Du lämnar butiken");
        }
    }

    private void shopHealthPotion(Player player, UI ui) {
        if (player.getHealth() < 300) {
            player.setHealth(player.getHealth() + 100);
            ui.showMessage("Du drack health potion och fick totalt " + player.getHealth() + " HP");
        } else {
            ui.showMessage("Health potion har ingen effekt över 300 HP");
        }
    }

    private void shopCoffee(Player player, UI ui) {
        if (player.getStrength() < 100) {
            player.setStrength(player.getStrength() + 10);
            ui.showMessage("Du drack kaffet och fick totalt " + player.getStrength() + " i styrka");
        } else {
            ui.showMessage("Kaffe har ingen effekt över 100");
        }
    }
}
