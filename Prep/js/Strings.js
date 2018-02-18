function findPattern (str, pattern, start) {
    for (var i=0; i<pattern.length; i++) {
        if (str.charAt(start+i) !== pattern.charAt(i)) {
            break; 
        }
    } 

    return (i === pattern.length) ? true : false; 
} 

function replacePattern (str, pattern) {
    var i = 0; 
    var j = 0; 

    while (j<str.length) {
        if (findPattern(str, pattern, j)) {
            console.log (' X '); 
            i++; 
            j+=pattern.length;  
        } else {
            console.log (' ' + str.charAt(j) + '');
            i++; 
            j++; 
        }
    } 
} 

function findOccurences (str) {
    console.log ('findOccurences'); 

    var i = 0; 
    var j = 1; 
    var count = 1;

    while (j<=str.length) {
        while(str.charAt(i) === str.charAt(j)) {
            count++; 
            i++; 
            j++; 
        } 

        console.log (' %d %s ', count, str.charAt(i));

        count = 1; 
        i++; 
        j++; 

        //console.log (' i = %d , j = %d ', i, j);
    }
} 

function isPalindrome (str) {
    if (!str || str.length === 0) {
        console.log('isPanlindrome: String is blank');
        return true; 
    } else if (str.length === 1) {
        return true; 
    } else {
        var start = 0; 
        var end = str.length - 1; 

        while (end > start) {
            if (str.charAt(start) !== str.charAt(end)) {
                return false; 
            } 
            start++;
            end--; 
        } 

        return true; 
    }
}

findOccurences ("aaabbcdde"); 
findOccurences ("a"); 

replacePattern ("abcdabc", 'abc'); 

console.log('isPalindrome: %s', isPalindrome ("abxba"));