import java.util.Arrays;

public class Quicksort {
    public static void main(String[] args){
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*100);
        }

        System.out.println(Arrays.toString(arr));
        qSort(arr);
        assert isSorted(arr);
        System.out.println(Arrays.toString(arr));
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
        return qSort(data, 0, data.length - 1);
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
