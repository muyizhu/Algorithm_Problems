import java.util.ArrayList;
import java.util.List;

//given a string containing only digits, restore it by returning all possible valid ip address combinations.

public class RESTORE_IP_ADDRESSES {

    public static List<String> allip(String ip){
        List<String> result = new ArrayList<>();
        if(ip == null || ip.length() <4){
            return result;
        }
        StringBuilder cur = new StringBuilder();

        helper(0,2,0,ip,result,cur,0);
        return result;
    }
    //思路：以点为单位，一个点一个点加，每次加点的位置只有三个位置，begin一直到begin+2（end）。以点的数目作为dfs的结束逻辑，
    // 每加入一个点，进行递归，递归时点数增加1，表示加了多少点
    public static void helper(int begin, int end, int dot, String ip, List<String> result, StringBuilder cur,int presize){
        //结束逻辑，收集解逻辑：当dot（点的数目），为4，说明我们已经加了4个点进入cur了，所以用cur-4（4个点）来表示cur里面有多少
        //数字，一旦cur里面除了点之外的长度等于ip，那么我们已经把ip都加入cur了，所以进行收集，不然就直接return。
        if(dot == 4){
            if(cur.length()-4 == ip.length()){
                cur.setLength(cur.length()-1);
                result.add(cur.toString());
            }
            return;
        }
        for(int i = begin;i<=end;i++){
            if(Stringtoint(ip.substring(begin,i+1))<256 ) {
                cur.append(ip.substring(begin,i+1) + ".");
                helper(i+1, i+3<ip.length()?i+3:ip.length()-1, dot + 1, ip, result, cur,cur.length());
                cur.setLength(presize);
                //如果第一位是0，不需要往0后面加数字
                if(ip.charAt(begin) == '0'){
                    break;
                }
            }
        }
    }
    public static int Stringtoint(String a){
        int s = 0;
        for(int i = 0;i<a.length();i++){
            s = (int)((int)a.charAt(i)%48*Math.pow(10,a.length()-i-1))+s;
        }
        return s;
    }

    public static void main(String[] args) {
        String ip = "25525511135";
        for(String each:allip(ip)){
            System.out.println(each);
        }

    }
}
