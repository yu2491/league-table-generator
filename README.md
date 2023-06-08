# League Table

This Java code implements a League Table for a football league. When creating a LeagueTable it will take a list of 
completed matches and produce a sorted list of LeagueTableEntry objects. A win is worth three points to the winning team. 
A draw is worth one point to each team. By default, the sorting rules for entries in the league table are:
* By total points (descending)
* Then by goal difference (descending)
* Then by goals scored (descending)
* Then by team name (in alphabetical order)

If you want to sort a league differently you can add your own Comparator object when creating a league.

## Prerequisites
To run this code, you need to have the following:

- Java Development Kit (JDK) installed on your machine and set to Java 11.
- Gradle installed on your machine.

#### Build the project by running the following command:
```
gradle build
```

#### You can run tests by running the following command:
```
gradle test
```


### Example:
Create instances of LeagueTable and Match, and add the matches to the league table:
```
List<Match> matches = new ArrayList<>();
matches.add("Chelsea", "Manchester United",1,0)
matches.add("Manchester United","Chelsea",1,3)
matches.add("Chelsea","Arsenal",2,0),
matches.add("Arsenal","Chelsea",1,1),
matches.add("Chelsea","Manchester City",2,0),
matches.add("Manchester City","Chelsea",2,0),
matches.add("Manchester United","Arsenal",1,0),
matches.add("Arsenal","Manchester United",2,3),
matches.add("Manchester City","Arsenal",2,1),
matches.add("Arsenal","Manchester City",2,1),
matches.add("Manchester City","Manchester United",1,1),
matches.add("Manchester United","Manchester City",2,1),

LeagueTable leagueTable = new LeagueTable(matches);
```
Call the getTableEntries() method on LeagueTable to get the sorted list of LeagueTableEntry objects:
```
List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

RESULT:
[LeagueTableEntry(teamName=Manchester United, played=6, won=3, drawn=3, lost=0, goalsFor=9, goalsAgainst=6, goalDifference=3, points=12), 
LeagueTableEntry(teamName=Chelsea, played=6, won=2, drawn=3, lost=1, goalsFor=7, goalsAgainst=5, goalDifference=2, points=9), 
LeagueTableEntry(teamName=Manchester City, played=6, won=2, drawn=1, lost=3, goalsFor=7, goalsAgainst=8, goalDifference=-1, points=7), 
LeagueTableEntry(teamName=Arsenal, played=6, won=1, drawn=1, lost=4, goalsFor=6, goalsAgainst=10, goalDifference=-4, points=4)]
```
