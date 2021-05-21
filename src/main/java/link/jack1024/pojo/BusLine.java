package link.jack1024.pojo;

import java.util.ArrayList;

public class BusLine {
    private int id;
    //线路编号
    private int lineNum;
    //线路票价
    private double linePrice;
    //首班车时间
    private String beginTime;
    //末班车时间
    private String endTime;
    //线路组合点集合
    private ArrayList<Point> points;
    //线路车站集合
    private ArrayList<Station> stations;
    //线路组合点车站集合
    private ArrayList<Point> pointStations;
    //线路车站间的距离
    private ArrayList<Integer> distance;
    //线路的组合点，车站，交叉点整合
    private ArrayList<Point> allPoints;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public double getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(double linePrice) {
        this.linePrice = linePrice;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public ArrayList<Point> getPointStations() {
        return pointStations;
    }

    public void setPointStations() {
        this.pointStations = new ArrayList<>();
        pointStations.addAll(points);
        for(Station s : stations){
            int stationX = s.getX();
            int stationY = s.getY();
            for(int i=0;i<pointStations.size()-1;i++){
                if(s.equals(pointStations.get(i))){
                    pointStations.remove(pointStations.get(i));
                    pointStations.add(i,s);
                }
                else if(s.equals(pointStations.get(i+1))){
                    pointStations.remove(pointStations.get(i+1));
                    pointStations.add(i+1,s);
                }
                else{
                    int p1X = Math.min(pointStations.get(i).getX(),pointStations.get(i+1).getX());
                    int p1Y = Math.min(pointStations.get(i).getY(),pointStations.get(i+1).getY());
                    int p2X = Math.max(pointStations.get(i).getX(),pointStations.get(i+1).getX());
                    int p2Y = Math.max(pointStations.get(i).getY(),pointStations.get(i+1).getY());
                    if(p1Y==p2Y && stationY==p1Y && stationX>=p1X && stationX<=p2X){
                        pointStations.add(i+1,s);
                        break;
                    }
                    if(p1X==p2X && stationX==p1X && stationY>=p1Y && stationY<=p2Y){
                        pointStations.add(i+1,s);
                        break;
                    }
                }

            }
        }
    }

    public ArrayList<Integer> getDistance() {
        return distance;
    }

    public void setDistance() {
        this.distance = new ArrayList<>();
        for(int i=0;i<allPoints.size()-1;i++){
            int distanceX = Math.abs(allPoints.get(i).getX() - allPoints.get(i+1).getX());
            int distanceY = Math.abs(allPoints.get(i).getY() - allPoints.get(i+1).getY());
            distance.add(distanceX+distanceY);
        }
    }

    public ArrayList<Point> getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(ArrayList<BusLine> busLines) {
        this.allPoints = new ArrayList<>();
        allPoints.addAll(pointStations);
        for(int i=0;i<allPoints.size()-1;i++){
            int ix = allPoints.get(i).getX();
            int iy = allPoints.get(i).getY();
            int i1x = allPoints.get(i+1).getX();
            int i1y = allPoints.get(i+1).getY();
            for(BusLine b : busLines){
                if(b.getLineNum() != this.lineNum){
                    for(int j=0;j<b.pointStations.size()-1;j++){
                        int jx = b.pointStations.get(j).getX();
                        int jy = b.pointStations.get(j).getY();
                        int j1x = b.pointStations.get(j+1).getX();
                        int j1y = b.pointStations.get(j+1).getY();
                        if(jy == j1y && ix == i1x){//纵横相交
                            if(ix<=Math.max(j1x,jx) && ix>=Math.min(j1x,jx) && jy<=Math.max(i1y,iy) && jy>=Math.min(i1y,iy)){
                                Point point = new Point(ix, jy, 3);
                                //添加一个判断（不能重复添加）
                                boolean isExists = false;
                                for(Point p : allPoints){
                                    if(p.equals(point)){
                                        isExists = true;
                                    }
                                }
                                if(!isExists){
                                    allPoints.add(i+1,point);
                                }
                            }
                        }
                        else if(jx == j1x && iy == i1y){//横纵相交
                            if(jx<=Math.max(i1x,ix) && jx>=Math.min(i1x,ix) && iy<=Math.max(j1y,jy) && iy>=Math.min(j1y,jy)){
                                Point point = new Point(jx, iy, 3);
                                //添加一个判断（不能重复添加）
                                boolean isExists = false;
                                for(Point p : allPoints){
                                    if(p.equals(point)){
                                        isExists = true;
                                    }
                                }
                                if(!isExists){
                                    allPoints.add(i+1,point);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "BusLine{" +
                "id=" + id +
                ", lineNum=" + lineNum +
                ", linePrice=" + linePrice +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", points=" + points +
                ", stations=" + stations +
                ", pointStations=" + pointStations +
                ", distance=" + distance +
                ", allPoints=" + allPoints +
                '}';
    }

    public void setType(){
        for(Station s : stations){
            s.setPointType(2);
        }
        for(Point p : points){
            p.setPointType(1);
        }
    }

    public void setStationsOrder(){
        stations.clear();
        for(Point p : pointStations){
            if(p.getPointType()==2){
                Station s = (Station) p;
                stations.add(s);
            }
        }
    }
}
