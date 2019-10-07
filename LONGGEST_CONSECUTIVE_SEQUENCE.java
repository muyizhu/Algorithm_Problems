import java.util.*;

//Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//Your algorithm should run in O(n) complexity.

   /* EXAMPLE：Input: [100, 4, 200, 1, 3, 2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.*/

public class LONGGEST_CONSECUTIVE_SEQUENCE {
    public static int LCS(int[] arr){
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0;i<arr.length;i++){
            set.add(arr[i]);
        }
        int count = 0;
        int num;
        int max=0;
        int left;
        int right;
        Iterator<Integer> iter = set.iterator();
        while(iter.hasNext()){
            num = iter.next();
            left = num-1;
            right = num + 1;
            while(set.contains(left)){
                set.remove(left);
                left--;
                count++;
            }
            while(set.contains(right)){
                set.remove(right);
                right++;
                count++;
            }
            set.remove(num);
            max = Math.max(count,max);
            iter = set.iterator();
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {100,101,104,106,108,107};
        System.out.println(LCS(a));
    }

}
