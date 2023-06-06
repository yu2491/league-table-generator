package pulselive.coding.task.yasmin.underdown;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeagueTableTest {

    List<LeagueTableEntry> tableEntries;

    LeagueTable leagueTable;

    @Before
    public void setUp() {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match("Team A", "Team B", 2, 1));
        leagueTable = new LeagueTable(matches);
    }

    @Test
    public void addingMatchToLeagueTableShouldAddTwoLeagueTableEntries() {
        tableEntries = leagueTable.getTableEntries();
        assertEquals(2, tableEntries.size());
    }

    @Test
    public void teamAStats() {
        LeagueTableEntry teamALeagueEntry = leagueTable.getTableEntries().get(0);
        assertEquals("Team A", teamALeagueEntry.getTeamName());
        assertEquals(1, teamALeagueEntry.getPlayed());
        assertEquals(1, teamALeagueEntry.getWon());
        assertEquals(0, teamALeagueEntry.getDrawn());
        assertEquals(0, teamALeagueEntry.getLost());
        assertEquals(2, teamALeagueEntry.getGoalsFor());
        assertEquals(1, teamALeagueEntry.getGoalsAgainst());
        assertEquals(1, teamALeagueEntry.getGoalDifference());
        assertEquals(3, teamALeagueEntry.getPoints());
    }

    @Test
    public void teamBStats() {
        LeagueTableEntry teamBLeagueEntry = leagueTable.getTableEntries().get(1);
        assertEquals("Team B", teamBLeagueEntry.getTeamName());
        assertEquals(1, teamBLeagueEntry.getPlayed());
        assertEquals(0, teamBLeagueEntry.getWon());
        assertEquals(0, teamBLeagueEntry.getDrawn());
        assertEquals(1, teamBLeagueEntry.getLost());
        assertEquals(1, teamBLeagueEntry.getGoalsFor());
        assertEquals(2, teamBLeagueEntry.getGoalsAgainst());
        assertEquals(-1, teamBLeagueEntry.getGoalDifference());
        assertEquals(0, teamBLeagueEntry.getPoints());
    }

    @Test
    public void whenAddingAMatchShouldAddToExistingLeagueTableEntryIfTeamAlreadyExists() {
        Match match = new Match("Team B", "Team A", 2, 2);
        leagueTable.addMatch(match);
        tableEntries = leagueTable.getTableEntries();
        assertEquals(2, tableEntries.size());
        assertEquals(2, tableEntries.get(0).getPlayed());
        assertEquals(2, tableEntries.get(1).getPlayed());
    }

    @Test
    public void whenAddingAMatchIfTeamAlreadyExistsPointsShouldBeAddedToExistingValues() {
        Match match = new Match("Team B", "Team A", 2, 2);
        leagueTable.addMatch(match);
        LeagueTableEntry teamALeagueEntry = leagueTable.getTableEntries().get(0);
        assertEquals(2, teamALeagueEntry.getPlayed());
        assertEquals(1, teamALeagueEntry.getWon());
        assertEquals(1, teamALeagueEntry.getDrawn());
        assertEquals(0, teamALeagueEntry.getLost());
        assertEquals(4, teamALeagueEntry.getGoalsFor());
        assertEquals(3, teamALeagueEntry.getGoalsAgainst());
        assertEquals(1, teamALeagueEntry.getGoalDifference());
        assertEquals(4, teamALeagueEntry.getPoints());
    }

    @Test
    public void getTableEntriesShouldBeSortedFirstByPoints() {
        Match winningTeamBMatch1 = new Match("Team B", "Team C", 3, 0);
        Match winningTeamBMatch2 = new Match("Team B", "Team D", 2, 1);
        leagueTable.addMatch(winningTeamBMatch1);
        leagueTable.addMatch(winningTeamBMatch2);
        assertEquals("Team B", leagueTable.getTableEntries().get(0).getTeamName());
    }

    @Test
    public void getTableEntriesShouldBeSortedSecondByGoalDifference() {
        Match winningTeamBMatch = new Match("Team B", "Team A", 3, 0);
        leagueTable.addMatch(winningTeamBMatch);
        tableEntries = leagueTable.getTableEntries();
        assertEquals(getTeamFromTable("Team A").getPoints(), getTeamFromTable("Team B").getPoints());
        assertEquals("Team B", leagueTable.getTableEntries().get(0).getTeamName());

    }

    @Test
    public void getTableEntriesShouldBeSortedThirdlyByGoalScored() {
        Match winningTeamBMatch = new Match("Team B", "Team C", 2, 0);
        leagueTable.addMatch(winningTeamBMatch);
        tableEntries = leagueTable.getTableEntries();
        assertEquals(getTeamFromTable("Team A").getPoints(), getTeamFromTable("Team B").getPoints());
        assertEquals(getTeamFromTable("Team A").getGoalDifference(), getTeamFromTable("Team B").getGoalDifference());
        assertEquals("Team B", tableEntries.get(0).getTeamName());
    }

    @Test
    public void getTableEntriesShouldBeSortedLastlyByTeamName() {
        Match winningTeamBMatch = new Match("Team B", "C Team", 2, 1);
        Match winningTeamCMatch = new Match("C Team", "Team A", 2, 1);
        leagueTable.addMatch(winningTeamBMatch);
        leagueTable.addMatch(winningTeamCMatch);
        tableEntries = leagueTable.getTableEntries();
        var teamA = getTeamFromTable("Team A");
        var teamB = getTeamFromTable("Team B");
        var teamC = getTeamFromTable("C Team");
        assertEquals(teamA.getPoints(), teamB.getPoints());
        assertEquals(teamB.getPoints(), teamC.getPoints());
        assertEquals(teamA.getGoalDifference(), teamB.getGoalDifference());
        assertEquals(teamB.getGoalDifference(), teamC.getGoalDifference());
        assertEquals(teamA.getGoalsFor(), teamB.getGoalsFor());
        assertEquals(teamB.getGoalsFor(), teamC.getGoalsFor());
        assertEquals("C Team", leagueTable.getTableEntries().get(0).getTeamName());
        assertEquals("Team A", leagueTable.getTableEntries().get(1).getTeamName());
        assertEquals("Team B", leagueTable.getTableEntries().get(2).getTeamName());
    }
    private LeagueTableEntry getTeamFromTable(String teamName) {
        for (LeagueTableEntry entry : tableEntries) {
            if (entry.getTeamName().equals(teamName)) {
                return entry;
            }
        }
        return null;
    }




}
