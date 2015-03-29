package ka2.pr;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class AccountFormL extends GridLayout {
Map<String, String> users;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8892263606851105713L;
	public boolean rejestracja = false;
	@PropertyId("login")
	@javax.validation.constraints.Size(min=3,max=32)
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
	
	@PropertyId("kolor")
	private ColorPicker color = new ColorPicker();
	
	//@PropertyId("kolor")
	//private TextField color = new TextField();
	private Label lbLogWarn = new Label();
	private Label lbPassWarn = new Label();
	private Label lbEmailWarn = new Label();
	public AccountFormL() {		
	super(3,5);
	
	users=new HashMap<String, String>();
	users.put("username","password");
	users.put("ktos", "cos");
	users.put("inne","haslo");
		
		
	setSpacing(true);
	addComponent(login,0,0);
	addComponent(lbLogWarn,2,0);
	addComponent(password,0,1);
	addComponent(lbPassWarn,2,1);
	addComponent(lbEmailWarn,2,2);
	addComponent(zaloguj,0,4);
	
	Slider sMode = new Slider();  //0 - logowanie, 1  -rejestracja
	sMode.setImmediate(false);
	sMode.setMin(0.0);
	sMode.setMax(1.0);
	sMode.setValue(0.0);
	
	sMode.addValueChangeListener(new ValueChangeListener() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 959637761432394752L;

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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1444764300050723717L;

		@Override
		public void textChange(TextChangeEvent event) {
			int len = event.getText().length();
			
			if (len <=3) {
				System.out.println("Za krótkie");
			}
			
		}
	});

	password.addBlurListener(new BlurListener() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 5371415070409616745L;

		@Override
		public void blur(BlurEvent event) {
			// TODO Auto-generated method stub
			System.out.println(validPassword());
			
		}
	});
	
	rpass.addBlurListener(new BlurListener() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -6499982428485784575L;

		@Override
		public void blur(BlurEvent event) {
			// TODO Auto-generated method stub
			System.out.println(validPassword());
		}
	});
	
	zaloguj.addClickListener(new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(!login.isValid() || !password.isValid()){
				return;
			}
		String username = login.getValue();
		String passw = password.getValue();

		
		boolean aaa;
		users.containsKey(username);
		users.get(username);
		boolean isValid = users.get(username).equals(passw)&&users.containsKey(username);
		//boolean isValid = username.equals("username")&&passw.equals("password");
		
		
		if (isValid){
			Random rnd = new Random();
			getSession().setAttribute("User", username);
			getSession().setAttribute("Kolor", new Color(rnd.nextInt(255),77,77));
			getUI().getNavigator().navigateTo("main");
			
		}
		else{
			System.out.println("Coś nie jest ");
		}
			
			
			
		}
	});
	
	}
	
	public void rejestracja(){
	removeComponent(zaloguj);
	addComponent(rpass,1,1);
	addComponent(email,0,2);
	addComponent(color,1,2);
	rpass.setRequired(true);
	email.setRequired(true);
	
	addComponent(register,0,4);
	

	
	}
	public void logowanie(){
	removeComponent(color);
	removeComponent(rpass);
	removeComponent(email);
	removeComponent(register);
	rpass.setRequired(false);
	email.setRequired(false);
	addComponent(zaloguj,0,4);
	}
	
	
	
	private boolean validPassword(){
		if(!rejestracja)return true;
		if(password.getValue().equals(rpass.getValue()))
		{lbPassWarn.setValue("");	
		return true;}
		else{ 			
			lbPassWarn.setValue("Niezgodne hasla");			
			return false;
		}
		
		
	}
	
	
	
}
