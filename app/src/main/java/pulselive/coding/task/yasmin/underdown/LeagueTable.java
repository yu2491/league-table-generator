package pulselive.coding.task.yasmin.underdown;

import java.util.ArrayList;
import java.util.List;

public class LeagueTable {
    private final List<Match> matches;

    public LeagueTable(final List<Match> matches) {
        this.matches = new ArrayList<>(matches);
    }

    public List<LeagueTableEntry> getTableEntries() {
        List<LeagueTableEntry> tableEntries = new ArrayList<>();
        for (Match match : matches) {
            addTableEntry(tableEntries, match.getHomeTeam(), match.getHomeScore(), match.getAwayScore());
            addTableEntry(tableEntries, match.getAwayTeam(), match.getAwayScore(), match.getHomeScore());
        }
        return tableEntries;
    }

    private void addTableEntry(List<LeagueTableEntry> tableEntries, String teamName, int goalsFor, int goalsAgainst) {
        int goalDifference = goalsFor - goalsAgainst;
        LeagueTableEntry tableEntry = new LeagueTableEntry().builder()
                .teamName(teamName)
                .played(1)
                .goalsFor(goalsFor)
                .goalsAgainst(goalsAgainst)
                .goalDifference(goalDifference)
                .build();
        if (goalDifference == 0) {
            tableEntry.setWon(0);
            tableEntry.setDrawn(1);
            tableEntry.setLost(0);
            tableEntry.setPoints(1);
        } else if (goalDifference > 0) {
            tableEntry.setWon(1);
            tableEntry.setDrawn(0);
            tableEntry.setLost(0);
            tableEntry.setPoints(3);
        } else {
            tableEntry.setWon(0);
            tableEntry.setDrawn(0);
            tableEntry.setLost(1);
            tableEntry.setPoints(0);
        }
        tableEntries.add(tableEntry);
    }
}
