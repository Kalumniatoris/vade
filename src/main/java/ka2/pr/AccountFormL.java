package ka2.pr;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class AccountFormL extends GridLayout {
	public boolean rejestracja = false;
	@PropertyId("login")
	private TextField login = new TextField("Login:");
	
	@PropertyId("password")
	private PasswordField password = new PasswordField("Hasło:");
	
	@PropertyId("rPass")
	private PasswordField rpass = new PasswordField("Powtórz hasło");
	
	@PropertyId("email")
	private TextField email = new TextField("Email:");
	
	private Button register = new Button("Zarejestruj");
	private Button zaloguj = new Button("Zaloguj");
	
	
	public AccountFormL() {
	super(3,3);
	setSpacing(true);
	addComponent(login);
	addComponent(password);
	
	login.setRequired(true);
	password.setRequired(true);
	
	
	if(rejestracja){
		
	addComponent(rpass);
	addComponent(email);
	addComponent(register);
	}else{
		addComponent(zaloguj);
	}
	
	login.setRequired(true);
	password.setRequired(true);
	
	
	
		// TODO Auto-generated constructor stub
	}
	
}
