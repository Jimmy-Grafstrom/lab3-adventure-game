package se.sprinto.hakan.adventuregame.model;

import se.sprinto.hakan.adventuregame.view.UI;

public class StartRoom implements Room {

    @Override
    public void enterRoom(Player player, UI ui) {
        ui.showMessage("Du befinner dig i start-rummet. Du ser sex dörrar framför dig.");
        ui.showMessage("Du måste besegra alla fiender och hitta skatten för att vinna.");
        boolean exit = false;
        while (!exit) {
            String choice = ui.getInput("Vilken dörr vill du ta? (1=Skog, 2=Fängelse, 3=Skattkammare, 4=Butiken, 5=Bergen, 6=IT-Högskolan, q=avsluta)");
            switch (choice) {
                case "1":
                    new ForestRoom().enterRoom(player, ui);
                    break;
                case "2":
                    new DungeonRoom().enterRoom(player, ui);
                    break;
                case "3":
                    if (!player.hasOpenedChest()) {
                        new TreasureRoom().enterRoom(player, ui);
                    } else {
                        ui.showMessage("Du har redan hittat och öppnat kistan");
                    }
                    break;
                case "4":
                    new ShopRoom().enterRoom(player, ui);
                    break;
                case "5":
                    new MountainRoom().enterRoom(player, ui);
                    break;
                case "6":
                    new IthsRoom().enterRoom(player, ui);
                    break;
                case "q":
                    exit = true;
                    break;
                default:
                    ui.showMessage("Ogiltigt val.");
            }
            if (player.hasWon()) {
                ui.showMessage("Grattis! Du har klarat spelet!");
                exit = true;
            } else if (player.getHealth() <= 0) {
                ui.showMessage(("Din hälsa är kritisk, du vacklar till och dör!"));
                exit = true;
            }
        }
    }
}

