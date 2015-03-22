package ka2.pr;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.aria.client.MainRole;
import com.google.web.bindery.requestfactory.apt.RfValidator;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Form;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import elemental.html.Console;

/**
 *
 */
@Theme("mytheme")
@Widgetset("ka2.pr.MyAppWidgetset")
public class MyUI extends UI {

	Navigator navigator;
	protected static final String MAINVIEW = "main";
	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("Avedacosta");
    	
    	VerticalLayout mLayout = new VerticalLayout();
    	mLayout.setMargin(true);
    	
    	navigator = new Navigator(this, this);
    	
    	navigator.addView("", new StartView());    	
    	navigator.addView(MAINVIEW, new MainView());


    }

    
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    	
    }
    
   //////Views

    public class StartView extends VerticalLayout implements View{
    public StartView() {
    	boolean register = false;
    	
		setSizeFull();
		//FieldGroup fgForm = new FieldGroup();
		FieldGroup fgAccount = new BeanFieldGroup<Account>(Account.class);
		fgAccount.setItemDataSource(new BeanItem<Account>(new Account("Login","Password","email")));
		//final Form form = new Form();
		
		final TextField tfLogin = new TextField("Login:");
		final PasswordField pfPass = new PasswordField("Hasło:");
		final PasswordField pfRPass = new PasswordField("Potwierdź hasło:");
		
		
		pfPass.setRequired(true);
		tfLogin.setRequired(true);
		
		AccountFormL afl = new AccountFormL();
		fgAccount.bindMemberFields(afl);
		
		Label lblLogin = new Label("Login");
		for (Object propertyId : fgAccount.getUnboundPropertyIds()){
			System.out.println(propertyId);
			addComponent(fgAccount.buildAndBind(propertyId));
		}
		
		//fgForm.a
		//form.addField("login", tfLogin);
		//form.addField("password", pfPass);
		
		addComponent(afl);
		
		final Button btnStart = new Button("Zaloguj", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(MAINVIEW);
				
			}
		});
		
		final Button btnRegister = new Button("Zarejestruj", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				addComponent(pfRPass);
				pfRPass.setRequired(true);
				pfRPass.addValidator(new Validator() {
					
					@Override
					public void validate(Object value) throws InvalidValueException {
						if(pfRPass.getValue() != pfPass.getValue()){
							throw new InvalidValueException("Hasła się nie pokrywają");
						}
						
					}
				});
			}
		});
		
		
		
		/*
		addComponent(tfLogin);
		addComponent(pfPass);		

		addComponent(lblLogin);
		addComponent(btnStart);
		*/
		addComponent(btnRegister);
		
		
		
	}	
    	
    	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
    	
    	
    	
    }
    
    public class MainView extends VerticalLayout implements View{
        public MainView() {
    		setSizeFull();
    		
    		Button btnStart = new Button("Powrót", new ClickListener() {
    			
    			@Override
    			public void buttonClick(ClickEvent event) {
    				navigator.navigateTo("");
    				
    			}
    		});
    		addComponent(btnStart);
    		
    	}	
        	
        	
    	@Override
    	public void enter(ViewChangeEvent event) {
    		// TODO Auto-generated method stub
    		
    	}
        	
        	
        	
        }
    
}
