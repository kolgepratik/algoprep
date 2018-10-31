
function ways_12 (ways_12_dp, n) {
    console.log (`ways_12 ( ${n} )`);

    if (ways_12_dp [n] !== undefined) {
        return ways_12_dp [n];
    } else {
        let value = undefined; 

        if (n < 0) {
            value = 0;
        } else if (n === 0) {
            value = 1;
        } else if (n === 1) {
            value = 1;
        } else {
            value = ways_12 (ways_12_dp, n-1) + ways_12 (ways_12_dp, n-2);
        }

        console.log (`value (${n}) = ${value}`);

        ways_12_dp [n] = value;

        return value;
    } 
}

// 4 -> 1 1 1 1 + 1 1 2 + 1 2 1 + 2 1 1 + 2 2 

let n = 4;
let ways_12_dp = new Array (n);

let ways = ways_12 (ways_12_dp, n);

console.log (`ways (${n}) = ${ways}`);
