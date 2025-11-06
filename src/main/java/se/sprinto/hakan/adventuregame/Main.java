package se.sprinto.hakan.adventuregame;

import se.sprinto.hakan.adventuregame.dao.FileStatisticsDao;
import se.sprinto.hakan.adventuregame.dao.StatisticsDao;
import se.sprinto.hakan.adventuregame.model.characters.Player;
import se.sprinto.hakan.adventuregame.model.rooms.StartRoom;
import se.sprinto.hakan.adventuregame.model.Statistics;
import se.sprinto.hakan.adventuregame.properties.ConfigManager;
import se.sprinto.hakan.adventuregame.service.StatisticsService;
import se.sprinto.hakan.adventuregame.view.ScannerUI;
import se.sprinto.hakan.adventuregame.view.UI;

public class Main {

    public static void main(String[] args) {
        UI ui = new ScannerUI();
        ui.showMessage("Välkommen till Äventyrsspelet!");
        ui.showMessage(ConfigManager.getInstance().getVersionAndName());
        String name = ui.getInput("Ange ditt namn:");
        Player player = new Player.Builder()
                .name(name)
                .health(100)
                .score(0)
                .strength(10)
                .build();

        new StartRoom().enterRoom(player, ui);

        StatisticsDao dao = new FileStatisticsDao();
        dao.save(new Statistics(player.getName(), player.getScore()));

        StatisticsService service = new StatisticsService(dao);
        StringBuilder leaderboard = new StringBuilder();
        leaderboard.append("--- Topplista ---\n\n");
        for (Statistics s : service.getSortedStatistics()) {
            leaderboard.append(s.getPlayerName())
                       .append(" - ")
                       .append(s.getScore())
                       .append(" poäng\n");
        }
        ui.showMessage(leaderboard.toString());
    }
}

