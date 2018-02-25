class Util { 

    constructor () { 
        this.options = {}; 

        this.options.log = {
            enabled: true 
        }; 
    }

    log (str, args) { 
        if (this.options.log.enabled) { 
            var a = args ? args : []; 
            a.unshift(str); 
            console.log.apply (this, a); 
        } 
    }
} 

module.exports = {
    Util: Util 
}; 
