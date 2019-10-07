import java.util.*;

public class KEEP_DISTANCE {
    // e.g. 3->[3,1,2,1,3,2]
    //complexity:
    //      time: k^k  space:2*k
    public static int[] keepdistance(int k) {
        int[] result = new int[2 * k];
        dfshelper(result, k);

        return result;
    }

    // find one solution
    public static boolean dfshelper(int[] result, int k) {
        if (k == 0) {

            return true;
        }
        for (int i = 0; i < result.length - k - 1; i++) {
            if (result[i] == 0 && result[i + k + 1] == 0) {
                result[i] = k;
                result[i + k + 1] = k;
                if (dfshelper(result, k - 1)) {
                    return true;
                }
                result[i] = 0;
                result[i + k + 1] = 0;
            }

        }
        return false;

    }

    //find all solutions
    // complexity:
    //      time: k^k（不清楚对不对） space: k^2
    public static List<int[]> keepdistance1(int k) {
        List<int[]> results = new ArrayList<>();
        int[] a = new int[2 * k];
        dfshelper1(results, k,a);
        return results;
    }

    public static void dfshelper1(List<int[]> results, int k,int[] result) {
        if (k == 0) {
            int[] newarray = new int[result.length];
            //System.out.println(Arrays.toString(results.get(results.size()-1)));
            //每当k等于0，说明每一对k都有地方放并且已经放完了，所以可以直接建一个新的数组，开始找新的解
            //注意：这里需要复制原本的数组，因为是dfs，return会返回到上一步，把当前可能的sequence全部找完才会再次return，所以需要保持数组是上一步的样子
            //如果创建的数组初始化为0，那么就会导致[1,0,1,0,0],[0,1,0,1,0]这样的结果。（一开始我就初始化为0，就发现错了）
            System.arraycopy(result,0,newarray,0,newarray.length);
            results.add(newarray);
            return;
        }
        //这里i<result.length-k-1，第一次写成result.length-k
        for (int i = 0; i < result.length - k - 1; i++) {

            if (result[i] == 0 && result[i + k + 1] == 0) {
                result[i] = k;
                result[i + k + 1] = k;
                //这里不再需要dfshelper为true的条件，因为我需要它不断for，而不是找到一个就返回。
                //这里想了半天需不需要用boolean
                dfshelper1(results, k - 1,result);
                //这里需要更新result指向新建的数组，不然它永远操作第一个数组，就永远只能拿到一个答案
                //result = results.get(results.size()-1);
                result[i] = 0;
                result[i + k + 1] = 0;
            }
        }
    }

    // all solutions, one by one
    public static List<List<Integer>> keepdistance2(int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        int index = 0;
        HashMap<Integer,Integer> amap = new HashMap<>(4*k);
        for(int i = 1;i<2*k+1;i++){
            amap.put(i,0);
        }
        dfshelper2(result, solution, index, k, amap);
        return result;

    }

    public static void dfshelper2(List<List<Integer>> result, List<Integer> solution, int index, int k, HashMap<Integer,Integer> amap) {
        if (index == 2 * k) {
            List<Integer> temp = new ArrayList<>();
            for (int each : solution) {
                temp.add(each);
            }

            result.add(temp);
            return;
        }
        for (int i = 1; i < k + 1; i++) {
           if((amap.get(i)==1 && (index-i-1>=0 && solution.get(index-i-1) == i)) || amap.get(i)==0 ){
               solution.add(i);
               amap.put(i,amap.get(i)+1);
               dfshelper2(result, solution, index + 1, k, amap);
               amap.put(i,amap.get(i)-1);
               solution.remove(solution.size() - 1);
           }

        }

    }

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(keepdistance(5)));
        for (List<Integer> each : keepdistance2(12)) {
            System.out.println(Arrays.toString(each.toArray()));
        }
        /*HashMap<Integer,Integer> amap = new HashMap<>();
        amap.put(1,0);
        System.out.println(amap.get(1));
        amap.put(1,10);
        System.out.println(amap.get(1));*/
        /*for (int[] each : keepdistance1(7)) {
            System.out.println(Arrays.toString(each));
        }*/
        //System.out.println(keepdistance2(3).size());

    }
}
