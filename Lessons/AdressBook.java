class AdressBook {
    public static int[] twoSum(int[] nums, int target) {

        int[] arr = new int[2];
        for (int i = 0; i < nums.length; i++){
            System.out.println(i);
            for (int j = i + 1; j < nums.length - 1; j++){
                if (nums[i] + nums[j] == target) {
                    arr[0] = i;
                    arr[1] = j;
                    return(arr);
                }
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[3];
        arr[0] = 3;
        arr[1] = 2;
        arr[2] = 4;

        System.out.println(twoSum(arr, 6));
    }
}