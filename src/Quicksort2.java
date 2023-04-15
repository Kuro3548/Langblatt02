import java.util.Arrays;

public class Quicksort2 {
    public static void main(String[] args){
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*100);
        }

        System.out.println(Arrays.toString(arr));
        qSort(arr);
        System.out.println(Arrays.toString(arr));
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
        return qSort(data, 0, data.length - 1);
    }
}
