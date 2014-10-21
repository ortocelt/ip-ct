package webservice;

import java.rmi.RemoteException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.testiranje.bean.UserBean;
import com.testiranje.dao.PolaganjeDao;

import webservice.ValidatorKreditneKarticeStub.GetCreditCardType;


@ManagedBean
@SessionScoped
public class wsBean {
	
	private int aClient;
	private int bClient;
	private String testWS;
	private String broj;
	private String sifraTesta;
	private int testId;
	private int userId;
	
	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getTestWS() {
		return testWS;
	}

	public void setTestWS(String testWS) {
		this.testWS = testWS;
	}

	private int rez;

	public int getRez() {
		return rez;
	}

	public void setRez(int rez) {
		this.rez = rez;
	}

	public int getaClient() {
		return aClient;
	}

	public void setaClient(int aClient) {
		this.aClient = aClient;
	}

	public int getbClient() {
		return bClient;
	}

	public void setbClient(int bClient) {
		this.bClient = bClient;
	}

	public wsBean() {
		// TODO Auto-generated constructor stub
	}
/*	
	public void validateCreditCard() throws RemoteException{
		CreditCardValidatorStub stub = new CreditCardValidatorStub();
		IsCreditCardValid valid = new IsCreditCardValid();	
		valid.setNumber(this.creditCardNumber);			
		this.responseMessage = stub.isCreditCardValid(valid).get_return();
	}
	*/
	
	/*public String vratiZbir() throws RemoteException {
		ValidatorKreditneKarticeStub stub = new ValidatorKreditneKarticeStub();
		Saberi saberi = new Saberi();
		GetCreditCardType 
		Test test1 = new Test();
		saberi.setA(getaClient());
		saberi.setB(getbClient());
		setTestWS(stub.test(test1).get_return());
		setRez(stub.saberi(saberi).get_return());
		return "";
	}
	*/
	public String prijavaTesta(int testId, int userId){
		setTestId(testId);
		setUserId(userId);
		return "/pages/kandidat/prijavaTesta.xhtml";
		
	}
	public void generisanjeSifre() throws RemoteException {
		
		
		//treba zalijepiti izgenerisanu sifru
		//u polaganje za tacan user id i test id
		//prethodno izvrsiti provjertu da li je 
		//kartica validna
		//u ovu metodu proslijediti gotove informacije za insert
				
		ValidatorKreditneKarticeStub stub = new ValidatorKreditneKarticeStub();
		GetCreditCardType gcct = new GetCreditCardType();
		gcct.setCreditCardNumber(getBroj());
		setSifraTesta(stub.getCreditCardType(gcct).get_return());
		String tempSifra = stub.getCreditCardType(gcct).get_return();
		
		if (!tempSifra.contains("Uplata")){		
		int ret = PolaganjeDao.postavljanjeSifre(tempSifra, getTestId(), getUserId());
		
			if (ret == 1)			
				setSifraTesta(tempSifra);
			else
				setSifraTesta("Vec ste prijavljeni na ovaj test!");
		}
		
		}
	

	public String getSifraTesta() {
		return sifraTesta;
	}

	public void setSifraTesta(String sifraTesta) {
			this.sifraTesta = sifraTesta;
	}
	
}

