import java.util.*;

public class REMOVE_ANAGRAMS {

    public static String[] helper(List<String> strings){
        HashMap<Integer,String> set = new HashMap<>();
        int str;
        for(String each:strings){
            str = 0;
            for(int i = 0;i<each.length();i++){
                str = str | (int)Math.pow(2,(each.charAt(i)%97));
            }
            set.put(str,each);
        }

        String[] result = new String[set.size()];
        int i = 0;
        for(int key:set.keySet()){
            result[i++] = set.get(key);
        }
        return result;
    }

    public static List<String> removeanagrams(String[] strings){
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int str;
        //按照每一个string的长度分块 // n
        HashMap<Integer,List<String>> Strings = new HashMap<>();
        for(String each:strings){
            if(!Strings.containsKey(each.length())){
                List<String> list = new ArrayList<>();
                list.add(each);
                Strings.put(each.length(),list);
            }
            else{
                Strings.get(each.length()).add(each);
            }
        }
        //分完了， 把每一块拿出来去重，然后合并成为没有anagram的String列表
        List<String> result = new ArrayList<>();
        for(int key:Strings.keySet()){
            List<String> Strs= Strings.get(key);
            for(String each:helper(Strs)){
                result.add(each);
            }
        }
        //去重完了，排序这个列表
        sortStrings(result,1);
        return result;
    }
    public static void sortStrings(List<String> strings,int accuracy){
        int[] arr = new int[strings.size()];
        int inte=0;
        int index = 0;

        HashMap<Integer,String> set = new HashMap<>();
        for(String each:strings){
            accuracy = accuracy>each.length()?each.length():accuracy;
            for(int i = 0;i<accuracy;i++){
                inte = inte + (int)(Math.pow((each.charAt(i)%26),10-(26*i)));
            }
            arr[index++] = inte;
            set.put(inte,each);
        }
        Arrays.sort(arr);
        index = 0;
        for(int key:arr){
            strings.set(index++,set.get(key));
        }
    }

    public static void main(String[] args) {
        String[] b = {"abbc","cba","bac","abcd"};
        List<String> a = removeanagrams(b);
        System.out.println(Arrays.toString(a.toArray()));
    }
}
