package link.jack1024.pojo;

import sun.applet.AppletListener;

import java.lang.reflect.Array;
import java.util.*;

public class BusMap {
    //邻接数组空标记
    public static final int M = -1;
    //Map中所有点的计数器
    public static int pointCount = 0;
    //公交地图表示的城市名
    private String cityName;
    //邻接矩阵
    private int[][] mapArray;
    //所有线路
    private ArrayList<BusLine> busLines;
    //所有点（组合点，车站，交叉点）
    private LinkedHashSet<Point> allPointSet;
    //点编号：编号对应的点的集合
    private HashMap<Integer,ArrayList<Point>> countPointHash;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int[][] getMapArray() {
        return mapArray;
    }

    public void setMapArray(int[][] mapArray) {
        this.mapArray = mapArray;
    }

    //完成map邻接矩阵
    public void setMap() {
//        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//        System.out.println(allPointSet.size());
//        for(Point p : allPointSet){
//            System.out.println(p);
//        }
        mapArray = new int[allPointSet.size()][allPointSet.size()];
        //初始化邻接矩阵
        for(int i=0;i<mapArray.length;i++){
            for(int j=0;j<mapArray[i].length;j++){
                if(i==j){
                    mapArray[i][j]=0;
                }
                else{
                    mapArray[i][j]=M;
                }
            }
        }
        for(int i=0;i<this.getBusLines().size();i++){
            ArrayList<Point> allPoints = this.getBusLines().get(i).getAllPoints();
            ArrayList<Integer> distance = this.getBusLines().get(i).getDistance();
            for(int j=0;j<allPoints.size()-1;j++){
//                System.out.println(allPoints.get(j).getPointNum()+"   :   "+allPoints.get(j+1).getPointNum());
                //无向图的邻接矩阵是对称的
                mapArray[allPoints.get(j).getPointNum()][allPoints.get(j+1).getPointNum()] = distance.get(j);
                mapArray[allPoints.get(j+1).getPointNum()][allPoints.get(j).getPointNum()] = distance.get(j);
            }
        }
    }

    public ArrayList<BusLine> getBusLines() {
        return busLines;
    }

    public void setBusLines(ArrayList<BusLine> busLines) {
        this.busLines = busLines;
    }

    public LinkedHashSet<Point> getAllPointSet() {
        return allPointSet;
    }

    public void setAllPointSet() {
        BusMap.pointCount = 0;
        allPointSet = new LinkedHashSet<>();
        //添加所有点（组合点，车站，交叉点）到点set集合中
        for(BusLine b : busLines){
            ArrayList<Point> allPoints = b.getAllPoints();
            for(Point p : allPoints){
                addPoint(p);
            }
        }
    }
    /**
     * 添加点到map对象的Set集合(确保唯一性)中
     * @param point
     */
    private void addPoint(Point point){
        boolean b = this.allPointSet.add(point);
        if(b){
            point.setPointNum(BusMap.pointCount);
            BusMap.pointCount++;
        }
        else{
            Point point1 = findPoint(point);
            point.setPointNum( point1.getPointNum());
        }
    }
    /**
     * 根据x，y坐标查找点（组合点，车站，交叉点）
     * @param point
     * @return
     */
    private Point findPoint(Point point){
        for(Point p : allPointSet){
            if(p.getX()==point.getX() && p.getY()==point.getY()){
                return p;
            }
        }
        return null;
    }

    public HashMap<Integer, ArrayList<Point>> getCountPointHash() {
        return countPointHash;
    }

    @Override
    public String toString() {
        return "BusMap{" +
                "cityName='" + cityName + '\'' +
                ", mapArray=" + Arrays.toString(mapArray) +
                ", busLines=" + busLines +
                ", allPointSet=" + allPointSet +
                ", countPointHash=" + countPointHash +
                '}';
    }

    public void setCountPointHash() {
        countPointHash = new HashMap<>();
        for(int i=0;i<allPointSet.size();i++){
            countPointHash.put(i,findPointByCount(i));
        }
    }

    //通过点编号找到点
    private ArrayList<Point> findPointByCount(int count){
        ArrayList<Point> points = new ArrayList<Point>();
        for(int i=0;i<this.getBusLines().size();i++){
            ArrayList<Point> allPoints = this.getBusLines().get(i).getAllPoints();
            for(Point p : allPoints){
                if(p.getPointNum() == count){
                    points.add(p);
                }
            }
        }
        return points;
    }


    //Dijkstra算法求最短路径
    public void recommend(int orig){
        //寻找最短路径
        dijkstra_alg(orig);

        if(ShortestPlan.shortest == null){
            System.out.println("没有最短路径了");
            return;
        }

        for(int i=0; i< ShortestPlan.shortest.length; i++){
            System.out.println("从" + (orig) + "出发到" + (i) + "的最短距离为："+ ShortestPlan.shortest[i]);
        }
    }
    private void dijkstra_alg(int orig) {

        int n =mapArray.length;       //顶点的个数
        int[] shortest = new int[n];  //存放从start到其他节点的最短路径
        boolean[] visited = new boolean[n]; //标记当前该顶点的最短路径是否已经求出，true表示已经求出


        // 初始化，第一个顶点求出
        shortest[orig] = 0;
        visited[orig] = true;

        //存放从start到其他各节点的最短路径
        String[] path = new String[n];

        for(int i = 0; i < n; i++){
            path[i] = orig + "--->" + i;
        }

        for(int count = 0; count != n-1; count ++)
        {
            //选出一个距离初始顶点最近的为标记顶点
            int k = M;
            int min = M;
            for(int i =0; i< n ; i++)//遍历每一个顶点
            {
                if( !visited[i] && mapArray[orig][i] != M) //如果该顶点未被遍历过且与orig相连
                {
                    if(min == -1 || min > mapArray[orig][i]) //找到与orig最近的点并更新最短距离
                    {
                        min = mapArray[orig][i];
                        k = i;
                    }
                }
            }
            //正确的图生成的矩阵不可能出现K== M的情况，除非没有和orig线相邻的情况框
            if(k == M)
            {
                System.out.println("the input adjacent matrix is wrong!");
                return;
            }
            shortest[k] = min;
//            System.out.println("~~~~~~~~~~~~~~~");
//            System.out.println(k);
            visited[k] = true;
            //以k为中心点，更新oirg到未访问点的距离
            for (int i = 0; i < n; i++)
            {
                if (!visited[i] && mapArray[k][i] != M)//如果该顶点未被遍历过且与k相连
                {
//                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    int callen = min + mapArray[k][i];
//                    System.out.println(mapArray[orig][i]);
//                    System.out.println(mapArray[orig][i] > callen);
//                    System.out.println(callen);
                    if (mapArray[orig][i] == M || mapArray[orig][i] > callen)//更新oirg到未访问点的距离
                    {
//                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        mapArray[orig][i] = callen;
                        path[i] = path[k] + "--->" + i;
                    }
                }
            }
        }

        for(int i=0;i<n;i++){
            System.out.println("从"+orig+"出发到"+i+"的最短路径为："+path[i]);
        }
        ShortestPlan.path = new int[n][];
        for(int i=0;i<n;i++){
            String[] split = path[i].split("--->");
            int[] path2 = new int[split.length];
            for(int j=0;j<path2.length;j++){
                path2[j] = Integer.parseInt(split[j]);
            }
            ShortestPlan.path[i] = path2;
            System.out.println(Arrays.toString(path2));
        }
        System.out.println("=====================================");

        ShortestPlan.shortest = shortest;
    }
}
