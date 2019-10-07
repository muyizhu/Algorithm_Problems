public class GRAPH_ALGORITHM {
    public static int[][] party = {
            {1,0,1,1},
            {1,1,1,0},
            {0,0,1,0},
            {1,1,1,1},
    };

    public static boolean isrec(int i, int j){
        if(party[i][j] == 1){
            return true;
        }
        return false;
    }

    public static int findcele(int n){
        int c = 0;
        for(int i = 0;i<n;i++){
            if(isrec(c,i)){
                c=i;
            }
        }
        for(int i = 0;i<n;i++){
            if(i<c && isrec(c,i)){
                return -1;
            }
            if(i<c && !isrec(i,c)){
                return -1;
            }
            if(i>c && !isrec(i,c)){
                return -1;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(findcele(4));
    }
}
