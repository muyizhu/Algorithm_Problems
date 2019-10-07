import java.util.Arrays;

public class MERGESORT {
    public static int[] mergesort(int[] arr){
        divideandmerge(arr,0,arr.length);
        return arr;
    }

    public static void divideandmerge(int[] arr, int begin, int end){
        if(end-begin == 1){
            return;
        }
        int mid = (begin+end)/2;
        divideandmerge(arr,begin,mid);
        divideandmerge(arr,mid,end);
        int[] solution = new int[end - begin];
        merge(arr,begin,mid,end,solution);
        for(int i = begin;i<end;i++){
            arr[i] = solution[i-begin];
        }
    }

    public static void merge(int[] arr, int begin, int mid, int end, int[] solution){
        int l = begin;
        int r = mid;
        int i = 0;
        while(l<mid && r<end){
            if(arr[l] == arr[r]){
                l++;
                r++;
                solution[i++] = arr[l];
                solution[i++] = arr[r];
            }
            else if(arr[l] <= arr[r]){
                solution[i++] = arr[l];
                l++;
            }
            else{
                solution[i++] = arr[r];
                r++;
            }
        }
        if(l == mid){
            for(int j = r;j<end;j++){
                solution[i++] = arr[j];
            }
        }
        else{
            for(int j = l;j<mid;j++){
                solution[i++] = arr[j];
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {3,2,1,4,4,33,64,11};
        System.out.println(Arrays.toString(mergesort(a)));
    }
}
