/* 
    @problem
    Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
    Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
    
    Example 1:
    Given nums = [1,1,2],
    Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
    It doesn't matter what you leave beyond the returned length.

    Example 2:
    Given nums = [0,0,1,1,1,2,2,3,3,4],
    Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
    It doesn't matter what values are set beyond the returned length.

    Internally you can think of this:

    // nums is passed in by reference. (i.e., without making a copy)
    int len = removeDuplicates(nums);

    // any modification to nums in your function would be known by the caller.
    // using the length returned by your function, it prints the first len elements.
    for (int i = 0; i < len; i++) {
        print(nums[i]);
    }

    @approach
    Iterate over the array from left to right while keeping a count of the number of distinct numbers.
    Runtime: O(n)
    Space: O(1)
*/

/**
* @param {number[]} nums
* @return {number}
*/
var removeDuplicates = function (nums) {
    if (nums === undefined || nums.length === undefined || nums.length === 0) {
        return 0;
    } else if (nums.length === 1) {
        return 1; 
    } else {
        let left = 1;
        let prev = nums[0];

        for (let i=1; i<nums.length; i++) {
            if (prev !== undefined && prev !== nums[i]) {
                nums[left] = nums[i];
                left++;
            }

            prev = nums[i];
        }

        return left;
    }
}

function run_removeDuplicates () {
    let nums = [ 0, 0, 0, 1, 1, 2, 2, 2, 2 ];
    let distinctCount = removeDuplicates (nums);

    console.log (`Found ${distinctCount} distinct numbers`);
    for (let i=0; i<distinctCount; i++) { 
        console.log (nums[i]);
    }
}

run_removeDuplicates ();
