package webService;

import java.util.Random;
//import java.util.regex.Pattern;

public class ValidatorKreditneKartice {

	public ValidatorKreditneKartice() {
		// TODO Auto-generated constructor stub
	}
	
		
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	String randomString( ) 
	{
	   StringBuilder sb = new StringBuilder( 10 );
	   for( int i = 0; i < 10; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	
	public String GetCreditCardType(String CreditCardNumber)
    {
       /* Pattern regVisa = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");        
        Pattern regMaster = Pattern.compile("^5[1-5][0-9]{14}$");
        Pattern regExpress = Pattern.compile("^3[47][0-9]{13}$");
        Pattern regDiners = Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
        Pattern regDiscover = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
        Pattern regJSB= Pattern.compile("^(?:2131|1800|35\\d{3})\\d{11}$");
        */
        boolean visa = CreditCardNumber.matches("^4[0-9]{12}(?:[0-9]{3})?$");
        boolean master = CreditCardNumber.matches("^5[1-5][0-9]{14}$");
        boolean express = CreditCardNumber.matches("^3[47][0-9]{13}$");
        boolean diners = CreditCardNumber.matches("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
        boolean discover = CreditCardNumber.matches("^6(?:011|5[0-9]{2})[0-9]{12}$");
        
        


        /*if    ((regVisa.matches(CreditCardNumber) != null)
        	|| (regMaster.matcher(CreditCardNumber) != null)
        	|| (regExpress.matcher(CreditCardNumber) != null)
        	|| (regDiners.matcher(CreditCardNumber) != null)
        	|| (regDiscover.matcher(CreditCardNumber) != null)
        	|| (regJSB.matcher(CreditCardNumber) != null))*/
        
        if (visa || master || express || diners || discover)
            return randomString();
        else
        return "Uplata nije prosla, pokusajte sa validnom karticom";
    }

}
