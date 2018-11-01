/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
Seen this question in a real interview before?
*/

/**
 * @param {number[]} nums
 */
var NumArray = function(nums) {
    this.nums = nums;

    this.sum_array = compute_sum_array (nums);    
    
    console.dir (this.sum_array);
};

function compute_sum_array (nums) {
    let sum_array = new Array (nums.length);
    
    sum_array [0] = nums[0];
    for (let e=1; e<nums.length; e++) {
        sum_array[e] = nums[e] + sum_array[e-1];
    }

    return sum_array;
}

/** 
 * @param {number} i 
 * @param {number} j
 * @return {number}
 */
NumArray.prototype.sumRange = function(i, j) {
    if (i < 0) {
        return 0;
    } else if (i === 0) {        
        return this.sum_array [j];
    } else {
        return this.sum_array [j] - this.sum_array [i-1];
    }
};

/** 
 * Your NumArray object will be instantiated and called as such:
 * var obj = Object.create(NumArray).createNew([-2, 0, 3, -5, 2, -1])
 * var param_1 = obj.sumRange(i,j)
 */

var obj = new NumArray ([-2, 0, 3, -5, 2, -1]);
var param_1 = obj.sumRange(2, 5);
console.log (`paran_1 = ${param_1}`);