package com.test.mytestdemo.arithmetic;

import java.util.ArrayList;
import java.util.List;

public class Calculate {

    //分治
    public double myPow(double x,int n){
        if(n==0 || x==1){
            return 1;
        }
        if(n<0){
            return 1/myPowHelp(x,Math.abs(n));
        }
        return myPowHelp(x,n);

    }
    public double myPowHelp(double x,int n){
        if(n==1){
            return x;
        }
        if(n%2==0){
            double half=myPowHelp(x,n/2);
            return half*half;
        }else{
            double half=myPowHelp(x,n/2);
            return half*half*x;
        }


    }
    public double myPow2(double x,int n){
        long N=n;
        if(N<0){
            x=1/x;
            N=-N;
        }
        double ans=1;
        for(long i=0;i<N;i++){
            ans=ans*x;
        }
        return ans;

    }
    //最大子序列的和
    //nums = [-2,1,-3,4,-1,2,1,-5,4]
    //6
    public int maxSubArray(int[] nums){
        int maxSum=0;
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(sum>maxSum){
                maxSum=sum;
            }
            if(sum<0){
                sum=0;
            }
        }
        return maxSum;
    }
    //动态规划算法
    //dp[i] = max(dp[i-1]+nums[i],nums[i])
    //dp[0] = nums[0]
    public int maxSubArray2(int[] nums){
        int[] dp=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            if(i==0){
                dp[i]=nums[i];
            }else{
                dp[i]=dp[i-1]+nums[i]>nums[i]?dp[i-1]+nums[i]:nums[i];
            }

        }
        int maxValue=0;
        for(int i=0;i<dp.length;i++){
            if(dp[i]>maxValue){
                maxValue=dp[i];
            }
        }
        return maxValue;
    }

    //螺旋矩阵
    //matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
    //[1,2,3,4,8,12,11,10,9,5,6,7]
    //初始化矩阵的左上角，初始化方向向右，路径超出界限或者进入之前访问的位置时，顺时针旋转，进入下一个方向

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {  //判断二维数组的合法性
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;   //获取二维数组的row column
        boolean[][] visited = new boolean[rows][columns];   //元素是否访问的数组
        int total = rows * columns;   //二维数据的大小
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0],
                    nextColumn = column + directions[directionIndex][1];

            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];

        }
        return order;
    }
    //按层模拟
    public List<Integer> spiralOrder2(int[][] matrix){
        List<Integer> order=new ArrayList<>();
        int left=0,right=matrix[0].length-1,top=0,bottom=matrix.length-1;
        while (left<=right && top<=bottom){
            for(int i=left;i<right;i++){
                order.add(matrix[top][i]);
            }
            for(int i=top+1;i<bottom;i++){
                order.add(matrix[i][right]);
            }
            for(int i=right-1;i>left;i--){
                order.add(matrix[bottom][i]);
            }
            for(int i=bottom;i>top;i--){
                order.add(matrix[i][left]);
            }
            left++;
            right--;
            top++;
            bottom--;

        }
        return order;

    }
    //跳跃游戏
    //[2,3,1,1,4]
    public boolean dumpTo(int[] arr){
        int maxJumpTo=0;
        int jumpTo=0;
        for(int i=0;i<arr.length-1;i++){
            jumpTo=arr[i]+i;
            if(jumpTo>maxJumpTo){
                maxJumpTo=jumpTo;
            }
        }
        if(maxJumpTo>=arr.length-1){
            return true;
        }else{
            return false;
        }


    }


}
