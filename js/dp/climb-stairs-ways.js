/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
*/

function ways_12 (ways_12_dp, n) {
    console.log (`ways_12 ( ${n} )`);

    if (ways_12_dp [n] !== undefined) {
        return ways_12_dp [n];
    } else {
        let value = undefined; 

        if (n < 0) {
            value = 0;
        } else if (n === 0) {
            value = 1;
        } else if (n === 1) {
            value = 1;
        } else {
            value = ways_12 (ways_12_dp, n-1) + ways_12 (ways_12_dp, n-2);
        }

        console.log (`value (${n}) = ${value}`);

        ways_12_dp [n] = value;

        return value;
    } 
}

// 4 -> 1 1 1 1 + 1 1 2 + 1 2 1 + 2 1 1 + 2 2 

let n = 4;
let ways_12_dp = new Array (n);

let ways = ways_12 (ways_12_dp, n);

console.log (`ways (${n}) = ${ways}`);
