package Assignments.Week_2;

import java.util.*;

public class Game {
    Map<String, String> scoreBoard = new HashMap<>(); // Todo: Store score in the scoreboard after every over
    ArrayList<Team> teams = new ArrayList<>();

    public Game(Team teamA, Team teamB) {
        teams.add(teamA);
        teams.add(teamB);
    }

    public void start() {

        System.out.println("Let's have a toss");
        int tossWinnerIndex = tossWin(teams);
        Team tossWinner = teams.get(tossWinnerIndex);
        Team tossLoser = teams.get(1 - tossWinnerIndex);

        Scanner scanner = new Scanner(System.in);
        System.out.println(tossWinner.team_name + " won the Toss.\nChoose the option you want to go with first: " +
                "\n1->Bat\n2->Bowl");
        String choice = scanner.next();
        switch (choice) {
            case "1" -> {
                System.out.println(tossWinner.team_name + " won the Toss and chose to Bat");
                System.out.println(tossWinner.team_name + " has the following players.");
                if (tossWinner.batsmen.size() != 0) System.out.println("\nBatsmen:");
                for (Map.Entry<Integer, String> batsman : tossWinner.batsmen.entrySet())
                    System.out.println(batsman.getValue());

                if (tossWinner.bowlers.size() != 0) System.out.println("\nBowlers:");
                for (Map.Entry<Integer, String> bowler : tossWinner.bowlers.entrySet())
                    System.out.println(bowler.getValue());
//                System.out.println("Bowler-----Balls Played-----Batsman-----Runs Scored-----Team Score");
                MatchController firstInnings = new MatchController();
                firstInnings.start(tossLoser, tossWinner);
                System.out.println("Wait STARTS!!!!!!!");
                try {
                    Thread.sleep((tossWinner.teamPlayers.size())*35000);
                } catch (InterruptedException e) {} // use future and callback to wait until the thread terminates
                System.out.println("Waiting ENDS!!!!!!!");
                MatchController secondInnings = new MatchController();
                secondInnings.start(tossWinner, tossLoser);
                try {
                    Thread.sleep((tossWinner.teamPlayers.size())*35000);
                } catch (InterruptedException e) {}
                System.out.println("Match ENDS!!!!!!");

//                Thread.sleep(30000);
//                Bowling bowling_second = new Bowling(tossWinner);
//                Batting batting_second = new Batting(tossLoser, bowling_second);

                System.out.println("Team " + ((tossLoser.total_runs > tossWinner.total_runs) ? tossLoser.team_name : tossWinner.team_name) + " won the game by " + Math.abs(tossWinner.total_runs - tossLoser.total_runs));
            }
            case "2" -> {
                System.out.println(tossWinner.team_name + " won the Toss and chose to Bowl");
                System.out.println(tossWinner.team_name + " has the following players.");
                if (tossWinner.batsmen.size() != 0) System.out.println("\nBatsmen:");
                for (Map.Entry<Integer, String> batsman : tossWinner.batsmen.entrySet())
                    System.out.println(batsman.getValue());

                if (tossWinner.bowlers.size() != 0) System.out.println("\nBowlers:");
                for (Map.Entry<Integer, String> bowler : tossWinner.bowlers.entrySet())
                    System.out.println(bowler.getValue());


                Bowling bowling = new Bowling(tossWinner);
                Batting batting = new Batting(tossLoser, bowling);
            }
            default -> {
                System.out.println("Please enter a Valid Input");
                start();
            }
        }
        //        System.out.println(tossWinner.team_name + " has made " + tossWinner.total_runs + " runs");
    }

    public int tossWin(ArrayList<Team> teams) {
        Random rand = new Random();
        return rand.nextInt(teams.size());
    }
}
