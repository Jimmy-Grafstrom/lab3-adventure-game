package se.sprinto.hakan.adventuregame.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.adventuregame.dao.StatisticsDao;
import se.sprinto.hakan.adventuregame.model.Statistics;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MockStatisticsServiceTest {

    @Mock
    private StatisticsDao mockDao;
    @InjectMocks
    private StatisticsService statisticService;

    private List<Statistics> getUnsortedList() {
        return new ArrayList<>(List.of(
                new Statistics("player-2nd", 100),
                new Statistics("player-3rd", 20),
                new Statistics("player-1st", 300)
        ));
    }

    @Test
    void getSortedStatistics() {
        when(mockDao.loadAll()).thenReturn(getUnsortedList());
        List<Statistics> sortedStats = statisticService.getSortedStatistics();

        assertEquals(3, sortedStats.size());
        assertEquals(300, sortedStats.get(0).getScore(), "Högsta poängen (300) ska vara först");
        assertEquals("player-1st", sortedStats.get(0).getPlayerName());
        assertEquals("player-2nd", sortedStats.get(1).getPlayerName());
        assertEquals("player-3rd", sortedStats.get(2).getPlayerName());

        verify(mockDao).loadAll();
    }
}