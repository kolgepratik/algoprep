/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].
*/

var runner = require('../util/runner');
var array = require('../util/array');

/**
 * 
 * @param {number []} cost_array cost of each climbing each stair
 * @param {number} n the cost of climbing from bottom till this stair
 */
function staircase_cost(staircase_cost_dp, cost_array, n) {
    console.log (`staircase_cost ( ${n} )`);

    if (staircase_cost_dp [n] !== undefined) {
        return staircase_cost_dp [n];
    } else {
        let return_value = 0;
        
        if (n <= 1) {
            return_value = 0;
        } else if (n === 2) {
            return_value = Math.min (
                cost_array [1], 
                cost_array [0]
            );
        } else {
            return_value = Math.min (
                cost_array[n-1] + staircase_cost (staircase_cost_dp, cost_array, n-1),
                cost_array[n-2] + staircase_cost (staircase_cost_dp, cost_array, n-2)
            );
        }

        console.log (`return_value: ${return_value}`);
        
        staircase_cost_dp [n] = return_value;
        
        return return_value;
    }
}

let test_case_list = [
    { name: '', params: { n: 3, cost_array: [10, 15, 20] }, expected: 15 },
    { name: '', params: { n: 10, cost_array: [1, 100, 1, 1, 1, 100, 1, 1, 100, 1] }, expected: 6 }
];

runner.run(test_case_list, function (runnerParams) {
    let staircase_cost_dp = new Array(runnerParams.n);

    let result = staircase_cost (staircase_cost_dp, runnerParams.cost_array, runnerParams.n);

    return result;
});
