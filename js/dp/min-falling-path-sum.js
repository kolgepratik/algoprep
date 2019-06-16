/*
Given a square array of integers A, we want the minimum sum of a falling path through A.

A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.

 

Example 1:

Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12
Explanation: 
The possible falling paths are:
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
The falling path with the smallest sum is [1,4,7], so the answer is 12.

 

Note:

1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100
*/

/**
 * @param {number[][]} A
 * @return {number}
 */
var minFallingPathSum = function(A) {
    if (A === undefined || A.length === undefined || A.length === 0 || A[0] === undefined || A[0].length === undefined || A[0].length !== A.length) {
        return 0;
    } else if (A.length === 1) {
        return A[0][0];
    } else {
        let square_dimension = A.length;

        let min_sum_dp = new Array (square_dimension);
        for (let r=0; r<square_dimension; r++) {
            min_sum_dp[r] = new Array (square_dimension);

            // Set values for 1st row. 
            min_sum_dp[0][r] = A[0][r];
        }

        let row_min = undefined;
        for (let r=1; r<square_dimension; r++) {
            row_min = Number.MAX_SAFE_INTEGER;

            for (let c=0; c<square_dimension; c++) {
                let left = (c === 0) ? Number.MAX_SAFE_INTEGER : min_sum_dp[r-1][c-1];
                let middle = min_sum_dp[r-1][c];
                let right = (c === (square_dimension - 1)) ? Number.MAX_SAFE_INTEGER : min_sum_dp[r-1][c+1];

                min_sum_dp[r][c] = A [r][c] 
                    + Math.min (
                        left, middle, right
                    );

                row_min = Math.min (row_min, min_sum_dp[r][c]);
            }
        } 

        return row_min;
    }
};

let A = [
    [ 1, 2, 3 ],
    [ 4, 5, 6 ],
    [ 7, 8, 9 ]
];

console.log (`get_min_sum = ${minFallingPathSum (A)}`);