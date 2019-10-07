public class Lambda_Learning {

    interface c{
        int a(int a,int b);

    }

    public static int add(int a,int b,c c){
        return c.a(a,b);
    }

    public static void main(String[] args) {

    }
}
