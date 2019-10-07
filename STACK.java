import java.util.Arrays;

public class STACK {

    public static class simpleStack<I> {
        private int pointer;
        private Object[] stack;
        private int c;
        int p;

        simpleStack() {
            this(12);
        }

        public simpleStack(int length) {
            pointer = 0;
            stack = new Object[length];
            c = length;
        }

        public void push(Object in) {
            if (pointer < stack.length) {
                stack[pointer] = in;
                pointer++;
            }
            if (pointer >= c) {
                Object[] temp = new Object[c * 2];
                for (int i = 0; i < c; i++) {
                    temp[i] = stack[i];
                }
                stack = temp;
                stack[pointer++] = in;
                c *= 2;
            }

        }
        public void print(){
            p = pointer-1;
            System.out.print("[");
            while(p>=0){
                System.out.print(stack[p]+" ");
                p--;
            }
            System.out.println("]");
        }

        public I pop() {
            if (!isEmpty()) {
                pointer = pointer - 1;
                return (I) stack[pointer];
            }
            return null;
        }

        public int size() {
            return pointer;
        }

        public I peek(){
            if(isEmpty()){
                return null;
            }
            return (I)stack[pointer-1];
        }

        public boolean isEmpty() {
            if (pointer == 0) {
                return true;
            }
            return false;
        }

        public int Capacity() {
            return c;
        }
    }

   /*Question: Given an array X, the span S[i]
    of X[i] is the maximum number
    of consecutive elements X[j]
    before and including X[i], such
    that X[j] < X[i]. In other words,
    the span at i is the distance
    from i to the first element
    before i and larger than X[i].*/
   //返回值：最大span对应的subarray。
   //解： 用一个数组temp，temp的每个位置对应输入数组a的每一个位置。
    //temp的每个位置存放a每个对应位置元素之前的，比它大的元素的位置。
   // 比如：a【4，2，0, 3】，temp【0，0，1,0】，a第一个位置的元素是4，4前面没有比它大的，所以temp对应的位置填0，
    //再看二号位2，2之前有4比三大，所以temp第二个位置填0，读作a的位置0的元素比位置1的元素大
    //再看a的第三个元素0，3比它大，我们不找4，因为只找从后往前第一个比它大的
    //所以temp是1，因为3的位置是1。最后我们看第四个元素3，temp的位置是0，因为只有4比他大
    //如何找的：我们看a的第四个元素3，先和前一个元素，也就是0比，比他大，所以只要再和0这个元素位置上
    //记录的比0大的元素再比就行了，也就是说找到temp里0这个元素对应位置的value，也就是1，然后和a在1的
    //value，也就是2比，又比2大，所以又从temp里找2的对应位置的元素发现是0，于是和a的0号位置比，发现比4
    //小，temp第四个位置填0；
   // 这样我们得到了temp，之后只要遍历temp 用temp的每个位置的index减去这个位置的value
    //就可以得到每个位置的span，然后我们找到最大的span就行了。
    //complexity:
    // time: worst case:n^2, space:n
   public static int[] maxspan(int[] a){
        int[] temp = new int[a.length];
        int pre;
        int max = 0;
       int b=0;
       int e=0;
        for(int i = 1;i<a.length;i++){
            pre = i-1;
            while(a[i]>=a[pre] && pre!=temp[pre]){
                pre = temp[pre];
            }
            temp[i] = pre;
            if(i-pre+1>max){
                max = i-pre+1;
                b = pre;
                e = i;
            }
        }
        int[] result = new int[max];
       System.out.println(b+" "+e);
        for(int i = 0;i<max;i++){
            result[i] = a[b++];
        }
        return result;
   }

   //haotao的解，不知道hantao是怎么想的，我感觉我想不到
    //所以想问一下这个题属于什么类型的题目，怎么想比较好？
    //solution：用stack，complexity，时间是n，空间是n
    public static int[] maxspan1(int[] a){
       simpleStack<Integer> stack = new simpleStack<>();
       stack.push(0);
       //stack里面只留subarray里最大的元素的位置，比如【11，1，2，4，6，5】，stack里就只有【0，4，5】
       for(int i = 1;i<a.length;i++){
           while(stack.peek() != null && a[stack.peek()]<=a[i]){
               stack.pop();
           }
           stack.push(i);

       }
       //stack找完了，下面是在stack里找最大的span，就直接stack前一个减后一个，看哪一对差值大
       int max = 0;//最大span
       int temp=0;
       int b=0;//b就是开始位置
       int e;//e就是结束位置
       int l = stack.size();
       for(int i = 0;i<l;i++){
           temp = stack.pop();
           if(stack.peek()!=null && temp-stack.peek()>max){
               max = temp-stack.peek();
               e = temp;
               b = stack.peek()+1;
           }
       }
       if(temp>max){
           max = temp;
       }
       //找到span了，现在按照b和e把max span的subarray复制出来返回
       int[] result = new int[max];
       for(int i = 0;i<result.length;i++){
           result[i] = a[b++];
       }
       return result;
    }

    public static void main(String[] args) {
       int[] a = {11,1,2,6,4,10};
        System.out.println(Arrays.toString(maxspan1(a)));


    }


}
