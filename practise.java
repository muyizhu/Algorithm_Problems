import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class practise {

    public static List<List<Integer>> AllSubArray(int [] nums){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        DFSHelper(result,cur,nums,nums.length);
        return result;
    }
    private static void DFSHelper(List<List<Integer>> result,List<Integer> cur,int [] nums, int index){
        if(cur.size() >0){
            result.add(new ArrayList<Integer>(cur));

        }
        for(int i = nums.length-1; i>=0; i--){
            if(index - i == 1){
                cur.add(nums[i]);
                DFSHelper(result,cur,nums,i);
                cur.remove(cur.size() - 1);
            }
        }
    }
    public class a{
        int i = 10;
        protected int ii = 10;
        void print(){
            System.out.println(i);
        }
    }
    public class b extends a{
        void print(){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        for(List<Integer> each:AllSubArray(a)) {
            System.out.println(Arrays.toString(each.toArray()));
        }

    }

}
