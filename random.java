public class random {

    public static int remainder(int x, int n, int p){
        if(n == 1){
            return x%p;
        }
        int a = remainder(x,n/2,p);
        return a*a%p;
    }


    public static void main(String[] args) {
        System.out.println(remainder(7,3,3));

    }
}
