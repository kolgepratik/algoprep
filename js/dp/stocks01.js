
var runner = require('../util/runner');
var array = require('../util/array');


function maxProfit(price_list, i, j) {
    console.log (`maxProfit ( ${i} , ${j} )`);

    let n = ((j - i) + 1);

    let return_value = 0;
    if (n === 1) {
        return_value = 0;
    } else if (n == 2) {
        if (price_list[j] > price_list[i]) {
            return_value = price_list[j] - price_list[i];
        }
    } else {
        let max_profit = 0;
        for (k=i+1; k<=j; k++) {
            console.log (`checking k = ${k}`);
            if (price_list[k] > price_list[i]) {
                max_profit = Math.max (
                    (price_list[k] - price_list[i]) + maxProfit (price_list, k, j),
                    maxProfit (price_list, k, j)
                );
            } 
        }

        return_value = max_profit;
    }

    console.log (`return_value: ${return_value}`);

    return return_value;
}

let test_case_list = [
    { name: '', params: { n: 6, price_list: [7, 1, 5, 3, 6, 4] }, expected: 5 }
];

runner.run(test_case_list, function (runnerParams) {
    let result = maxProfit(runnerParams.price_list, 0, runnerParams.n - 1);

    return result;
});
