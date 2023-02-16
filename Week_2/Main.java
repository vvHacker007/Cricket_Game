package Assignments.Week_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        createTeams();
    }

    private static void createTeams() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the total number of players in each team: ");
        int totalPlayers;
        while (true) {
            totalPlayers = scanner.nextInt();
            if (totalPlayers < 2) System.out.println("Please enter a valid Input");
            else break;
        }

        System.out.println("Player_1, Enter your Team Name:");
        String team_a = scanner.next();
        Team teamA = new Team(team_a);
        teamA.setTeamPlayers(totalPlayers);

        System.out.println("Player_2, Enter your Team Name:");
        String team_b = scanner.next();
        Team teamB = new Team(team_b);
        teamB.setTeamPlayers(totalPlayers);

        Game game = new Game(teamA, teamB);
        game.start();
    }
}
