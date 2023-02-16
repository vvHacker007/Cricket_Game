package Assignments.Week_2;

public class Bowling implements Runnable {
    Team team;
    int totalPlayers;
    int totalOvers = 0;
    boolean inningsEnd = false;
    Thread t;

    public Bowling(Team team) {
        this.team = team;
        this.totalPlayers = this.team.teamPlayers.size();
        this.t = new Thread(this, "Bowling");
//        t.start();
        try {
            Thread.sleep(2500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (this.team.totalOvers != totalPlayers) {
                //            System.out.println(t.getState()+" is the current state.");
                //            System.out.println("Bowl" + this.team.totalOvers + " " + totalPlayers);
                int playerIndex = this.team.totalOvers;
                this.team.bowl(playerIndex);
                try {
                    Thread.sleep(2500);
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            System.out.println("INNINGS OVER!!!");
            System.out.println("We will have a short break before the next innings starts.\n");
            inningsEnd = true;
        }
    }
}
