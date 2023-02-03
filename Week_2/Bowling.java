package Assignments.Week_2;

public class Bowling implements Runnable {
    Team team;
    int totalPlayers;
    int totalOvers = 0;
    public Bowling(Team team) {
        this.team = team;
        this.totalPlayers = this.team.teamPlayers.size();
        Thread t = new Thread(this,"Bowling");
        t.start();
    }

    @Override
    public void run() {
        while(this.totalOvers!=totalPlayers-1) {
            int playerIndex = this.totalOvers;
            this.team.bowl(playerIndex);
            this.totalOvers = this.team.totalOvers;
            try {
                Thread.sleep(5000);
            } catch (Exception e) {e.printStackTrace();}
        }
    }
}
