import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class Q1 {
    static long inversion=0;
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

        public static long countingInversions(int [] arr) {
            mergeSort(arr, 0, arr.length-1);
            return inversion;
        }

        public static long[] mergeSort(int[] arr, int l, int r){

            long[] merged;
            if(l == r){ // One element array
                merged = new long[1];
                merged[0] = arr[l];
                return merged;
            }
            else{
                int mid = (l+r) / 2;
                long[] leftSorted = mergeSort(arr, l, mid);
                long[] rightSorted = mergeSort(arr, mid+1, r);
                merged = merge(leftSorted, rightSorted);
            }
            return merged;
        }

        public static long[] merge(long[] left, long[] right){
            long[] merged = new long[left.length + right.length];
            int leftIndex = 0; // index for left array
            int rightIndex = 0; // index for right array
            int mergedIndex = 0; // index for merged array
            while(leftIndex < left.length && rightIndex < right.length){
                if(left[leftIndex] <= right[rightIndex]){
                    merged[mergedIndex++] = left[leftIndex++];
                }
                else { // in this case inversion happens
                    merged[mergedIndex++] = right[rightIndex++];
                    inversion += left.length - leftIndex ;
                    // our array is already sorted therefore if inversion happened in index i then inversion also occurs in elements larger than A[i]
                }
            }
            // We fill the remaining elements into the array called merged from here
            while(rightIndex < right.length){
                merged[mergedIndex++] = right[rightIndex++];
            }
            while(leftIndex < left.length){
                merged[mergedIndex++] = left[leftIndex++];
            }
            return merged;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();

            int [] arr = new int [n];

            for(int i = 0 ; i < n ; i++)
                arr[i] = in.nextInt();

            System.out.println(countingInversions(arr));

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

        public long nextLong() {
            return Long.parseLong(next());
        }


    }
}