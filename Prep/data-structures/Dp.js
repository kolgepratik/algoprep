var Util = require('./Common.js'); 
var $ = new Util.Util(); 

/**
 * Returns the length of increasing array elements. 
 * Any element less than prev max is ignored. 
 * array = [ 1, 3, 2, 4, 7, 3, 8, 5 ] => [ 1, 3, 4, 7, 8 ] => 5 
 * @param {*} array 
 */
function longestIncreasingSubsequence (array) {
    var dp = new Array(array.length).fill(0); 

    dp[0] = 1; 
    var max = dp[0]; 
    for (var i=1; i<array.length; i++) {
        for (var j=0; j<i; j++) {
            if (array[j] < array[i]) {
                dp[i] = dp[j] + 1; 
                max = max < dp[i] ? dp[i] : max; 
            }
        } 
    } 

    return max; 
} 

var array = [ 1, 3, 2, 4, 7, 3, 8, 5 ]; 
$.log ('longestIncreasingSubsequence: %s', [longestIncreasingSubsequence(array)]); 

/**
 * Fib using DP and Memoization. 
 * @param {*} n 
 */
function fib (n) { 
    var f = new Array(n); 

    f[0] = 1; 
    f[1] = 1; 

    for (var i=2; i<n; i++) {
        f[i] = f[i-1] + f[i-2]; 
    } 

    return f; 
} 

/**
 * Recursive Fib using DP and Memoization. 
 * @param {*} n 
 */
function fibRec (n) {
    var f = new Array(n+1).fill(-1); 

    fibRec_util (n, f); 

    return f; 
} 

function fibRec_util (n, f) {
    if (n === 0 || n === 1) {
        f[n] = n; 
    } else if (f[n] !== -1) { 
        return f[n]; 
    } else {
        f[n] = fibRec_util(n - 2, f) + fibRec_util(n - 1, f); 
    } 

    return f[n]; 
}

$.log ('fib: %s', [fibRec(9)]); 

/**
 * Recursive Factorial using DP. 
 * @param {*} n 
 */
function fact (n) {
    if (n === 0) {
        return 1; 
    } else {
        return n * fact(n-1); 
    }
} 

$.log ('fact: %s', [fact(5)]); 

/**
 * Longest common subsequence using DP and Memoization. 
 * @param {*} a 
 * @param {*} b 
 */
function longestCommonSubsequence (a, b) { 
    var r = a.length; 
    var c = b.length; 

    var dp = new Array(r + 1); 
    for (var i=0; i<dp.length; i++) {
        dp[i] = new Array(c + 1);
        dp[i].fill(0); 
    } 

    for (var i=0; i<a.length; i++) {
        for (var j=0; j<b.length; j++) {
            if (a.charAt(i) === b.charAt(j)) {
                dp[i + 1][j + 1] = dp[i][j] + 1; 
            } else {
                dp[i + 1][j + 1] = Math.max (dp[i][j + 1], dp[i+1][j]); 
            }
        } 
    } 

    var lcsLength = dp[r][c]; 
    var lcs = new Array(lcsLength); 
    
    while (r > 0 && c > 0) { 
        if (a.charAt(r - 1) === b.charAt(c- 1)) {
            lcs[lcsLength - 1] = a.charAt(r - 1); 
            lcsLength--; 
            r--; 
            c--; 
        } else {
            if (dp[r][c - 1] > dp[r - 1][c]) {
                c = c - 1; 
            } else {
                r = r - 1; 
            }
        }
    }
    
    $.log ('longestCommonSubsequence: %s', [lcs]);
} 

/**
 * Recursive Longest common subsequence using DP and Memoization. 
 * @param {*} a 
 * @param {*} b 
 */
function longestCommonSubsequenceRec (a, b) { 
    var r = a.length; 
    var c = b.length; 

    var dp = new Array(r); 
    for (var i=0; i<r; i++) {
        dp[i] = new Array(c);
        dp[i].fill(-1); 
    } 

    $.log('longestCommonSubsequenceRec: %s', [longestCommonSubsequenceRec_util (a, b, r - 1, c - 1, dp)]); 
} 

function longestCommonSubsequenceRec_util (a, b, alength, blength, dp) {
    if (alength < 0 || blength < 0) {
        result = 0; 
    } else { 
        if (dp[alength][blength] !== -1) { 
            result = dp[alength][blength]; 
        } else {
            var result = -1; 
            if (a.charAt(alength) === b.charAt(blength)) {
                result = 1 + longestCommonSubsequenceRec_util (a, b, alength - 1, blength - 1, dp); 
            } else {
                result = Math.max (longestCommonSubsequenceRec_util (a, b, alength - 1, blength, dp), longestCommonSubsequenceRec_util (a, b, alength, blength - 1, dp)); 
            } 

            dp[alength][blength] = result; 
        } 
    }

    return result; 
} 

longestCommonSubsequenceRec ('abdb', 'cacbc'); 

/**
 * Maximum sum of contiguous elements in array. 
 * @param {*} array 
 */
function maxContinuousSum (array) {
    var dp = new Array(array.length).fill(0); 

    var currentSum = 0; 
    var maxSum = 0; 
    for (var i=0; i<array.length; i++) {
        currentSum = currentSum + array[i]; 

        if (currentSum < 0) {
            currentSum = 0; 
        } 

        if (maxSum < currentSum) {
            maxSum = currentSum; 
        }
    } 

    return maxSum; 
}

var array = [ -2, 11, -4, 13, -5, 2]; 
$.log('maxContinuousSum: %s', [maxContinuousSum (array)]);

/**
 * Maximum sum of contiguous elements in array. 
 * Restriction: You cannot select 2 contiguous elements. 
 * @param {*} array 
 */
function maxContinuousSum_variation (array) {
    var dp = new Array(array.length).fill(0); 

    var currentSum = 0; 
    var maxSum = 0; 
    for (var i=1; i<array.length; i++) { 
        if (i === 0) {
            currentSum = array[0]; 
        } else if (i === 1) { 
            currentSum = Math.max (array[0], array[1]); 
        } else { 
            currentSum = Math.max(array[i] + array[i -2], currentSum); 

            if (currentSum < 0) {
                currentSum = 0; 
            } 

            if (maxSum < currentSum) {
                maxSum = currentSum; 
            }
        }
    } 

    return maxSum; 
}

$.log('maxContinuousSum_variation: %s', [maxContinuousSum_variation (array)]); 

/**
 * Dimensions of matrix m[i] = m[i-1] x m[i]. 
 * m contains m.length - 1 matrices. 
 * 
 * @param {*} m 
 */
function matrixMultMinProduct (m) {
    var n = m.length - 1; 

    var dp = new Array(n); 
    var sol = new Array(n); 
    for (var i=0; i<n; i++) {
        dp[i] = new Array(n);
        sol[i] = new Array(n);
        dp[i].fill(-1); 
        sol[i].fill(-1); 
    }

    for (var i=1; i<=n; u++) {
        for (var j=1; j<=n; j++) {
            
            dp[i][j] = Number.MAX_VALUE; 
            for (var k=i-1; k<j; k++) {
                var currentCost = m[i-1] * m[k] * m[j]; 

                if (dp[i][j] > currentCost) {
                    dp[i][j] = currentCost; 
                    sol[i][j] = k; 
                }
            }
        } 
    } 
}

function knapSack (w, v, c) { 
    return knapSack_util (w, v, w.length - 1, c); 
}

function knapSack_util (w, v, n, c) {
    var result = -1; 
    if (n === 0) {
        result = 0; 
    }  else if (w[n] > c) {
        result = knapSack_util (w, v, n-1, c); 
    } else {
        result = Math.max (knapSack_util (w, v, n-1, c), v[n] + knapSack_util (w, v, n-1, c-w[n])); 
    } 

    return result; 
}

var w = [ 1, 2, 4, 2, 5 ]; 
var v = [ 5, 3, 5, 3, 2 ]; 
$.log ('knapSack: %s', [knapSack (w, v, 10)]); 

/**
 * Check if there is a subset in n whose sum is equal to s. 
 * @param {*} n 
 * @param {*} s 
 */
function subsetSum (a, s) { 
    return subsetSum_util (a, a.length - 1, s); 
} 

function subsetSum_util (a, n, s) {
    if (s === 0) { 
        return true; 
    } else if (n < 0 || s < 0) {
        return false; 
    } else {
        return subsetSum_util (a, n-1, s - a[n]) || subsetSum_util (a, n-1, s); 
    }
}

var array = [3 ,2, 1, 19, 3]; 
$.log ('subsetSum: %s', [subsetSum (array, 19)]); 

