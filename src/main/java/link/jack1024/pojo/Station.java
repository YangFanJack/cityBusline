package link.jack1024.pojo;

import java.util.ArrayList;

public class Station extends Point{
    private int id;
    //车站名
    private String stationName;
    //经过该车站的线路集合
    private ArrayList<BusLine> busLines;

    public Station(int x, int y, int pointType) {
        super(x, y, pointType);
    }

    public Station() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public ArrayList<BusLine> getBusLines() {
        return busLines;
    }

    public void setBusLines(ArrayList<BusLine> busLines) {
        this.busLines = busLines;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", x=" + super.getX() +
                ", y=" + super.getY() +
                ", pointType=" + super.getPointType() +
                ", pointNum=" + super.getPointNum() +
                ", busLineNum=" + super.getBusLineNum() +
                ", stationName='" + stationName +
                ", busLines=" + busLines +
                '}';
    }

    public boolean stationInLine(ArrayList<Point> points){
        int stationX = this.getX();
        int stationY = this.getY();
        for(int i=0;i<points.size()-1;i++){
            int p1X = Math.min(points.get(i).getX(),points.get(i+1).getX());
            int p1Y = Math.min(points.get(i).getY(),points.get(i+1).getY());
            int p2X = Math.max(points.get(i).getX(),points.get(i+1).getX());
            int p2Y = Math.max(points.get(i).getY(),points.get(i+1).getY());
            if(p1Y==p2Y && stationY==p1Y && stationX>=p1X && stationX<=p2X){
                return true;
            }
            if(p1X==p2X && stationX==p1X && stationY>=p1Y && stationY<=p2Y){
                return true;
            }
        }
        return false;
    }
}
