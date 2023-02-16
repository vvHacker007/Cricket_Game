package Assignments.Week_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Team {
    static boolean nextBall = false;
    public ArrayList<String> teamPlayers = new ArrayList<>();
    public Map<Integer, Player> playerStats = new HashMap<>(); // Not Used
    public int totalWickets;
    public int total_runs = 0;
    public int totalOvers = 0;
    public String team_name;
    Player batsman;
    Player bowler;
    Map<Integer, String> batsmen = new HashMap<>();
    Map<Integer, String> bowlers = new HashMap<>();

    public Team(String team_name) {
        this.team_name = team_name;
    }

    public void setTeamPlayers(int totalPlayers) {

        int playerCount = 0;
        Scanner scanner = new Scanner((System.in));

        while (playerCount != totalPlayers) {
            System.out.println("Enter player " + (playerCount + 1) + " name: ");
            String playerName = scanner.next();

            System.out.println("Enter player " + (playerCount + 1) + " role: \n1. Batsman\n2. Bowler");
            int player_role = scanner.nextInt();

            switch (player_role) {
                case 1 -> {
                    this.teamPlayers.add(playerName);
                    this.batsmen.put(teamPlayers.size() - 1, playerName);
                }
                case 2 -> {
                    this.teamPlayers.add(playerName);
                    this.bowlers.put(teamPlayers.size() - 1, playerName);
                }
                default -> {
                    System.out.println("Please enter a valid option");
                    playerCount--;
                }
            }
            playerCount++;
        }
    }

    public synchronized void bat(int playerIndex) {
//        System.out.println(Team.nextBall+" Bat");
        while (!Team.nextBall) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
//        System.out.println("Current batsman is " + teamPlayers.get(playerIndex) + " with " + this.total_runs + " runs.");
        if (teamPlayers.get(playerIndex).equals(batsmen.get(playerIndex))) {
            if (batsman == null) batsman = new BatsMan(teamPlayers.get(playerIndex));
            updateTeamScore(batsman.bat(), playerIndex); // ToDo: Figure a way out to add runs for batting and then add wickets for wicket and add bowls to the code
        } else if (teamPlayers.get(playerIndex).equals(bowlers.get(playerIndex))) {
            if (bowler == null) bowler = new Bowler(teamPlayers.get(playerIndex));
            updateTeamScore(bowler.bat(), playerIndex);
        }
//        System.out.println("Team.nextball="+Team.nextBall+"   "+this.teamPlayers.get(playerIndex));
        Team.nextBall = false;
        notifyAll();
    }

    public synchronized void bowl(int playerIndex) {
//        System.out.println(Team.nextBall+" Ball");
        while (Team.nextBall) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        if (teamPlayers.get(playerIndex).equals(batsmen.get(playerIndex))) {
            if (batsman == null) batsman = new BatsMan(teamPlayers.get(playerIndex));
            batsman.bowl(this.team_name);
            if (batsman.getBallsBowled() % 6 == 0) completeOver(playerIndex);
        } else if (teamPlayers.get(playerIndex).equals(bowlers.get(playerIndex))) {
            if (bowler == null) bowler = new Bowler(teamPlayers.get(playerIndex));
            bowler.bowl(this.team_name);
            if (bowler.getBallsBowled() % 6 == 0 && this.totalOvers != 0) completeOver(playerIndex);
        }
//        System.out.println("Team.nextball="+Team.nextBall+"   "+this.teamPlayers.get(playerIndex));
        Team.nextBall = true;
        notifyAll();
    }

    public void updateTeamScore(String run, int playerIndex) {
        if (run.equals("W")) {
            if (teamPlayers.get(playerIndex).equals(batsmen.get(playerIndex))) {
                playerStats.put(playerIndex, batsman);
            } else if (teamPlayers.get(playerIndex).equals(bowlers.get(playerIndex))) {
                playerStats.put(playerIndex, bowler);
            }
            playerIndex++;
            if (teamPlayers.get(playerIndex).equals(batsmen.get(playerIndex))) {
                batsman = new BatsMan(teamPlayers.get(playerIndex));
            } else if (teamPlayers.get(playerIndex).equals(bowlers.get(playerIndex))) {
                bowler = new Bowler(teamPlayers.get(playerIndex));
            }
            this.totalWickets++;
            System.out.println(team_name+" has made a total of "+this.total_runs+" runs.\n");
        } else {
            this.total_runs += Integer.valueOf(run);
        }
    }
//
//    public void hitWicket(int playerIndex) {
//        String player = teamPlayers.get(playerIndex);
//        System.out.println(player + " has hit the wickets");
//        playerIndex++;
//        if (teamPlayers.get(playerIndex).equals(batsmen.get(playerIndex))) {
//            batsman = new BatsMan(teamPlayers.get(playerIndex));
//        } else if (teamPlayers.get(playerIndex).equals(bowlers.get(playerIndex))) {
//            bowler = new Bowler(teamPlayers.get(playerIndex));
//        }
//        this.totalWickets++;
//    }
//
//    public void addRuns(int run, int playerIndex) {
//        System.out.println(this.teamPlayers.get(playerIndex) + " has scored a " + run);
//        this.total_runs += run;
//    }

    public void completeOver(int playerIndex) {
        System.out.println(teamPlayers.get(playerIndex) + " has completed it's over");
        playerIndex++;
        if (teamPlayers.get(playerIndex).equals(batsmen.get(playerIndex)))
            batsman = new BatsMan(teamPlayers.get(playerIndex));
        else if (teamPlayers.get(playerIndex).equals(bowlers.get(playerIndex)))
            bowler = new Bowler(teamPlayers.get(playerIndex));
        System.out.println("New Bowler is " + teamPlayers.get(playerIndex));
        this.totalOvers++;
//        System.out.println(this.totalOvers+" number of overs are completed.");
    }
}
