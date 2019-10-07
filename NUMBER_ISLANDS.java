import java.util.*;

public class NUMBER_ISLANDS {
    //question：找到一片海里有多少片岛屿
    //      [1,0,0,0]
    //        []

    public static int islands(int[][] sea){
        int count = 0;
        for(int i = 0;i<sea.length;i++){
            for(int j = 0;j<sea[i].length;j++){
                if(sea[i][j] == 1){
                    //找到一个岛，就把岛变成海
                    dfs(i,j,sea);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfs(int row, int col, int[][] sea){
        //判断row和col两个index是否超界
        if(row >= sea.length || row < 0 || col>=sea[row].length || col<0){
            return;
        }
        //把所有和这个点连接的是1的位置都变成0，直到这个岛完全被变成海
        if(sea[row][col] == 1){
            sea[row][col]=0;
            dfs(row+1,col,sea);
            dfs(row-1,col,sea);
            dfs(row, col+1,sea);
            dfs(row,col-1,sea);
        }

    }

    /*A 2d grid map of m rows and n columns is initially filled with water. We may perform an
    addLand operation which turns the water at position (row, col) into a land. Given a list
    of positions to operate, count the number of islands after each addLand operation. An island is
    surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume
    all four edges of the grid are all surrounded by water.

    Example:

    Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
    Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).*/


    public static class UnionFind{
        int[] laodas;
        int count;
        UnionFind(int c){
            laodas = new int[c];
            count = c;
            for(int i = 0;i<c;i++){
                laodas[i] = i;
            }
        }

        int find(int i){
            if(laodas[i] == i){
                return i;
            }
            laodas[i] = find(laodas[i]);
            return laodas[i];
        }
        void union(int i, int j){
            int ilaoda = find(i);
            int jlaoda = find(j);
            if(ilaoda == jlaoda){
                return;
            }
            laodas[ilaoda] = jlaoda;
            count--;
        }
        int getcount(){
            return count;
        }
    }


    public static List<Integer> numberofislands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        HashSet<Integer> map= new HashSet<>();
        UnionFind uf = new UnionFind(m*n);
        int temp;
        int row;
        int col;
        uf.count = 0;
        for(int i = 0;i<positions.length;i++){
            row = positions[i][0];
            col = positions[i][1];
            if(row>=m||row<0||col>=n||col<0){
                continue;
            }
            uf.count++;
            temp = row*n+col;
            map.add(temp);
            if(map.contains(temp-1)) uf.union(temp-1,temp);
            if(map.contains(temp+1)) uf.union(temp+1,temp);
            if(map.contains(temp-n)) uf.union(temp-n,temp);
            if(map.contains(temp+n)) uf.union(temp+n,temp);
            result.add(uf.getcount());
        }
        return result;
    }


    public static void main(String[] args) {
        /*int[][] sea = {
                {0,0,1,1,1,0,0},
                {1,0,1,0,1,1,0},
                {0,1,0,1,0,0,0},
                {1,0,1,0,1,1,1}
        };
        System.out.println(islands(sea));*/

        int[][] positions = {
                {0,0},
                {1,0},
                {0,1},
                {1,2}
        };
        /*for(Integer each:numberofislands2(4,2,positions)){
            System.out.println(each);
        }*/
        System.out.println(numberofislands2(4,5,positions));
    }
}
