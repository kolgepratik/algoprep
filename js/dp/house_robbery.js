/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
*/

function house_robbery (house_robbery_dp, value_array, n) {
    console.log (`house_robbery ( ${n} )`);

    if (house_robbery_dp [n] !== undefined) {
        return house_robbery_dp [n];
    } else {
        let value = undefined; 

        if (n < 0) {
            value = 0;
        } else if (n === 0) {
            value = value_array [0];
        } else if (n === 1) {
            value = Math.max (
                value_array [0], value_array [1]
            );
        } else {
            value = Math.max (
                value_array [n] + house_robbery (house_robbery_dp, value_array, n-2),
                house_robbery (house_robbery_dp, value_array, n-1)
            );
        }

        console.log (`value (${n}) = ${value}`);

        house_robbery_dp [n] = value;

        return value;
    } 
}

let value_array = [2, 7, 9, 3, 1];
let house_robbery_dp = new Array (value_array.length);

let max_robbery_value = house_robbery (house_robbery_dp, value_array, value_array.length-1);

console.log (`max_robbery_value = ${max_robbery_value}`);
