package pulselive.coding.task.yasmin.underdown;

import java.util.Comparator;

/**
 * This comparator compares firstly by total points (descending) then by goal difference (descending)
 * then by goals scored (descending) then will compare by team name (in alphabetical order)
 */
public class BestLeagueTableEntryComparator implements Comparator<LeagueTableEntry> {
    @Override
    public int compare(LeagueTableEntry entry1, LeagueTableEntry entry2) {
        int pointsComparison = Integer.compare(entry2.getPoints(), entry1.getPoints());
        if (pointsComparison != 0) {
            return pointsComparison;
        }

        int goalDifferenceComparison = Integer.compare(entry2.getGoalDifference(), entry1.getGoalDifference());
        if (goalDifferenceComparison != 0) {
            return goalDifferenceComparison;
        }

        int goalsForComparison = Integer.compare(entry2.getGoalsFor(), entry1.getGoalsFor());
        if (goalsForComparison != 0) {
            return goalsForComparison;
        }

        return entry1.getTeamName().compareToIgnoreCase(entry2.getTeamName());
    }
}
