/* QUESTION: Given an array, A, of n positive integers, each of which
    appears in A exactly twice, except for one integer, x,
    describe an O(n)-time method for finding x using only a
    single variable besides A*/
public class BIT_OPERATION {

    public static int findmissing(int[] a){
        // Solution: Using xor
        for(int i = 1;i<a.length;i++){
            a[0] = a[0]^a[i];
        }
        return a[0];

    }


    public static void main(String[] args) {
        int[] a = {1,2,3,4,1,2,3};

        System.out.println(findmissing(a));
    }
}
