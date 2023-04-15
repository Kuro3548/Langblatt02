import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quicksort {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int[] arr = readArray(input);
        qSort(arr);
        assert isSorted(arr);
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
    public static int partition(int[] data, int l, int r){
        if(l >= r){
            return -1;
        }
        int pivot_index = l;
        int bound = l; //Bound where all Elements left of it are bigger than pivot
        for(int i = l; i <= r; i++){
            //If found Element is smaller than pivot
            if(data[i] > data[pivot_index]){
                //Swapping i, bound
                int temp = data[i];
                data[i] = data[bound];
                data[bound] = temp;
                //Case: Swapped Element was Pivot => Readjust Pivot_Index
                if(bound == pivot_index){
                    pivot_index = i;
                }
                bound++;
            }
        }
        //Putting Pivot to the correct place (to bound)
        int temp = data[pivot_index];
        data[pivot_index] = data[bound];
        data[bound] = temp;
        return bound;
    }
    public static int[] qSort(int[] data, int l, int r){
        if(l >= r){
            return data;
        }
        int mid = partition(data, l, r);
        qSort(data, l, mid - 1);
        qSort(data, mid + 1, r);
        return data;
    }
    public static int[] qSort(int[] data){
        Instant start = Instant.now();
        int[] out = qSort(data, 0, data.length - 1);
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toNanos();
        System.out.println("Time: " + time);
        return out;
    }
    public static boolean isSorted(int[] data){
        int prev = data[0];
        for(int i = 1; i < data.length; i++){
            if(prev < data[i]){
                return false;
            }
            prev = data[i];
        }
        return true;
    }
}
