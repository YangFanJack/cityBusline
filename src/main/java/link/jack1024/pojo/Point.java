package link.jack1024.pojo;

public class Point {
    private int id;
    //x坐标
    private int x;
    //y坐标
    private int y;
    //标记组合点类型(0:组合点;1:车站;2:交叉点)
    private int pointType;
    //点(组合段，车站，交叉点)的编号（在加入到map集合中使用）
    private int pointNum;
    //所属线路编号
    private int busLineNum;

    public Point() {
    }

    public Point(int x, int y, int pointType) {
        this.x = x;
        this.y = y;
        this.pointType = pointType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPointType() {
        return pointType;
    }

    public void setPointType(int pointType) {
        this.pointType = pointType;
    }

    public int getPointNum() {
        return pointNum;
    }

    public void setPointNum(int pointNum) {
        this.pointNum = pointNum;
    }

    public int getBusLineNum() {
        return busLineNum;
    }

    public void setBusLineNum(int busLineNum) {
        this.busLineNum = busLineNum;
    }

    @Override
    public int hashCode() {
        int x = getX()*1000 + 100000;
        int y = getY() + 100;
        return x+y;
    }

    @Override
    public boolean equals(Object obj) {
        boolean b = obj instanceof Point;
        if(!b){
            return false;
        }
        int x1 = getX()*1000 + 100000;
        int y1 = getY() + 100;
        Point objPoint = (Point) obj;
        int x2 = objPoint.getX()*1000 + 100000;
        int y2 = objPoint.getY() + 100;
        if(x1+y1 == x2+y2){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", pointType=" + pointType +
                ", pointNum=" + pointNum +
                ", busLineNum=" + busLineNum +
                '}';
    }
}
