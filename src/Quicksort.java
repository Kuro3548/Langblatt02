import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quicksort {

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int[] arr = readArray(input);

		if (arr.length < 20) {
			System.out.println("" + Arrays.toString(arr));
		}
		Instant start = Instant.now();
		qSort(arr);
		Instant finish = Instant.now();
		assert isSorted(arr);
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
		int[] out = qSort(data, 0, data.length - 1);
		return out;
	}
	public static int partition(int[] data, int l, int r){
		if(l >= r){
			return l;
		}
		int pivot_index = l;
		int bound = l; //Bound where all Elements left of it are bigger than pivot
		for(int i = l + 1; i <= r; i++){
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