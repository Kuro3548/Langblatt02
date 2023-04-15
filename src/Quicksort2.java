import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quicksort2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int[] arr = readArray(input);
        qSort(arr);
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
    public static int[] partition(int[] arr, int l, int r){
        if(arr[l] < arr[r]){
            swap(arr, l, r);
        }
        int bound_1 = l + 1;
        int bound_2 = r - 1;
        int p_1 = arr[l];
        int p_2 = arr[r];
        //We have Left Pivot > Right Pivot
        for(int i = l + 1; i <= bound_2; i++){ //If done correctly, we already know that all elements behind bound_2 are smaller than p_2.
            //Case 1: Found Element is Greater than Left Pivot
            if(arr[i] > p_1){
                swap(arr, i, bound_1);
                bound_1++;
            }else if(p_2 >= arr[i]){ //Case 2: Found Element is Lower than Right Pivot
                //We adjust bound_2 and look for the first Element from the right that is greater than right pivot (and thus needs to be swapped)
                while(p_2 > arr[bound_2] && i < bound_2){
                    bound_2--;
                }
                swap(arr, i, bound_2);
                bound_2--;
                //Special Case: The Element that we found from the right is not only greater than p_2, but also greater than p_1.
                if(arr[i] > p_1){
                    swap(arr, i, bound_1);
                    bound_1++;
                }
            }
        }
        bound_1--;
        bound_2++;
        swap(arr, l, bound_1);
        swap(arr, r, bound_2);
        return new int[] {bound_1, bound_2};
    }
    private static void swap(int[] data, int x1, int x2){
        int temp = data[x1];
        data[x1] = data[x2];
        data[x2] = temp;
    }
    public static int[] qSort(int[] data, int l, int r){
        if(l >= r){
            return data;
        }
        int[] mid = partition(data, l, r);
        qSort(data, l, mid[0] - 1);
        qSort(data, mid[0] + 1, mid[1] - 1);
        qSort(data, mid[1] + 1, r);
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
}
