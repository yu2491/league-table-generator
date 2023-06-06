package pulselive.coding.task.yasmin.underdown;

import java.util.Comparator;

public class LeagueTableEntryComparator implements Comparator<LeagueTableEntry> {
    @Override
    public int compare(LeagueTableEntry entry1, LeagueTableEntry entry2) {
        // Compare by total points (descending)
        int pointsComparison = Integer.compare(entry2.getPoints(), entry1.getPoints());
        if (pointsComparison != 0) {
            return pointsComparison;
        }

        // Compare by goal difference (descending)
        int goalDifferenceComparison = Integer.compare(entry2.getGoalDifference(), entry1.getGoalDifference());
        if (goalDifferenceComparison != 0) {
            return goalDifferenceComparison;
        }

        // Compare by goals scored (descending)
        int goalsForComparison = Integer.compare(entry2.getGoalsFor(), entry1.getGoalsFor());
        if (goalsForComparison != 0) {
            return goalsForComparison;
        }

        // Compare by team name (in alphabetical order)
        return entry1.getTeamName().compareToIgnoreCase(entry2.getTeamName());
    }
}
