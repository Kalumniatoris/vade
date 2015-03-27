package ka2.pr;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;

public class AccountFormL2 extends GridLayout {
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
	private Label lIAR = new Label("Logowanie/Rejestracja");
	
	public AccountFormL2() {
	super(2,5);
		
		
		
	setSpacing(true);
	addComponent(login,0,0);
	addComponent(password,0,1);
	
	Slider sMode = new Slider();  //0 - logowanie, 1  -rejestracja
	sMode.setImmediate(false);
	sMode.setMin(0.0);
	sMode.setMax(1.0);
	sMode.setValue(0.0);
	
	sMode.addValueChangeListener(new ValueChangeListener() {
		
		@Override
		public void valueChange(ValueChangeEvent event) {
			double n = (double) event.getProperty().getValue();
			if(n==0){logowanie();}
			else{rejestracja();}
			System.out.println(n);
			
		}
	});
	
	addComponent(sMode, 0, 3);
	addComponent(lIAR,1,3);
	
	login.setRequired(true);
	password.setRequired(true);
		
	login.addTextChangeListener(new TextChangeListener() {
		
		@Override
		public void textChange(TextChangeEvent event) {
			int len = event.getText().length();
			
			if (len <=3) {
				System.out.println("Za krótkie");
			}
			
		}
	});
	
	
	}
	
	public void rejestracja(){
	removeComponent(zaloguj);
	addComponent(rpass,1,1);
	addComponent(email,0,2);
	rpass.setRequired(true);
	email.setRequired(true);
	
	addComponent(register,0,4);
	

	
	}
	public void logowanie(){
	removeComponent(rpass);
	removeComponent(email);
	removeComponent(register);
	rpass.setRequired(false);
	email.setRequired(false);
	addComponent(zaloguj,0,4);
	}
	
	
	
}
