package Assignments.Week_1;

import java.util.ArrayList;
import java.util.Random;
public class Team {
    public int total_runs=0;
    public int total_wickets=10;
    public void generateRuns() {
        ArrayList<String> runs = new ArrayList<String>();
        runs.add("0");
        runs.add("1");
        runs.add("2");
        runs.add("3");
        runs.add("4");
        runs.add("5");
        runs.add("6");
        runs.add("W");
        Random rand = new Random();
        String run = runs.get(rand.nextInt(runs.size()));
        if(run.equals("W")) {
            countWickets();
        } else {
            addRuns(run);
        }
    }

    public void addRuns(String run) {
        this.total_runs += Integer.valueOf(run);
    }

    public void countWickets() {
        this.total_wickets--;
    }
}
