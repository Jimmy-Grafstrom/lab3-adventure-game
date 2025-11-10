package se.sprinto.hakan.adventuregame.dao;

import se.sprinto.hakan.adventuregame.model.Statistics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FakeFileStatisticsDao implements StatisticsDao {

    @Override
    public void save(Statistics stat) {
        // Gör ingenting
    }

    @Override
    public List<Statistics> loadAll() {
        return createFakeList();
    } // Returnerar fake-lista

    public List<Statistics> createFakeList() { // Skapar fake-lista
        List<Statistics> stats = new ArrayList<>();
        stats.add(new Statistics("player-2nd", 100));
        stats.add(new Statistics("player-3rd", 20));
        stats.add(new Statistics("player-1st", 300));
        return stats;
    }
}

