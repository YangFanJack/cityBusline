package link.jack1024.pojo;

import java.util.ArrayList;

public class ShortestPlan {
    public static int[][] path;
    public static int[] shortest;

    private int[] searchPath;
    private int searchShortest;
    private ArrayList<ArrayList<Point>> searchPathStation;

    public int[] getSearchPath() {
        return searchPath;
    }

    public void setSearchPath(int[] searchPath) {
        this.searchPath = searchPath;
    }

    public int getSearchShortest() {
        return searchShortest;
    }

    public void setSearchShortest(int searchShortest) {
        this.searchShortest = searchShortest;
    }

    public ArrayList<ArrayList<Point>> getSearchPathStation() {
        return searchPathStation;
    }

    public void setSearchPathStation(ArrayList<ArrayList<Point>> searchPathStation) {
        this.searchPathStation = searchPathStation;
    }
}
