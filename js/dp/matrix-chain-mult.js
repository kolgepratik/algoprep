
var runner = require ('../util/runner');
var array = require ('../util/array');

function mcm (mcm_dp, i, j, dimensions_array) {
    console.log (`mcm: i=${i}, j=${j}`);
    let return_value = undefined;

    if (i === j) {
        console.log (`return 0 [when (i === j)]`);
        return_value = 0;
    } else {

        if (mcm_dp [i][j] !== undefined) {
            console.log (`return previously computed value: ${mcm_dp [i][j]}`);
            return_value = mcm_dp[i][j];
        } else {
            let matrix_count = (j - i) + 1;

            if (matrix_count === 2) {
                // If there's just 2 matrices to multiply, return the cost as their multiplication.
                let cost = dimensions_array[i-1] * dimensions_array[i] * dimensions_array[j];
                console.log (`return ${cost} [when (matrix_count === 2)]`);
                
                return_value = cost;
            } else {
                // k = number of possible ways to multiply the given matrices. 
                let min_cost = Number.MAX_SAFE_INTEGER;
                for (let k=i; k<j; k++) {
                    console.log (`k=${k}`);
                    
                    let mcm_left = mcm (mcm_dp, i, k, dimensions_array);
                    let mcm_right = mcm (mcm_dp, k + 1, j, dimensions_array);
                    let cost = (dimensions_array[i-1] * dimensions_array[k] * dimensions_array[j]);

                    console.log (`${mcm_left} + ${mcm_right} + ${cost}`);

                    min_cost = Math.min (
                        min_cost, 
                        mcm_left + mcm_right + cost
                    );
                }

                if (min_cost === Number.MAX_SAFE_INTEGER) {
                    return_value = 0;
                } else {
                    console.log (`return ${min_cost} as min_cost`);
                    return_value = min_cost;
                }
            }
        }

    }

    mcm_dp[i][j] = return_value;
    return return_value;
}

let test_case_list = [
    { name: '', params: { dimensions_array: [ 40, 20, 30, 10, 30 ], n: 4 }, expected: 26000 },
    { name: '', params: { dimensions_array: [ 10, 20, 30, 40, 30 ], n: 4 }, expected: 30000 },
    { name: '', params: { dimensions_array: [ 10, 20, 30 ], n: 2 }, expected: 6000 }
];

runner.run (test_case_list, function (runnerParams) {
    let mcm_dp = array.create_2d_array (runnerParams.n + 1, runnerParams.n + 1, function (r, c) {
        if (r === 0 || c === 0) {
            return 0;
        } else if (r === c) {
            return 0;
        } else {
            return undefined;
        }
    });

    mcm (mcm_dp, 1, runnerParams.n, runnerParams.dimensions_array);

    return mcm_dp[1][runnerParams.n];
});
