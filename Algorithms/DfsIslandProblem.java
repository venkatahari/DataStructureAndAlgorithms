/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and
is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: grid = [
["1","1","1","1","0"],
["1","1","0","1","0"],
["1","1","0","0","0"],
["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
["1","1","0","0","0"],
["1","1","0","0","0"],
["0","0","1","0","0"],
["0","0","0","1","1"]
]
Output: 3
* */
public class DfsIslandProblem {

    public static int numOfIsland(int[][] input){
        if(input==null)
            return 0;
        int count=0;
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[0].length;j++){
                if(input[i][j]==1){
                    dfs(i,j,input);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfs(int i, int j, int[][] input){
        if(i<0 || i>=input.length || j<0 || j>=input[0].length)
            return;
        if(input[i][j]==1){
            input[i][j] = 2;
        }else{ // 0 or 2
            return;
        }
        dfs(i+1,j,input);
        dfs(i-1,j,input);
        dfs(i,j+1,input);
        dfs(i,j-1,input);
    }

    public static void main(String[] arg){
        int[][] input={{1,1,0,0,0},
                        {1,1,0,0,0},
                        {0,0,1,0,0},
                        {0,0,0,1,1}};
        System.out.println(numOfIsland(input));
    }
}
