package Assignments.Week_2;

public class MatchController {
    public void start(Team bowlingTeam,Team battingTeam) {
        System.out.println("Batting Team: " + battingTeam.team_name + "\nBowling Team: " + bowlingTeam.team_name + "\n");
        Bowling bowling = new Bowling(bowlingTeam);
        Batting batting = new Batting(battingTeam, bowling);
//        while (!batting.inningsEnd || !bowling.inningsEnd) {
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//            }
//        }
    }
}
