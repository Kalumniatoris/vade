package ka2.pr;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.aria.client.MainRole;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
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
    	
		setSizeFull();
		TextField tfLogin = new TextField("Login:");
		PasswordField pfPass = new PasswordField("Hasło:");
		
		Button btnStart = new Button("Rozpocznij", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(MAINVIEW);
				
			}
		});
		addComponent(btnStart);
		addComponent( tfLogin);
		addComponent(pfPass);
		
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
