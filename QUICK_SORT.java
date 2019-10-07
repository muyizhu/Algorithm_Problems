import java.util.Arrays;

public class QUICK_SORT {

    public static int[] quicksort(int[] arr){
        helper(arr,0,arr.length-1,arr.length-1);
        return arr;
    }
    public static void helper(int[] arr, int left, int right, int d){
        if(left >=right){
            return;
        }
        System.out.println(Arrays.toString(arr));
        int i = partition(arr,left,right);
        helper(arr,left,i-1,i-1);
        helper(arr,i+1,right,right);
    }
    public static int partition(int[] arr, int left, int right){
        int dummy = arr[right];
        int l = left;
        int r = right-1;
        while(l<=r){
            if(arr[l]<=dummy){
                l++;
            }
            else if(arr[r]>=dummy){
                r--;
            }
            else{
                swap(arr,l,r);
                l++;
                r--;
            }
        }
        swap(arr,l,right);
        return l;
    }

    public static void swap(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right]= temp;
    }


    public static void main(String[] args) {
        int[] a = {10,9,8,7,6,5,4};
        System.out.println(Arrays.toString(quicksort(a)));
        //quicksort(a);
    }
}
