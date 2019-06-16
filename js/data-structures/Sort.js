class Sort {

    bubbleSort (data) { 
        if (data === undefined || typeof(data) !== 'object') {
            console.error('bubbleSort: not an array or blank array');
        } else {
            console.log('bubbleSort: sorting');
            for (var i=0; i<data.length; i++) {
                for (var j=0; j<data.length-1; j++) {
                    if (data[j] > data[j+1]) {
                        var temp = data[j+1]; 
                        data[j+1] = data[j]; 
                        data[j] = temp; 
                    }
                }
            } 

            console.log(data);
        }
    } 

    selectionSort (data) {
        if (data === undefined || typeof(data) !== 'object') {
            console.error('selectionSort: not an array or blank array');
        } else {
            console.log('selectionSort: sorting');
            for (var i=0; i<data.length - 1; i++) { 
                var minIdx = i; 
                for (var j=i+1; j<data.length; j++) { 
                    if (data[j] < data[minIdx]) {
                        minIdx = j; 
                    }
                } 

                var temp = data[minIdx]; 
                data[minIdx] = i; 
                data[i] = temp; 
            } 

            console.log(data);
        }  
    } 

    quickSort () {
        if (data === undefined || typeof(data) !== 'object') {
            console.error('quickSort: not an array or blank array');
        } else { 
            console.log('quickSort: sorting');
            this.quickSort_helper(data, 0, data.length - 1); 
            console.log(data); 
        } 
    }

    quickSort_helper (data, left, right) {
        if (data === undefined || typeof(data) !== 'object') {
            console.error('quickSort: not an array or blank array');
        } else { 
            if (left < right) {
                var mid = this.quickSort_partition(data, left, right); 
                if (mid !== undefined) {
                    this.quickSort_helper (data, left, mid - 1);
                    this.quickSort_helper (data, mid + 1, right);
                } 
            }
        }  
    } 

    /**
     * pivot is the rightmost element 
     * j starts from left and goes till right 
     * -- at every iteration, compare data at j and pivot
     * -- i is incremented every time we swap 
     * finally swap i and right 
     * return i 
     */
    quickSort_partition (data, left, right) { 
        if (left < right) {
            var pivot = right; 
            var i = left; 

            for (var j=left; j<=right; j++) {
                if (data[j]<data[pivot]) {
                    var temp = data[i]; 
                    data[i] = data[j]; 
                    data[j] = temp; 
                    i++; 
                } 
            } 

            var temp = data[i]; 
            data[i] = data[right]; 
            data[right] = temp; 

            return i; 
        } else {
            return undefined; 
        }
    } 

    mergeSort (data) {
        if (data === undefined || typeof(data) !== 'object') {
            console.error('mergeSort: not an array or blank array');
        } else { 
            console.log('mergeSort: sorting');
            this.mergeSort_helper(data, 0, data.length - 1); 
            console.log(data); 
        } 
    }

    mergeSort_helper (data, left, right) {
        if (left < right) { 
            var mid = Math.floor((left + right) / 2); 

            this.mergeSort_helper (data, left, mid); 
            this.mergeSort_helper (data, mid + 1, right); 
            this.mergeSort_merge (data, left, mid, right); 
        }
    } 

    /**
     * keep track of original mid 
     * create a temporary array 
     * find minimum elements and move them to arr 
     * get remaining elements and move them to arr 
     * copy array to main array 
     */
    mergeSort_merge (data, left, mid, right) { 
        var leftEnd = mid; 
        var arr = []; 
        var i = 0; 
        while (left <= leftEnd && (mid + 1) <= right) { 
            if (data[left] < data[mid + 1]) {
                arr[i] = data[left]; 
                left++; 
                i++; 
            } else {
                arr[i] = data[mid + 1]; 
                mid++; 
                i++;
            }
        } 

        while (left <= leftEnd) {
            arr[i++] = data[left++]; 
        } 

        while ((mid+1) <= right) {
            arr[i++] = data[mid++ + 1]; 
        }


        for (i = i - 1; i>=0; i--) {
            data[right--] = arr[i]; 
        } 
    }
} 

var data = [2, 9, 5, 1, 7, 6, 4]; 
var sort = new Sort();  
//sort.bubbleSort(data); 
//sort.selectionSort(data); 
//sort.quickSort(data);
sort.mergeSort(data);