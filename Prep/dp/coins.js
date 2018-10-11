
var runner = require ('../util/runner');
var array = require ('../util/array');


function coins (coins_dp, coinList, sum, n) {
    if (sum === 0) {
        coins_dp [sum][n] = 1;

        return 1;
    } else if (sum < 0) {            
        return 0;
    } else if (n === 0) {
        coins_dp [sum][n] = 0;
        
        return 0;
    } else if (coins_dp [sum][n] !== undefined) {
        return coins_dp [sum][n];
    } else {        
        let result;

        /* 
        * When you are not allowed to repeat the coins, you must move to the next coin. 
        * i.e. coins (coinList, <n-1>, sum - coinList[n]) 
        */
        result = 
            coins (coins_dp, coinList, sum - coinList[n - 1], n) 
                +
            coins (coins_dp, coinList, sum, n-1)
        ;     
        
        coins_dp [sum][n] = result;

        return result;
    }
}

let test_case_list = [
    { name: '', params: { n: 3, sum: 4, coinList: [ 1, 2, 3 ] }, expected: 4 },
    { name: '', params: { n: 4, sum: 10, coinList: [ 2, 5, 3, 6 ] }, expected: 5 },
    { name: '', params: { n: 5, sum: 10, coinList: [ 2, 5, 3, 6, 7 ] }, expected: 6 },
];

runner.run (test_case_list, function (runnerParams) {
    let coins_dp = array.create_2d_array (runnerParams.sum + 1, runnerParams.n + 1);

    let result = coins (coins_dp, runnerParams.coinList, runnerParams.sum, runnerParams.n);

    return result;
});
