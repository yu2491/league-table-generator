package pulselive.coding.task.yasmin.underdown;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeagueTableTest {

    @Test
    public void addingMatchToLeagueTableShouldAddTwoLeagueTableEntries() {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match("Team A", "Team B", 2, 1));
        LeagueTable leagueTable = new LeagueTable(matches);
        List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();
        assertEquals(2, tableEntries.size());
    }


}
