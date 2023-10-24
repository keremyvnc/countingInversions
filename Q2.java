import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Q2 {
    static double[] q2; // We are gonna use this variable for general sorting process
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(in, out);
        out.close();
    }

    static class TaskA {

        public static double[] quickSort(double [] arr) {
            myQuickSort2(arr, 0, arr.length-1);
            return q2;
        }

        private static void myQuickSort2(double[] A, int p, int r){
            if(p<r) {
                int[] q = partition2(q2, p, r);
                myQuickSort2(q2, p, q[0]-1);
                myQuickSort2(q2, q[0]+1, q[1]-1);
                myQuickSort2(q2, q[1]+1,r);
            }
        }

        public static int[] partition2(double[] A, int p, int r){
            if(A[p] > A[r]) A = swapDouble(A,p,r); // A[left] < A[right]
            double leftPivot = A[p];
            double rightPivot = A[r];
            int li=p+1; // left pivot index
            int ri=r-1; // right pivot index

            int j = p+1; // iterator
            while (j <= ri) {
                if(A[j] < leftPivot) A = swapDouble(A,li++,j++); // if iterator is smaller than small pivot iterator change j and li
                else if(A[j] > rightPivot) A = swapDouble(A,j,ri--); // if iterator is bigger than big pivot iterator change j and ri
                else j++;
            }
            swapDouble(A,li-1,p); // li-1 and ri+1 is our pivots true location in array
            swapDouble(A,ri+1,r);
            int[] pivots = new int[]{li-1,ri+1}; // We store our pivot locations here
            q2=A;
            return pivots;
        }

        static double[] swapDouble(double[] arr, int i, int j)
        {
            double temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            return arr;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();

            double [] arr = new double [n];

            for(int i = 0 ; i < n ; i++)
                arr[i] = in.nextDouble();
            q2 = arr;
            double[] result = quickSort(arr);

            for (int i = 0 ; i < result.length ; i++) {
                System.out.print(result[i]);
                if (i == result.length - 1)
                    System.out.print("\n");
                else
                    System.out.print(" ");
            }

        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }


        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }


    }
}