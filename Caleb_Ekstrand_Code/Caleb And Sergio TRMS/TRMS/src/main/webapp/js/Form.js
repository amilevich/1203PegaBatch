/**
 * 
 */
//https://stackoverflow.com/questions/19728666/drop-down-box-dependent-on-the-option-selected-in-another-drop-down-box
function dynamicdropdown(listindex)
    {
		let select = document.getElementById("pass-grade");
		var length = select.options.length;
		for (i = 0; i < length; i++) {
			  select.options[i] = null;
			}
        switch (listindex)
        {
        case "1" :
            select.options[0]=new Option("Default","Default");
            select.options[1]=new Option("A","A");
            select.options[2]=new Option("B","B");
            select.options[3]=new Option("C","C");
            select.options[4]=new Option("D","D");
            select.options[5]=new Option("F","F");
            
            break;
        case "2" :
        	select.options[0]=new Option("Default","Default");
        	select.options[1]=new Option("90%","90%");
        	select.options[2]=new Option("80%","80%");
        	select.options[3]=new Option("70%","70%");
        	select.options[4]=new Option("60%","60%");
            break;
        
    	case "3" :
    		for (i = 0; i < length; i++) {
    			  select.options[i] = null;
    			}
    		select.options[0]=new Option("Pass","Pass");

    		break;
        }
    
        return true;
    }