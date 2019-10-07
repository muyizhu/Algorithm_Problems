import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Character.MIN_VALUE;

//
public class LARGEST_SUBARRAY_SUM {
    public static int ISS(int[] arr){
        int curmax;
        int realmax=MIN_VALUE;
        int pre = 0;
        for(int i = arr.length-1;i>=0;i--){
            curmax = arr[i];
            curmax = Math.max(curmax+pre, curmax);
            pre = curmax;
            realmax = Math.max(realmax,curmax);
        }
        return realmax;
    }

    //dfs
    public static List<List<Integer>> dfsISS(int[] arr){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if(arr == null || arr.length ==0){
            return result;
        }
        dfshelper(result,cur,arr,0);
        return result;
    }

    public static void dfshelper(List<List<Integer>> result, List<Integer> cur, int[] arr, int index){
        if(cur.size()>0){
            result.add(new ArrayList<>(cur));
        }
        for(int i = index;i<arr.length;i++){
            if( index == 0 || i == index){
                cur.add(arr[i]);
                dfshelper(result,cur,arr,i+1);
                cur.remove(cur.size()-1);
            }
        }
    }

    //加不加
    public static List<List<Integer>> recursionISS(int[] arr){
       return recursionhelper(arr,arr.length-1);
    }
    public static List<List<Integer>> recursionhelper(int[] arr, int index){
        if(index == 0){
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> cur = new ArrayList<>();
            cur.add(arr[index]);
            result.add(new ArrayList<>(cur));
            return result;
        }
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> pre = recursionhelper(arr,index-1);
        for(List<Integer> each:pre){
            each = new ArrayList<>(each);
            if(arr[index-1] == each.get(each.size()-1)) {
                each.add(arr[index]);
                result.add(new ArrayList<>(each));
            }
        }
        result.addAll(pre);
        result.add(Arrays.asList(arr[index]));
        return result;
    }

    public static int ISS_sum(int[] arr){
        int largest = Integer.MIN_VALUE;
        for(int i = 0;i<arr.length;i++){
            largest = Math.max(sumhelper(arr,i),largest);
        }
        return largest;
    }
    public static int sumhelper(int[] arr, int index){
        if(index == arr.length-1){
            return arr[index];
        }
        int pre;
        int largest = arr[index];
        for(int i = index+1;i<arr.length;i++){
            if(i - index == 1){
                pre = sumhelper(arr,i);
                System.out.println(pre+arr[index]+" "+largest);
                largest = Math.max(pre+arr[index],largest);
            }
        }
        return largest;
    }

    public static void main(String[] args) {
        int[] a = {10,1,-1,8};
        System.out.println(ISS_sum(a));
    }
}
