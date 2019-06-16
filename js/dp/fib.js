
var runner = require ('../util/runner');

function fibRecursive (fib_dp, n) {
    if (n === 0) {
        return 0;
    } else if (n === 1) {
        return 1;
    } else if (fib_dp [n] !== undefined) {
        return fib_dp [n];
    } else {
        let result = fibRecursive (fib_dp, n - 1) + fibRecursive (fib_dp, n - 2);
        
        fib_dp [n] = result;

        return result;
    }
}

let test_case_list = [
    { name: '', params: { n: 5 }, expected: 5 },
    { name: '', params: { n: 6 }, expected: 8 },
    { name: '', params: { n: 8 }, expected: 21 }
];

runner.run (test_case_list, function (runnerParams) {
    const n = runnerParams.n;

    let fib_dp = new Array (n);

    return fibRecursive (fib_dp, n);
});