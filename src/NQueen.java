import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/*
给你一个 N×N 的棋盘，让你放置 N 个皇后，使得它们不能互相攻击。
PS：皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位。
 */

public class NQueen {

    ArrayList<StringBuffer[][]> res=new ArrayList<>();

    public static void main(String[] args) {

        NQueen nQueen=new NQueen();
        nQueen.solveNQueens(8);
        int i=0;
    }

    ArrayList<StringBuffer[][]> solveNQueens(int n){

        StringBuffer[][] board=new StringBuffer[n][n];   //棋盘
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j]=new StringBuffer(".");       //.为空  Q为皇后
            }
        }
        backtrack(board,0);
        return res;
    }

    //选择列表
    //路径
    void backtrack(StringBuffer[][] board,int row){
        //判断结束条件
        StringBuffer buffer=new StringBuffer();
        if(row==board.length){   //行数等于棋盘行数
            int n=board.length;
            StringBuffer[][] tmp=new StringBuffer[n][n];   //这里因为board是值传递，后续遍历会修改结果，所以需要把结果保存出来
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    char c=board[i][j].charAt(0);
                    tmp[i][j]=new StringBuffer(".");
                    tmp[i][j].setCharAt(0,c);
                }
            }
            res.add(tmp);           //保存结果
            return;
        }

        int n=board[row].length;
        for(int col=0;col<n;col++){
            if(!isValid(board,row,col))
                continue;
            board[row][col].setCharAt(0,'Q');
            backtrack(board,row+1);
            board[row][col].setCharAt(0,'.');
        }
    }
    //判断是否有效
    boolean isValid(StringBuffer[][] board,int row,int col){
        int n=board.length;
        for(int i=0;i<n;i++){
            if(board[i][col].charAt(0)=='Q')   //当前列的上下是否有皇后
                return false;
        }

        for(int i=row-1,j=col+1;i>=0&&j<n;i--,j++){   //右对角线
            if(board[i][j].charAt(0)=='Q')
                return false;
        }

        for (int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){   //左对角线
            if(board[i][j].charAt(0)=='Q')
                return false;
        }
        return true;
    }

}
