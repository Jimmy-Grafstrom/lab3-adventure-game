package se.sprinto.hakan.adventuregame.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sprinto.hakan.adventuregame.dao.FakeFileStatisticsDao;
import se.sprinto.hakan.adventuregame.model.Statistics;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsServiceTest {
    private FakeFileStatisticsDao fakeFileStatisticsDao;
    private StatisticsService statisticsService;

    @BeforeEach
    void setUp() {
        fakeFileStatisticsDao = new FakeFileStatisticsDao();
        statisticsService = new StatisticsService(fakeFileStatisticsDao);
    }

    @Test
    void getSortedStatistics_ReturnSortedList(){
        List<Statistics> sortedList = statisticsService.getSortedStatistics();
        assertEquals(3, sortedList.size());
        assertEquals("player-1st", sortedList.get(0).getPlayerName());
        assertEquals(300, sortedList.get(0).getScore());
        assertEquals("player-2nd", sortedList.get(1).getPlayerName());
        assertEquals(100, sortedList.get(1).getScore());
        assertEquals("player-3rd", sortedList.get(2).getPlayerName());
        assertEquals(20, sortedList.get(2).getScore());
    }
}
