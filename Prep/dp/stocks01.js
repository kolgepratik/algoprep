
var runner = require ('../util/runner');
var array = require ('../util/array');


function maxProfit (priceList, n) {
    
}

let test_case_list = [
    { name: '', params: { n: 6, coinList: [ 7, 1, 5, 3, 6, 4 ] }, expected: 5 },
    { name: '', params: { n: 5, coinList: [ 7, 6, 4, 3, 1 ] }, expected: 0 }
];

runner.run (test_case_list, function (runnerParams) {
    let result = maxProfit (runnerParams.priceList, runnerParams.n - 1);

    return result;
});
