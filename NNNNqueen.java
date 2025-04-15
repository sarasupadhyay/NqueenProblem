import java.util.Scanner;
public class NNNNqueen extends Thread {
    private static int sol =0;
    private final Object lock =new Object();
    private int N;
    private int row;

    public NNNNqueen(int N, int row) 
    {
        this.N = N;
        this.row = row;
    }
    public void run() 
    {
        boolean[] leftrow = new boolean[N];
        boolean[] upperDiagonal = new boolean[2*N - 1];
        boolean[] lowerDiagonal = new boolean[2*N - 1];
       // Place queen at (row, 0)
        leftrow[row] = true;
        upperDiagonal[N - 1 + 0 - row] = true;
        lowerDiagonal[row + 0] = true;
        solve(1, leftrow, upperDiagonal, lowerDiagonal);
    }

    private void solve(int col, boolean[] leftrow, boolean[] upperDiagonal, boolean[] lowerDiagonal)
     {
        if (col >= N)
         {
            this.sol++;
            return;
         }

        for (int r = 0; r < N; r++) 
        {
            if (leftrow[r]==false && upperDiagonal[N - 1 + col - r]==false && lowerDiagonal[r + col]==false) 
            {
                // Place queen
                leftrow[r] = true;
                upperDiagonal[N - 1 + col - r] = true;
                lowerDiagonal[r + col] = true;
                 solve(col + 1, leftrow, upperDiagonal, lowerDiagonal);
                // Backtrack
                leftrow[r] = false;
                upperDiagonal[N - 1 + col - r] = false;
                lowerDiagonal[r + col] = false;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sa = new Scanner(System.in);
        System.out.print("Enter the value of n for solving Nqueen: ");
        int N = sa.nextInt();
        sa.close();
        long start = System.currentTimeMillis();
        Thread[] t = new Thread[N];
        for (int i = 0; i < N; i++) {
            t[i] = new NNNNqueen(N, i);
            t[i].start();
        }
        for (int i = 0; i < N; i++)
        {
            t[i].join();
        }
        long end = System.currentTimeMillis();
        System.out.println("Total solutions: " + sol);
        System.out.println("Time: " + (end - start) + " milliseconds");
    }
}
