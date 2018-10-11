
function create_2d_array (rowCount, columnCount, initValue) {
    let return_2d_array = new Array (rowCount);

    for (let r=0; r<rowCount; r++) {
        return_2d_array [r] = new Array (columnCount);
    }

    if (initValue) {
        if (typeof initValue === 'function') {
            for (let r=0; r<rowCount; r++) {
                for (let c=0; c<columnCount; c++) {
                    return_2d_array [r][c] = initValue (r, c);
                }
            }
        } else {
            for (let r=0; r<rowCount; r++) {
                for (let c=0; c<columnCount; c++) {
                    return_2d_array [r][c] = initValue;
                }
            }
        }
    }

    return return_2d_array;
}

module.exports = {
    create_2d_array 
};