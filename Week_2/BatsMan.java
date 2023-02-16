package Assignments.Week_2;

import java.util.ArrayList;
import java.util.Random;

public class BatsMan implements Player {
    Random rand;
    ArrayList<String> runs = new ArrayList<>();
    int ballsPlayed = 0;
    int ballsBowled = 0;
    int playerScore = 0; // Not Used
    String player;

    public BatsMan(String playerName) {
        setRuns();
        this.player = playerName;
        this.rand = new Random();
    }

    public int getBallsBowled() {
        return this.ballsBowled;
    }

    public int getBallsPlayed() {
        return this.ballsPlayed;
    }

    @Override
    public void setRuns() {
        this.runs.add("0");
        this.runs.add("1");
        this.runs.add("5");
        this.runs.add("6");
        this.runs.add("2");
        this.runs.add("W");
        this.runs.add("3");
        this.runs.add("4");
        this.runs.add("W");
    }

    public String bat() {
        System.out.println(player + " is on the pitch and has played " + getBallsPlayed() + " balls");
        this.ballsPlayed++;
        String run = this.runs.get(this.rand.nextInt(this.runs.size())); // current_ball_output
        addPlayerScore(run);
        return run;
    }

    @Override
    public void bowl(String team_name) {
        System.out.println("Current Bowler is " + this.player + " from Team " + team_name + " and has played " + this.ballsBowled + " balls.");
        this.ballsBowled++;
    }

    public void addPlayerScore(String run) {
        if (run.equals("W")) hitWicket();
        else addRuns(Integer.valueOf(run));
    }

    public void hitWicket() {
        System.out.println(player + " has hit the wickets and made a total of " + this.playerScore + " runs in " +
                this.ballsPlayed + " balls.");
    }

    public void addRuns(int run) {
        System.out.println(player + " has scored a " + run + "\n");
        this.playerScore += run;
    }
}
