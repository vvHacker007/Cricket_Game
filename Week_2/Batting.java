package Assignments.Week_2;

public class Batting implements Runnable {
    Team team;
    int totalPlayers;
    int totalWickets = 0;
    public Batting(Team team) {
        this.team = team;
        this.totalPlayers = this.team.teamPlayers.size();
        Thread t = new Thread(this,"Batting");
        t.start();
    }

    @Override
    public void run() {
        while(this.totalWickets!=totalPlayers-1) {
            int playerIndex = this.totalWickets;
            this.team.bat(playerIndex);
            this.totalWickets = this.team.totalWickets;
            try {
                Thread.sleep(5000);
            } catch (Exception e) {e.printStackTrace();}
        }
    }
}
