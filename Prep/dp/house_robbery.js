
function house_robbery (house_robbery_dp, value_array, n) {
    console.log (`house_robbery ( ${n} )`);

    if (house_robbery_dp [n] !== undefined) {
        return house_robbery_dp [n];
    } else {
        let value = undefined; 

        if (n < 0) {
            value = 0;
        } else if (n === 0) {
            value = value_array [0];
        } else if (n === 1) {
            value = Math.max (
                value_array [0], value_array [1]
            );
        } else {
            value = Math.max (
                value_array [n] + house_robbery (house_robbery_dp, value_array, n-2),
                house_robbery (house_robbery_dp, value_array, n-1)
            );
        }

        console.log (`value (${n}) = ${value}`);

        house_robbery_dp [n] = value;

        return value;
    } 
}

let value_array = [2, 7, 9, 3, 1];
let house_robbery_dp = new Array (value_array.length);

let max_robbery_value = house_robbery (house_robbery_dp, value_array, value_array.length-1);

console.log (`max_robbery_value = ${max_robbery_value}`);
