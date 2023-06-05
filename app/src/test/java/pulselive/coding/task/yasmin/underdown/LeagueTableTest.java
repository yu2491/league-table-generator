package pulselive.coding.task.yasmin.underdown;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeagueTableTest {

    List<LeagueTableEntry> tableEntries;

    @Before
    public void setUp() {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match("Team A", "Team B", 2, 1));
        LeagueTable leagueTable = new LeagueTable(matches);
        tableEntries = leagueTable.getTableEntries();
    }

    @Test
    public void addingMatchToLeagueTableShouldAddTwoLeagueTableEntries() {
        assertEquals(2, tableEntries.size());
    }

    @Test
    public void teamAStats() {
        assertEquals("Team A", tableEntries.get(0).getTeamName());
        assertEquals(1, tableEntries.get(0).getPlayed());
        assertEquals(1, tableEntries.get(0).getWon());
        assertEquals(0, tableEntries.get(0).getDrawn());
        assertEquals(0, tableEntries.get(0).getLost());
        assertEquals(2, tableEntries.get(0).getGoalsFor());
        assertEquals(1, tableEntries.get(0).getGoalsAgainst());
        assertEquals(1, tableEntries.get(0).getGoalDifference());
        assertEquals(3, tableEntries.get(0).getPoints());
    }

    @Test
    public void teamBStats() {
        assertEquals("Team B", tableEntries.get(1).getTeamName());
        assertEquals(1, tableEntries.get(1).getPlayed());
        assertEquals(0, tableEntries.get(1).getWon());
        assertEquals(0, tableEntries.get(1).getDrawn());
        assertEquals(1, tableEntries.get(1).getLost());
        assertEquals(1, tableEntries.get(1).getGoalsFor());
        assertEquals(2, tableEntries.get(1).getGoalsAgainst());
        assertEquals(-1, tableEntries.get(1).getGoalDifference());
        assertEquals(0, tableEntries.get(1).getPoints());
    }


}
