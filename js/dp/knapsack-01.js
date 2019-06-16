
var runner = require ('../util/runner');
var util_array = require ('../util/array');

function knapsack01 (dp_knapsack, weightList, valueList, capacity, n) {

    if (n === 0 || capacity === 0) {
        return 0;
    } else {
        if (dp_knapsack [capacity][n] !== undefined) {
            return dp_knapsack [capacity][n];
        } else {
            let result;

            if (weightList[n-1] > capacity) {
                /**
                 * If the current item's weight is greater than the knapsack capacity, we cannot include it.
                 * Skip to the next item.
                 */
                
                result = knapsack01 (dp_knapsack, weightList, valueList, capacity, n - 1);
            } else {
                result = Math.max (
                    valueList[n-1] + knapsack01 (dp_knapsack, weightList, valueList, capacity - weightList[n-1], n - 1), 
                    knapsack01 (dp_knapsack, weightList, valueList, capacity, n - 1)
                );
            }

            dp_knapsack [capacity][n] = result;

            return result; 
        }
    }
}

let test_case_list = [
    { name: '', params: { n: 4, weightList: [ 2, 3, 4, 5 ], valueList: [ 3, 4, 5, 6 ], capacity: 5 }, expected: 7 },
    { name: '', params: { n: 3, weightList: [ 3, 2, 1 ], valueList: [ 5, 3, 4 ], capacity: 5 }, expected: 9 },
    { name: '', params: { n: 6, weightList: [ 1, 2, 3, 8, 7, 4 ], valueList: [ 20, 5, 10, 40, 15, 25 ], capacity: 10 }, expected: 60 },
    { name: '', params: { n: 3, weightList: [ 10, 20, 30 ], valueList: [ 60, 100, 120 ], capacity: 50 }, expected: 220 }
];

runner.run (test_case_list, function (runnerParams) {
    let dp_knapsack = util_array.create_2d_array (runnerParams.capacity + 1, runnerParams.n + 1);

    let result = knapsack01 (dp_knapsack, runnerParams.weightList, runnerParams.valueList, runnerParams.capacity, runnerParams.n);

    return result;
});