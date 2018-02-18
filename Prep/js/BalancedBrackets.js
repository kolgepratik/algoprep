var StackQueue = require('./StackQueue.js');

function balancedBrackets (str) {
    if (!str || str.length < 1) {
        console.error('balancedBrackets: String is blank'); 
        return true; 
    } else {
        var s = new StackQueue.Stack(); 
        var mappings = []; 
        mappings['{'] = '}';
        mappings['('] = ')';
        mappings['['] = ']'; 
        
        var keys = Object.keys(mappings); 
        var values = Object.values(mappings); 

        for (var i=0; i<str.length; i++) {
            var current = str.charAt(i); 

            if (keys.indexOf(current) !== -1) {
                s.push(current); 
            } else if (values.indexOf(current) !== -1) { 
                var prev = s.pop();

                if (mappings[prev] !== current) {
                    return false; 
                }
            } 
        } 

        return true; 
    }
} 

console.log('balancedBrackets: %s', balancedBrackets ('{(-x)()}'));