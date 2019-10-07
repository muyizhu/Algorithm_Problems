public class SPARSE_ALGORITHM {
    //Two int arrays with same length, how to get the dot product?
    public static class Pair{
        int value;
        int index;
    }

    public static int dotproduct(int[] arr1, int[] arr2){
        int len = arr1.length>arr2.length? arr2.length:arr1.length;
        int sum = 0;
        for(int i = 0;i<len;i++){
            if((arr1[i]&arr2[i])!=0){
                sum = sum+arr1[i]*arr2[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {

    }
}
