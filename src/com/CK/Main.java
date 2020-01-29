package com.CK;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

public class Solution {
    public void wiggleSort(int[] nums) {
        if (nums.length<=1) return;


        Arrays.sort(nums);

        //1, 5, 1, 1, 6, 4
        //1, 1, 1, 4, 5, 6
        //X, 6, X, 5, X, 4
        //1, 6, 1, 5, 1, 4

        int[] tmp = new int[nums.length];
        for(int i=0;i<nums.length;i++)
            tmp[i] = nums[i];

        int count = nums.length-1;
        for(int i=1;i<nums.length;i=i+2){
            nums[i] = tmp[count--];
        }

        for(int i=0;i<nums.length;i=i+2){
            nums[i] = tmp[count--];
        }

    }
}


class Solution {
    public void wiggleSort(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;

        int left = 0, i = 0, right = n - 1;

        while (i <= right) {

            if (nums[newIndex(i,n)] > median) {
                swap(nums, newIndex(left++,n), newIndex(i++,n));
            }
            else if (nums[newIndex(i,n)] < median) {
                swap(nums, newIndex(right--,n), newIndex(i,n));
            }
            else {
                i++;
            }
        }
    }

    private int newIndex(int index, int n) {
        return (1 + 2*index) % (n | 1);
    }

    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length - 1, index = nums.length - k;
        while (start < end) {
            int pivot = partion(nums, start, end);
            if (pivot < index)
                start = pivot + 1;
            else if (pivot > index)
                end = pivot - 1;
            else
                return nums[pivot];
        }
        return nums[start];
    }

    private int partion(int[] nums, int start, int end) {
        int pivot = start;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot])
                start++;
            while (start <= end && nums[end] > nums[pivot])
                end--;
            if (start > end)
                break;

            swap(nums, start, end);
        }
        swap(nums, pivot, end);
        return end;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}