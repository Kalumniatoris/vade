package ka2.pr;

import java.io.Serializable;
import java.util.Random;

import javax.validation.constraints.NotNull;

import com.vaadin.shared.ui.colorpicker.Color;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -731086694679050916L;

	@NotNull
	@javax.validation.constraints.Size(min=3,max=32)
	String login;
	
	@NotNull
	@javax.validation.constraints.Size(min=6)
	String password;
	
	String email;
	
	@NotNull
	Color  kolor;
	

	public Account(String login, String password, String email) {
		Random rnd = new Random(System.currentTimeMillis());
		this.login = login;
		this.password = password;
		this.email = email;
		this.kolor = new Color(rnd.nextInt(255), rnd.nextInt(255),rnd.nextInt(255));
		// TODO Auto-generated constructor stub
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//public Color getKolor(){
	//	return kolor;//.getCSS().substring(1);
	//}
	
	public void setKolor(Color kolor){
		this.kolor=kolor;
	}
	
	
	public int dajRed(){
		return this.kolor.getRed();
	}
	public int dajGreen(){
		return this.kolor.getGreen();
	}
	public int dajBlue(){
		return this.kolor.getBlue();
	}
	
	public String stringGetColor(){
		return this.kolor.getCSS().substring(1);
	}
	
	
}
