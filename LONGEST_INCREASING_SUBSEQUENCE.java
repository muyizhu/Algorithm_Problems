import java.util.*;

//Question: e.g. [1,2,3,2]->[1,2],[1,3], [1,2,3],[2,3],[1,2]
public class LONGEST_INCREASING_SUBSEQUENCE {
    //find all increasing subsequence

    public static List<List<Integer>> LIS(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        int index = 0;
        HashSet<List<Integer>> aset = new HashSet<>();
        dfshelper(arr, result, solution, index, aset);
        return result;
    }

    public static void dfshelper(int[] arr, List<List<Integer>> result, List<Integer> solution, int index, HashSet<List<Integer>> aset) {
        if (index == arr.length) {
            if (solution.size() > 1 && !aset.contains(solution)) {
                List<Integer> newsolution = new ArrayList<>();
                newsolution.addAll(solution);
                //这里不用存newsolution也行
                //是不是因为只要solution里的值不一样，solution每次进set的hashcode就会不一样，即便是被同一个变量指向，也会被存在不同的地方
                //而一旦出现重复解，hashcode也会再次一样，所以也可以达到效果
                aset.add(solution);
                result.add(newsolution);
            }
            return;
        }
        if (solution.size() == 0 || arr[index] > solution.get(solution.size() - 1)) {
            solution.add(arr[index]);
            dfshelper(arr, result, solution, index + 1, aset);
            solution.remove(solution.size() - 1);
        }
        dfshelper(arr, result, solution, index + 1, aset);
    }

    //dfs: find only one loggest solution
    public static List<List<Integer>> LIS1(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        int index = 0;
        HashSet<List<Integer>> aset = new HashSet<>();
        //和LIS一模一样，除了这里多加了一个max一直track最长的list
        int[] max = new int[1];
        dfshelper1(arr, result, solution, index, aset, max);
        return result;
    }

    public static void dfshelper1(int[] arr, List<List<Integer>> result, List<Integer> solution, int index, HashSet<List<Integer>> aset, int[] max) {
        if (index == arr.length) {
            if (solution.size() > 1 && !aset.contains(solution) && solution.size() > max[0]) {
                result.clear();
                List<Integer> newsolution = new ArrayList<>();
                newsolution.addAll(solution);
                aset.add(solution);
                result.add(newsolution);
                max[0] = solution.size();
            } else if (solution.size() == max[0]) {
                List<Integer> newsolution = new ArrayList<>();
                newsolution.addAll(solution);
                aset.add(solution);
                result.add(newsolution);
            }
            return;
        }
        if (solution.size() == 0 || arr[index] > solution.get(solution.size() - 1)) {
            solution.add(arr[index]);
            dfshelper1(arr, result, solution, index + 1, aset, max);
            solution.remove(solution.size() - 1);
        }
        dfshelper1(arr, result, solution, index + 1, aset, max);
    }


    //递归方法：所有递增子序列（size >=2）my best fake method so far
    public static List<List<Integer>> mybestLIS(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        recursionhelper(arr, arr.length - 1, result);
        return result;
    }

    public static void recursionhelper(int[] arr, int index, List<List<Integer>> result) {
        if (index == 0) {
            result.add(new ArrayList<>());
            result.get(0).add(arr[index]);
            result.add(new ArrayList<>());
            return;
        }
        recursionhelper(arr, index - 1, result);
        for (List<Integer> each : new ArrayList<>(result)) {
            each = new ArrayList<>(each);
            if (each.size() == 0 || arr[index] > each.get(each.size() - 1)) {
                each.add(arr[index]);
                result.add(each);
            }
        }
    }

     //real best solution ：find only the length。
    public static int RealLIS1(int[] arr) {
        int max=0;
        int[] dp = new int[arr.length];
        for(int i = 0;i<arr.length;i++){
            max = Math.max(RealLIShelper1(arr,i,dp),max);
        }
        return max;
    }

    public static int RealLIShelper1(int[] arr, int index, int[] dp) {
        int max = 1;
        for (int i = 0; i < index; i++) {
            if (arr[i]<arr[index]) {
                if(dp[i]==0) {
                    dp[i] = RealLIShelper1(arr, i, dp);
                    max = Math.max( dp[i]+ 1, max);
                }
                else{
                    max = Math.max(dp[i] + 1, max);
                }

            }
        }
        return max;
    }

    public static List<List<Integer>> RealLIS(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0;i<arr.length;i++){
            result.addAll(RealLIShelper(arr,i));
        }
        return result;
    }

    public static List<List<Integer>> RealLIShelper(int[] arr, int index) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        solution.add(arr[index]);
        result.add(solution);
        for (int i = 0; i < index; i++) {
            if (arr[i]<arr[index]) {
                result = RealLIShelper(arr,i);
                for(List<Integer> each:result){
                    each.add(arr[index]);
                }
            }
        }
        return result;
    }

    public static int LIS3(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        int max = 1;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(recursionhelper1(arr, i), max);
        }
        return max;
    }

    public static int recursionhelper1(int[] arr, int index) {
        int max = 1;
        for (int i = index - 1; i >= 0; i--) {
            if (arr[i] < arr[index]) {
                max = Math.max(recursionhelper1(arr, i) + 1, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] test = {6, 7, 8, 9, 0, 1, 2, 3};
        for (List<Integer> each : RealLIS(test)) {
            System.out.println(Arrays.toString(each.toArray()));
        }
        System.out.println(RealLIS1(test));
    }
}
