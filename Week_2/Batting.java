package Assignments.Week_2;

import java.util.concurrent.ExecutionException;

public class Batting implements Runnable {
    Team team;
    int totalPlayers;
    int totalWickets = 0;
    boolean inningsEnd = false;
    Bowling bowling;
    Thread t;

    public Batting(Team team, Bowling bowling) {
        this.team = team;
        this.totalPlayers = this.team.teamPlayers.size();
        t = new Thread(this, "Batting");
        this.bowling = bowling;
        bowling.t.start();
        try {
            Thread.sleep(2500);
        } catch (Exception e) {
        }
        t.start();
    }

    @Override
    public void run() {
        try {
            while (this.team.totalWickets != totalPlayers) { // ToDo: Configure this out
                //            System.out.println(bowling.t.getState()+" is the current state of bowling thread.");
                //            System.out.println("Bat" + this.team.totalWickets + " " + totalPlayers);
                int playerIndex = this.team.totalWickets;
                this.team.bat(playerIndex);
                try {
                    Thread.sleep(2500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            System.out.println("INNINGS OVER!!!");
            System.out.println("We will have a short break before the next innings starts.\n");
            System.out.println("Team " + this.team.team_name + " has made a total of " + this.team.total_runs +
                    " runs in " + bowling.team.totalOvers + " overs\n\n");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            bowling.t.interrupt();
            t.interrupt();
            inningsEnd = true;
        }
    }
}
