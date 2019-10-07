/*Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, return if the string matches the abbreviation.

        Assumptions:
        The original string only contains alphabetic characters.
        Both input and pattern are not null.
        Pattern would not contain invalid information like "a0a","0".
        Examples:
        pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.*/


import java.util.ArrayList;
import java.util.List;

public class BOOK_ABBREVIATION {

  /*  public static boolean isAbbreviation(String book, String abb){
        return abbhelper(book,abb,0,0);
    }
    public static boolean abbhelper(String book, String abb, int left, int right){
        if(left == book.length() && right == abb.length()){
            return true;
        }
        else if(!(left<book.length() && right<abb.length())){
            return false;
        }
        else if(book.charAt(left) == abb.charAt(right) ){
            return abbhelper(book,abb,left+1,right+1);
        }
        else{
            for(int i = 1; i<book.length()-left+1;i++){
                if((i+"").equals(abb.charAt(right)+"")){
                    return abbhelper(book,abb,i+left,right+1);
                }
            }
            return false;
        }
    }*/

    public static List<String> getabbreviations(String book){
        List<String> result = new ArrayList<>();
        StringBuilder b = new StringBuilder();
        getabbhelper(book,result,b,0,0);
        return result;
    }

    public static void getabbhelper(String book, List<String> result, StringBuilder solution, int index, int count){
        if(index == book.length()){
            int len = solution.length();
            if(count>0){
                solution.append(count);
            }
            result.add(solution.toString());
            solution.setLength(len);
            return;
        }
        getabbhelper(book,result,solution,index+1,count+1);
        int len = solution.length();
        if(count>0){
            solution.append(count);
        }
        solution.append(book.charAt(index));
        getabbhelper(book,result,solution,index+1,0);
        solution.setLength(len);

    }

    /*//find all abbreviations of the book
    public static List<String> abbreviation(String book){
        List<String> result = new ArrayList<>();
        List<String> solution = new ArrayList<>();
        int index = 0;
        int signal = -1;
        int abb = 0;
        abbhelper(book,result,solution,abb,index,signal);
        return result;

    }

    public static void abbhelper(String book, List<String> result, List<String> solution, int abb, int index, int signal){
        if(index == book.length()){
            if(abb>0){
                return;
            }
            String s = "";
            for(String each:solution){
                s+=each;
            }
            result.add(s);
            return;
        }
        if(signal == -1){
            solution.add(book.charAt(index)+"");
            abbhelper(book,result,solution,abb,index+1,-1);
            solution.remove(solution.size()-1);
            solution.add("1");
            abbhelper(book,result,solution,abb,index+1,0);
            solution.remove(solution.size()-1);
            abbhelper(book,result,solution,1,index+1,1);

        }
        else if (signal == 0){
            solution.add(book.charAt(index)+"");
            abbhelper(book,result,solution,abb,index+1,-1);
            solution.remove(solution.size()-1);
        }
        else{
            solution.add((abb+1)+"");
            abbhelper(book,result,solution,0,index+1,0);
            solution.remove(solution.size()-1);
            abbhelper(book,result,solution,abb+1,index+1,1);
        }

    }*/

    public static void main(String[] args) {
        /*for(String each:abbreviation("dove")){
            System.out.println(each);
        }*/
        //abbreviation("dove");
        for(String each:getabbreviations("book")){
            System.out.println(each);
        }
    }



}
