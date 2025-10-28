package se.sprinto.hakan.adventuregame.model;

import se.sprinto.hakan.adventuregame.view.UI;

public class ForestRoom implements Room {

    private AbstractCharacter trollkarl = new Trollkarl("Trollkarlen", 100, 0, 20);

    @Override
    public void enterRoom(Player player, UI ui) {
        ui.showMessage("Du kliver in i en mörk skog.");
        if (player.hasFoundKey() && player.hasDefeatedTrollkarl()) {
            ui.showMessage("Det finns inget att göra i skogen.");
            return;
        }
        if (!player.hasFoundKey()) {
            ui.showMessage("Något glimmar i mossan...");
        }
        if (!player.hasDefeatedTrollkarl()) {
            ui.showMessage("Du ser en trollkarl, han kan hela dig så länge du inte har mer än 200 health");
        }
        if (!player.hasDefeatedTrollkarl() && !player.hasFoundKey()) {
            trollkarlIsAliveNotFoundKey(player, ui);
        } else if (player.hasDefeatedTrollkarl() && !player.hasFoundKey()) {
            notFoundKey(player, ui);
        } else if (!player.hasDefeatedTrollkarl() && player.hasFoundKey()) {
            trollkarlIsAlive(player, ui);
        }
    }

    private void trollkarlIsAliveNotFoundKey(Player player, UI ui) {
        String choice = ui.getInput("""
                Vad vill du göra?
                1: Vill du undersöka glittret?
                2: Prata med trollkarlen?""");
        if (choice.equals("1")) {
            ui.showMessage("Du hittar en nyckel!");
            player.setFoundKey(true);
            player.addScore(20);
        } else if (choice.equals("2")) {
            if (player.getHealth() < 200) {
                player.setHealth(player.getHealth() + 100);
                ui.showMessage("Trollkarlen ger dig mer 100 mer health");
                ui.showMessage("Ditt HP: " + player.getHealth());
            } else {
                ui.showMessage("Du har redan blivit tillräckligt helad säger trollkarlen.");
                stridMedTrollkarlen(player, ui);
            }
        } else {
            ui.showMessage("Du ignorerar glittret och trollkarlen och går vidare.");
        }
    }

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

    private void trollkarlIsAlive(Player player, UI ui) {
        String choice = ui.getInput("Vill du prata med trollkarlen? (ja/nej)");
        if (choice.equalsIgnoreCase("ja")) {
            if (player.getHealth() < 200) {
                player.setHealth(player.getHealth() + 100);
                ui.showMessage("Trollkarlen ger dig mer 100 mer health");
                ui.showMessage("Ditt HP: " + player.getHealth());
            } else {
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

