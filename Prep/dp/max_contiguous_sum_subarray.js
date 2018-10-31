
function max_contiguous_sum_subarray (value_array) {
    if (value_array === undefined || !value_array.length || value_array.length === 0) {
        return 0;
    } else if (value_array.length === 1) {
        return value_array [0];
    } else {
        let n = value_array.length; 

        let max_sum_so_far = value_array [0];
        let sum_so_far = value_array [0];
        let start_idx = 0;
        let end_idx = 0;

        for (let i=1; i<n; i++) {
            sum_so_far = value_array [i] + sum_so_far;

            if (sum_so_far <= value_array[i]) {
                sum_so_far = value_array[i];                
            }

            if (sum_so_far >= max_sum_so_far) {
                end_idx = i;

                max_sum_so_far = sum_so_far;
            } 

            if (sum_so_far === 0) {
                start_idx = i;
                end_idx = i;

                sum_so_far = value_array [i];
            }
        }
        
        console.log (`max_sum_so_far = ${max_sum_so_far} from [ ${start_idx} -> ${end_idx} ]`);  
        
        return max_sum_so_far;
}
}

let value_array = [-2, 1, -3, 4, -1, 2, 1, -5, 4];

let max_sum_so_far = max_contiguous_sum_subarray (value_array);
