package Assignments.Week_1;

import java.util.ArrayList;
import java.util.Random;

public class MatchController {
    public static void main(String[] args) throws InterruptedException {
        Team A = new Team("A");
        Team B = new Team("B");
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(A);
        teams.add(B);
        System.out.println("Let's have a toss");
        int toss_winner = tossWin(teams);
        System.out.println(teams.get(toss_winner).team_name+ " won the Toss and decides to bat");
        while(teams.get(toss_winner).total_wickets!=0) {
            teams.get(toss_winner).play();
        }
        System.out.println(teams.get(Math.abs(1-toss_winner)).team_name+ " needs a total of "+(teams.get(toss_winner).total_runs+1)+" runs to win the match.");
        while(teams.get(Math.abs(1-toss_winner)).total_wickets!=0 && teams.get(Math.abs(1-toss_winner)).total_runs<=teams.get(toss_winner).total_runs) {
            teams.get(Math.abs(1-toss_winner)).play();
        }
        if(A.total_wickets==B.total_wickets && A.total_runs==B.total_runs) main(args);
        if(B.total_runs>A.total_runs) System.out.println("Team B Won the game by "+(B.total_runs-A.total_runs)+" runs and "+(B.total_wickets-A.total_wickets)+" wickets");
        else System.out.println("Team A Won the game by "+(A.total_runs-B.total_runs)+" runs and "+(A.total_wickets-B.total_wickets)+" wickets");
    }

    public static int tossWin(ArrayList<Team> teams) {
        Random rand = new Random();
        return rand.nextInt(teams.size());
    }
}
