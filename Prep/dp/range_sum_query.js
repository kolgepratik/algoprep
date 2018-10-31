/**
 * @param {number[]} nums
 */
var NumArray = function(nums) {
    this.nums = nums;

    this.sum_range_dp = compute_dp_array (nums);

       
};

function compute_dp_array (nums) {
    let dp_array = new Array (nums.length);
    for (let e=0; e<sum_range_dp.length; e++) {
        sum_range_dp.push (new Array(nums.length));
    }

    

    return dp_array;
}

/** 
 * @param {number} i 
 * @param {number} j
 * @return {number}
 */
NumArray.prototype.sumRange = function(i, j) {
    
};

/** 
 * Your NumArray object will be instantiated and called as such:
 * var obj = Object.create(NumArray).createNew(nums)
 * var param_1 = obj.sumRange(i,j)
 */
