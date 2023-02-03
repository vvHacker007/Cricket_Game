package Assignments.Week_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class  Team {
    static boolean nextBall = false;
    public ArrayList<String> teamPlayers = new ArrayList<>();
    public Map<String, ArrayList> playerStats = new HashMap<>(); // Not Used
    public int totalWickets;
    public int total_runs = 0;
    public int totalOvers = 0;
    public String team_name;
    Player batsman;
    Player bowler;
    Map<Integer,String> batsmen = new HashMap<>();
    Map<Integer,String> bowlers = new HashMap<>();

    public Team( String team_name ) {
        this.team_name = team_name;
    }

    public void setTeamPlayers(int totalPlayers) {

        int playerCount=0;
        Scanner scanner = new Scanner((System.in));

        while(playerCount!=totalPlayers) {
            System.out.println("Enter player "+ (playerCount+1) + " name: ");
            String playerName = scanner.next();

            System.out.println("Enter player "+ (playerCount+1) + " role: \n1. Batsman\n2. Bowler");
            int player_role = scanner.nextInt();

            switch(player_role) {
                case 1 -> {
                    this.teamPlayers.add(playerName);
                    this.batsmen.put(teamPlayers.size()-1,playerName);
                }
                case 2 -> {
                    this.teamPlayers.add(playerName);
                    this.bowlers.put(teamPlayers.size()-1,playerName);
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
        while(!Team.nextBall) {
            try { wait();} catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(Team.nextBall);
        if(teamPlayers.get(playerIndex).equals(batsmen.get(playerIndex))) {
            if(playerIndex==0) {
                String player = teamPlayers.get(playerIndex);
                batsman = new BatsMan(player);
            }
            addPlayerScore(batsman.bat(), playerIndex); // ToDo: Figure a way out to add runs for batting and then add wickets for wicket and add bowls to the code
            System.out.println("Current batsman is "+teamPlayers.get(playerIndex)+" with "+this.total_runs+" runs.");
        } else if(teamPlayers.get(playerIndex).equals(bowlers.get(playerIndex))) {
            if(playerIndex==0) {
                String player = teamPlayers.get(playerIndex);
                Player bowler = new Bowler(player);
            }
            addPlayerScore(bowler.bat(), playerIndex);
        }
        Team.nextBall = false;
        notifyAll();
    }
    public synchronized void bowl(int playerIndex) {
        while(Team.nextBall) {
            try { wait(); } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(teamPlayers.get(playerIndex).equals(batsmen.get(playerIndex))) {
            if(this.totalOvers==0) batsman = new BatsMan(teamPlayers.get(playerIndex));
            batsman.bowl();
            if(batsman.getBallsPlayed()%6==0) completeOver(playerIndex);
        } else if(teamPlayers.get(playerIndex).equals("Bowler")) {
            if(this.totalOvers==0) bowler = new Bowler(teamPlayers.get(playerIndex));
            bowler.bowl();
            if(bowler.getBallsPlayed()%6==0 && this.totalOvers!=0) completeOver(playerIndex);
        }
        Team.nextBall = true;
        notifyAll();
    }

    public void addPlayerScore(String run, int playerIndex) {
        if(run.equals("W")) hitWicket(playerIndex);
        else addRuns(Integer.valueOf(run));
    }

    public void hitWicket(int playerIndex) {
        String player = teamPlayers.get(playerIndex);
        System.out.println(player + " has hit the wickets");
        playerIndex++;
        if(teamPlayers.get(playerIndex).equals(batsmen.get(playerIndex))) batsman = new BatsMan(teamPlayers.get(playerIndex));
        else bowler = new Bowler(teamPlayers.get(playerIndex));
        this.totalWickets++;
    }
    public void addRuns(int run) {
        this.total_runs+=run;
    }
    public void completeOver(int playerIndex) {
        System.out.println(teamPlayers.get(playerIndex)+" has completed it's over");
        playerIndex++;
        if(teamPlayers.get(playerIndex).equals(batsmen.get(playerIndex))) batsman = new BatsMan(teamPlayers.get(playerIndex));
        else bowler = new Bowler(teamPlayers.get(playerIndex));
        this.totalOvers++;
    }
}
