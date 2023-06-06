package pulselive.coding.task.yasmin.underdown;

import java.util.ArrayList;
import java.util.List;

public class LeagueTable {
    private final List<Match> matches;

    private final List<LeagueTableEntry> tableEntries = new ArrayList<>();

    public LeagueTable(final List<Match> matches) {
        this.matches = new ArrayList<>(matches);
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public List<LeagueTableEntry> getTableEntries() {
        for (Match match : matches) {
            addOrUpdateLeagueTableEntry(match.getHomeTeam(), match.getHomeScore(), match.getAwayScore());
            addOrUpdateLeagueTableEntry(match.getAwayTeam(), match.getAwayScore(), match.getHomeScore());
        }
        return tableEntries;
    }

    private void addOrUpdateLeagueTableEntry(String teamName, int goalsFor, int goalsAgainst) {
        LeagueTableEntry tableEntry = getTeamFromTable(teamName);
        if(tableEntry == null) {
            tableEntry = newTeamLeagueTableEntry(teamName);
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

    private LeagueTableEntry newTeamLeagueTableEntry(String teamName) {
        return new LeagueTableEntry().builder()
                .teamName(teamName)
                .played(0)
                .won(0)
                .drawn(0)
                .lost(0)
                .goalsFor(0)
                .goalsAgainst(0)
                .goalDifference(0)
                .points(0)
                .build();
    }

    private LeagueTableEntry getTeamFromTable(String teamName) {
        for (LeagueTableEntry entry : tableEntries) {
            if (entry.getTeamName().equals(teamName)) {
                return entry;
            }
        }
        return null;
    }

    private static void updateEntryForWin(LeagueTableEntry tableEntry) {
        tableEntry.setWon(tableEntry.getWon() + 1);
        tableEntry.setPoints(tableEntry.getPoints() + 3);
    }
    private static void updateEntryForLoss(LeagueTableEntry tableEntry) {
        tableEntry.setLost(tableEntry.getLost() + 1);
    }
    private static void updateEntryForDraw(LeagueTableEntry tableEntry) {
        tableEntry.setDrawn(tableEntry.getDrawn() + 1);
        tableEntry.setPoints(tableEntry.getPoints() + 1);
    }
}
