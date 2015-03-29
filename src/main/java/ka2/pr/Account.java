package ka2.pr;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.vaadin.shared.ui.colorpicker.Color;

public class Account implements Serializable{
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
		this.login = login;
		this.password = password;
		this.email = email;
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
	
	public int getRed(){
		return this.kolor.getRed();
	}
	public int getGreen(){
		return this.kolor.getGreen();
	}
	public int getBlue(){
		return this.kolor.getBlue();
	}
	
}
