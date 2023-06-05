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
            LeagueTableEntry homeTeam = new LeagueTableEntry(match.getHomeTeam(), 1, 0,0,0, match.getHomeScore(), match.getAwayScore(), 0, 0);
            LeagueTableEntry awayTeam = new LeagueTableEntry(match.getAwayTeam(), 1,0, 0,0, match.getAwayScore(), match.getHomeScore(), 0,0);
            tableEntries.add(homeTeam);
            tableEntries.add(awayTeam);
        }
        return tableEntries;
    }
}
