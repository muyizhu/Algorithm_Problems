//Given a 2D board and a word, find if the word exists in the grid.
//
//The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//Example:
//board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//Given word = "ABCCED", return true.
//Given word = "SEE", return true.
//Given word = "ABCB", return false.
public class WORD_SEARCH {

    public static boolean wordsearch(int[][] grid,String word){
        if(grid == null || word == null || grid.length==0){
            return false;
        }
        int[][] visit = new int[grid.length][grid[0].length];
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                    if(dfshelper(grid,word,0,i,j,visit)){
                        return true;
                    }
            }
        }
        return false;
    }

    public static boolean dfshelper(int[][] grid,String word,int index,int row, int col,int[][] visit){
        if(index == word.length()){
            return true;
        }
        if(row>=grid.length||row<0||col>=grid[0].length||col<0||visit[row][col]==1){
            return false;
        }
        if(grid[row][col] == word.charAt(index)){
            visit[row][col] = 1;
            if(dfshelper(grid,word,index+1,row+1,col,visit)){
                visit[row][col] = 0;
                return true;
            }
            if(dfshelper(grid,word,index+1,row-1,col,visit)) {
                visit[row][col] = 0;
                return true;
            }
            if(dfshelper(grid,word,index+1,row,col+1,visit)){
                visit[row][col] = 0;
                return true;
            }
            if(dfshelper(grid,word,index+1,row,col-1,visit)){
                visit[row][col] = 0;
                return true;
            }
            visit[row][col] = 0;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] board =
                {
                        {'A', 'B', 'C', 'D'},
                        {'B', 'C', 'D', 'O'},
                        {'A', 'D', 'E', 'E'}
                };
        System.out.println(wordsearch(board,"ABCDC"));
    }
}
