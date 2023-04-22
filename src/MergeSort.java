import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Mergesort {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int[] arr = readArray(input);

        if (arr.length < 20) {
            System.out.println("" + Arrays.toString(arr));
        }
        Instant start = Instant.now();
        mSort(arr);
        Instant finish = Instant.now();
        if (arr.length < 20) {
            System.out.println("" + Arrays.toString(arr));
        }
        long time = Duration.between(start, finish).toNanos();
        System.out.println("Time: " + time);
        printInfo(arr);
    }

    /**
     * [For test purposes]: Generates an array with values between 0 and 100
     * @param size The size of the generated array
     * @return A randomly generated array
     */
    public static int[] randomArray(int size){
        int[] out = new int[size];
        for(int i = 0; i < size; i++){
            out[i] = (int)(Math.random() * 100);
        }
        return out;
    }

    /**
     * From 1b): Puts the Input-Lines into an Integer-Array
     * @param in A scanner with the input to read
     * @return The array, filled with all numbers in the Input-Stream
     */
    private static int[] readArray(Scanner in){
        ArrayList<Integer> list = new ArrayList<>();
        while(in.hasNextLine()){
            try {
                int current = Integer.parseInt(in.nextLine());
                list.add(current);
            }catch(Exception e){
                System.err.println("Input read was NaN");
            }
        }
        //Händisch: ArrayList<Integer> -> int[]
        int[] arr = new int[list.size()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = list.get(i);
        }
        return arr;
    }
    public static int[] merge(int[] data, int l, int r, int m){
        //System.out.println("Function Call(Merge): " + "[" + l + ", " + m + "][" + (m + 1) + ", " + r + "]");
        int[] all = new int[r - l + 1];
        for(int i = 0; i < all.length; i++){
            all[i] = data[l + i];
        }
        //System.out.println("To sort: " + Arrays.toString(all));
        //All[l - l, m - l][m + 1 - l, r - l] are sorted

        int left_select = 0;
        int right_select = m + 1 - l;
        for(int i = l; i <= r; i++){
            boolean right_isEmpty = right_select > r - l;
            boolean left_isEmpty = left_select > m - l;
            if(!left_isEmpty && (right_isEmpty || all[left_select] >= all[right_select])){
                data[i] = all[left_select];
                left_select++;
            }else{
                data[i] = all[right_select];
                right_select++;
            }
        }

        //System.out.println("Data after Merge: " + Arrays.toString(data));
        return data;
    }
    public static int[] mSort(int[] data, int l, int r){
        if(l >= r){
            return data;
        }
        int avg = (int)(Math.floor((l + r) /2));
        mSort(data, l, avg);
        mSort(data, avg + 1, r);
        return merge(data, l, r, avg);
    }
    public static int[] mSort(int[] data){
        int[] out = mSort(data, 0, data.length - 1);
        return out;
    }

    /**
     * Prints information about the area, including Minimum, Maximum and Median
     * @param data An array sorted in descending order
     */
    public static void printInfo(int[] data){
        if(data.length < 1){
            //Case: Array is Empty
            System.out.println("Min: -, Med: -, Max: -");
            return;
        }
        double med = calculateMedian(data);
        //data[size - 1] = Minimum, data[0] = Maximum, sum/size = Median (als double)
        System.out.println("Min: " + data[data.length - 1] + ", Med: " + med + ", Max: " + data[0]);

    }

    /**
     * Calculates the Median of a sorted array
     * @param data A sorted Database for calculating the median
     * @return Median value of the array
     */
    public static double calculateMedian(int[] data){
        double out;
        if (data.length < 1) {
            throw new IllegalArgumentException("Tried calculating median of empty dataset");
        }
        if (data.length % 2 == 1) {
            //Odd size -> Median = Middle Element
            out = data[data.length / 2];
        } else {
            //Even size -> Median = Average of middle elements
            out = (data[data.length / 2 - 1] + data[data.length / 2]) / (double) 2;
        }
        return out;
    }
}
