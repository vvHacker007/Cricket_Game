package Assignments.Week_1;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Team {
    ArrayList<String> runs = new ArrayList<>();
    public int total_runs = 0;
    public int total_wickets = 10;
    public String team_name = "";

    public Team( String team_name ) {
        this.runs.add( "0" );
        this.runs.add( "1" );
        this.runs.add( "W" );
        this.runs.add( "5" );
        this.runs.add( "6" );
        this.runs.add( "W" );
        this.runs.add( "2" );
        this.runs.add( "3" );
        this.runs.add( "W" );
        this.runs.add( "4" );
        this.runs.add( "W" );
        this.team_name = team_name;
    }

    public void play() throws InterruptedException {
        Random rand = new Random();
        String run = this.runs.get( rand.nextInt( this.runs.size() ) );
        if( run.equals( "W" ) ) {
            countWickets();
        } else {
            addRuns( run );
        }
        sleep( 1000 );
    }

    public void addRuns( String run ) {
        this.total_runs += Integer.valueOf( run );
        System.out.println( "Team " + this.team_name + " scored a " + run + " which makes it a total of " + this.total_runs );
    }

    public void countWickets() {
        this.total_wickets--;
        System.out.println( "Wickets were hit!!\nTotal of " + this.total_wickets + " wickets are remaining and Team " + this.team_name + " has scored a total of " + this.total_runs + " runs" );
    }
}
