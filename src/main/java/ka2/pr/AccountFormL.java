package ka2.pr;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class AccountFormL extends GridLayout {
	@PropertyId("login")
	private TextField login = new TextField("Login:");
	
	@PropertyId("password")
	private PasswordField password = new PasswordField("Hasło:");
	
	@PropertyId("rPass")
	private PasswordField rpass = new PasswordField("Powtórz hasło");
	
	@PropertyId("email")
	private TextField email = new TextField("Email:");
	
	
	
	public AccountFormL() {
	super(2,3);
	setSpacing(true);
	addComponent(login);
	addComponent(password);
	addComponent(email);
		// TODO Auto-generated constructor stub
	}
	
}
