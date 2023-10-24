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
public class Q23 {
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
            myQuickSort3(arr, 0, arr.length-1);
            return q2;
        }

        public static void myQuickSort3(double[] A, int p, int r){
            if(p < r) {
                int[] q = partition3(A, p, r);
                myQuickSort3(q2, p, q[0]-1);
                myQuickSort3(q2, q[0]+1, q[1]-1);
                myQuickSort3(q2, q[1]+1,q[2]-1);
                myQuickSort3(q2, q[2]+1, r);
            }

        }
        public static int[] partition3(double[] A, int left, int right) {
            if(A[left] > A[left+1]) A = swapDouble(A, left, left+1);
            if(A[left+1] > A[right]) A = swapDouble(A, left+1, right);
            if(A[left] > A[left+1]) A = swapDouble(A, left, left+1);
            if(right-left==2) return new int[]{left, left+1, right}; // If array contains only three elements than this array is sorted
            // Now A[left] < A[left+1] < A[right]
            double p1=A[left], p2 = A[left+1], p3=A[right];

            int a = left+2;  //      .. < p1
            int b = left+2;  // p1 < .. < p2
            int c = right-1; // p2 < .. < p3
            int d = right-1; //      p3 < ..

            while(b <= c){
                while(A[b] < p2 && b <= c){
                    if(A[b] < p1) {
                        A = swapDouble(A,a++,b); // If b is smaller than small pivot we must change a and b
                    }
                    b++;
                }
                while(A[c] > p2 && b <= c){
                    if(A[c] > p3) {
                        A = swapDouble(A,d--,c);
                    }
                    c--;
                }
                if(b<=c) {
                    if(A[c] < p1) {
                        if(A[b] > p3) { // c < a < d < b we have
                            A = swapDouble(swapDouble(A,b,a),a,c);// If b is the biggest and c is the smallest ( b > a > c ) we change the order to (a<b<c)
                            A = swapDouble(A, c,d); // Than we just change c and d because d must be represent biggest
                            d--;
                        }
                        else { // c < a < b
                            A = swapDouble(A, c,b);
                            A = swapDouble(A, b,a);
                        }
                        a++; b++; c--;
                    }
                    else{
                        if(A[b] > p3){ // c < d < b
                            A = swapDouble(swapDouble(A,b,c),c,d);
                            d--;
                        }
                        else{ // A[b] and A[c] are just fine but we just need to replace these with eachother
                            A = swapDouble(A, c,b);
                        }
                        b++;c--;
                    }
                }
            }

            int[] pivots = new int[3]; // We store our pivot locations in this array
            A = swapDouble(A, left+1, --a);
            pivots[0]  = a;
            A = swapDouble(A, a, --b);
            A = swapDouble(A, left, --a);
            pivots[1] = a;
            A= swapDouble(A, right,++d);
            pivots[2] = d;
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