var runner = require ('../util/runner');
var array = require ('../util/array');

function lsp (lsp_dp, i, j, string_array) {

    if (lsp_dp[i][j] !== undefined) {
        console.log (`return previously computed value for ( ${i}, ${j} )`);
        
        return lsp_dp [i][j];
    } else {
        let n = ((j - i) + 1);

        let return_value = undefined;
        if (n === 0) {
            return_value = 0;
        } else if (n === 1) {
            return_value = 1;
        } else if (n >= 2) {
            return_value = 
                (string_array[i] === string_array[j]) 
                    ? 2 + lsp (lsp_dp, i + 1, j - 1, string_array) 
                    : Math.max (
                        lsp (lsp_dp, i + 1, j, string_array),
                        lsp (lsp_dp, i, j - 1, string_array)
                    );
        }

        lsp_dp [i][j] = return_value;
        
        return return_value;
}
}


let test_case_list = [
    { name: '', params: { string_array: [ 'a', 'b', 'c', 'd', 'a', 'b' ] }, expected: 3 },
    { name: '', params: { string_array: [ 'a', 'b', 'c', 'd', 'b', 'a' ] }, expected: 5 },
    { name: '', params: { string_array: [ 'a', 'c', 'b', 'd', 'a' ] }, expected: 3 },
    { name: '', params: { string_array: [ 'a', 'a' ] }, expected: 2 },
    { name: '', params: { string_array: [ 'b' ] }, expected: 1 },
    { name: '', params: { string_array: [ 'a', 'b', 'a' ] }, expected: 3 },
    { name: '', params: { string_array: [ 'a', 'b', 'c' ] }, expected: 1 }
];

runner.run (test_case_list, function (runnerParams) {
    let lsp_dp = array.create_2d_array (runnerParams.string_array.length, runnerParams.string_array.length);

    return lsp (lsp_dp, 0, runnerParams.string_array.length - 1, runnerParams.string_array);
});
