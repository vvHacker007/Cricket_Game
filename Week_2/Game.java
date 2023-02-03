package Assignments.Week_2;

import java.util.*;

public class Game {
    Map<String, String> scoreBoard = new HashMap<>(); // Todo: Store score in the scoreboard after every over
    ArrayList<Team> teams = new ArrayList<>();

    public Game(Team teamA, Team teamB) {
        teams.add( teamA );
        teams.add( teamB );
    }
    public void start() {

        System.out.println("Let's have a toss");
        int tossWinnerIndex = tossWin(teams);
        Team tossWinner = teams.get(tossWinnerIndex);
        Team tossLoser = teams.get(1-tossWinnerIndex);

        Scanner scanner = new Scanner(System.in);
        System.out.println( tossWinner.team_name + " won the Toss.\nChoose the option you want to go with first: " +
                "\n1->Bat\n2->Bowl" );
        String choice = scanner.next();

        switch(choice) {
            case "1" -> {
                System.out.println(tossWinner.team_name + " won the Toss and chose to Bat");
                System.out.println(tossWinner.team_name+" has the following players.");
                if(tossWinner.batsmen.size()!=0) System.out.println("\nBatsmen:");
                for(Map.Entry<Integer,String> batsman : tossWinner.batsmen.entrySet()) System.out.println(batsman.getValue());

                if(tossWinner.bowlers.size()!=0) System.out.println("\nBowlers:");
                for(Map.Entry<Integer,String> bowler : tossWinner.bowlers.entrySet()) System.out.println(bowler.getValue());

                new Bowling(tossLoser);
                new Batting(tossWinner);

            }
            case "2" -> {
                System.out.println(tossWinner.team_name + " won the Toss and chose to Bowl");
                System.out.println(tossWinner.team_name+" has the following players.");
                if(tossWinner.batsmen.size()!=0) System.out.println("\nBatsmen:");
                for(Map.Entry<Integer,String> batsman : tossWinner.batsmen.entrySet()) System.out.println(batsman.getValue());

                if(tossWinner.bowlers.size()!=0) System.out.println("\nBowlers:");
                for(Map.Entry<Integer,String> bowler : tossWinner.bowlers.entrySet()) System.out.println(bowler.getValue());


                new Bowling(tossWinner);
                new Batting(tossLoser);
            }
            default -> {
                System.out.println("Please enter a Valid Input");
                start();
            }
        }
        System.out.println(tossWinner.team_name+" has made "+tossWinner.total_runs+" runs");
    }
    public int tossWin( ArrayList<Team> teams ) {
        Random rand = new Random();
        return rand.nextInt( teams.size() );
    }
}
