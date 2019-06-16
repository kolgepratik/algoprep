/*
Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.

Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.

 

Example 1:

Input: [5,3,4,5]
Output: true
Explanation: 
Alex starts first, and can only take the first 5 or the last 5.
Say he takes the first 5, so that the row becomes [3, 4, 5].
If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 

Note:

2 <= piles.length <= 500
piles.length is even.
1 <= piles[i] <= 500
sum(piles) is odd.
*/

/**
 * @param {number[]} piles
 * @return {boolean}
 */
var stoneGame = function(piles) {
    let stone_game_dp = new Array (piles.length);
    for (let i=0; i<piles.length; i++) {
        stone_game_dp [i] = new Array (piles.length);
    }

    let i = piles.length/2 - 1;
    let j = i + 1;
    if (i >= 0 && j<piles.length) {
        stone_game_dp [i][j] = Math.max (
            piles[i] - piles[j], 
            piles[j] - piles[i]
        );

        for (i=i-1, j=j+1; i>=0; i--, j++) {
            let turn_profit = Math.max (
                piles[i] - piles[j], 
                piles[j] - piles[i]
            );

            stone_game_dp [i][j] = stone_game_dp[i+1][j-1] + turn_profit;
        }

        return stone_game_dp [0][piles.length - 1] > 0;
    }

    return false;
};

let pile_array = [5, 3, 4, 5];
console.log (`max_one = ${stoneGame (pile_array)}`);