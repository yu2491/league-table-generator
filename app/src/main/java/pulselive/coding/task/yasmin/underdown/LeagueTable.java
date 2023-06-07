package pulselive.coding.task.yasmin.underdown;

import java.util.ArrayList;
import java.util.List;

public class LeagueTable {

    static final int POINTS_FOR_A_WIN = 3;
    static final int POINTS_FOR_A_DRAW = 1;
    static final int POINTS_FOR_A_LOSS = 0;

    private final List<Match> matches;
    public LeagueTable(final List<Match> matches) {
        this.matches = new ArrayList<>(matches);
    }

    private final List<LeagueTableEntry> tableEntries = new ArrayList<>();
    private final LeagueTableEntryComparator leagueTableEntryComparator = new LeagueTableEntryComparator();

    public List<LeagueTableEntry> getTableEntries() {
        createTableEntriesFromMatches();
        sortTableEntries();
        return tableEntries;
    }

    private void createTableEntriesFromMatches() {
        for (Match match : matches) {
            createOrUpdateLeagueTableEntry(match.getHomeTeam(), match.getHomeScore(), match.getAwayScore());
            createOrUpdateLeagueTableEntry(match.getAwayTeam(), match.getAwayScore(), match.getHomeScore());
        }
    }

    private void sortTableEntries() {
        tableEntries.sort(leagueTableEntryComparator);
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    private void createOrUpdateLeagueTableEntry(String teamName, int goalsFor, int goalsAgainst) {
        LeagueTableEntry tableEntry = getTeamFromTableEntries(teamName);
        if(tableEntry == null) {
            tableEntry = new LeagueTableEntry(teamName, 0,0,0,0,0,0,0,0);
            tableEntries.add(tableEntry);
        }
        tableEntry.setPlayed(tableEntry.getPlayed() + 1);
        tableEntry.setGoalsFor(tableEntry.getGoalsFor() + goalsFor);
        tableEntry.setGoalsAgainst(tableEntry.getGoalsAgainst() + goalsAgainst);
        int goalDifference = goalsFor - goalsAgainst;
        tableEntry.setGoalDifference(tableEntry.getGoalDifference() + goalDifference);
        if (goalDifference == 0) {
            updateEntryForDraw(tableEntry);
        } else if (goalDifference > 0) {
            updateEntryForWin(tableEntry);
        } else {
            updateEntryForLoss(tableEntry);
        }
    }

    private LeagueTableEntry getTeamFromTableEntries(String teamName) {
        for (LeagueTableEntry entry : tableEntries) {
            if (entry.getTeamName().equalsIgnoreCase(teamName)) {
                return entry;
            }
        }
        return null;
    }

    private static void updateEntryForWin(LeagueTableEntry tableEntry) {
        tableEntry.setWon(tableEntry.getWon() + 1);
        tableEntry.setPoints(tableEntry.getPoints() + POINTS_FOR_A_WIN);
    }
    private static void updateEntryForLoss(LeagueTableEntry tableEntry) {
        tableEntry.setLost(tableEntry.getLost() + 1);
        tableEntry.setPoints(tableEntry.getPoints() + POINTS_FOR_A_LOSS);
    }
    private static void updateEntryForDraw(LeagueTableEntry tableEntry) {
        tableEntry.setDrawn(tableEntry.getDrawn() + 1);
        tableEntry.setPoints(tableEntry.getPoints() + POINTS_FOR_A_DRAW);
    }
}
