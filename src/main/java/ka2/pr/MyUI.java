package ka2.pr;

import java.awt.Color;
import java.util.Random;

import javax.servlet.annotation.WebServlet;

import com.alsnightsoft.vaadin.widgets.canvasplus.CanvasPlus;
import com.alsnightsoft.vaadin.widgets.canvasplus.CanvasPlus.CanvasClickDownListener;
import com.alsnightsoft.vaadin.widgets.canvasplus.CanvasPlus.CanvasClickUpListener;
import com.alsnightsoft.vaadin.widgets.canvasplus.CanvasPlus.CanvasMouseMoveListener;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;










/*
import org.vaadin.hezamu.canvas.Canvas;
import org.vaadin.hezamu.canvas.Canvas.CanvasMouseDownListener;
import org.vaadin.hezamu.canvas.Canvas.CanvasMouseMoveListener;
*/
import ka2.pr.*;

/**
 *
 */
@Theme("mytheme")
@Widgetset("ka2.pr.MyAppWidgetset")
public class MyUI extends UI {
	Cantr cantr=new Cantr();
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
		FieldGroup fgAccount = new BeanFieldGroup<Account>(Account.class);
		fgAccount.setItemDataSource(new BeanItem<Account>(new Account("Login","Password","email")));
		final AccountFormL afl = new AccountFormL(); 
		fgAccount.bindMemberFields(afl);
		
		
	
		
		
		//addComponent(sMode);
		//AccountFormL afl = new AccountFormL();
		
		
		Label lblLogin = new Label("Login");
		for (Object propertyId : fgAccount.getUnboundPropertyIds()){
			System.out.println(propertyId);
			addComponent(fgAccount.buildAndBind(propertyId));
		}
		
		addComponent(afl);
		
		addComponent(new Button("test", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(MAINVIEW);
				
			}
		}));
		
	}	
    	
    	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
    	
    	
    	
    }
    
    public class MainView extends VerticalLayout implements View{
    boolean vird = false;
    CanvasPlus can = new CanvasPlus();
   
    
        public MainView() {
    		setSizeFull();
    		
    		Button btnBack = new Button("Powrót", new ClickListener() {
    			
    			@Override
    			public void buttonClick(ClickEvent event) {
    				navigator.navigateTo("");
    				
    			}
    		});
    		addComponent(btnBack);
    		cantr.setG(3, 5, 255);
    		cantr.setB(8, 4, 120);
    		System.out.println(cantr.getG(3, 5)+"%%"+cantr.getB(8, 4));
    		addComponent(can);
    		  

   can.setWidth("200px");
   can.setHeight("200px");
   can.setFillStyle("000000");
   can.fillRect(0.0, 0.0,200.0,200.0);
   dfC(cantr, can);
   //can.setStrokeStyle(15,25,200);
   /*
   can.setFillStyle("55faa1");
   can.fillRect(50.0, 50.0,100.0,100.0);
   final Random rnd = new Random();
   double ex=rnd.nextDouble()*200;
   can.setFillStyle("40a10F");
   can.fillRect(ex, 170.0,10.0,50.0);
   //can.set
    */
    
   can.setStyleName("dravca");
   
   
   can.setLineWidth(1.0);
   
    	can.addMouseMoveListener(new CanvasMouseMoveListener() {			
			@Override
			public void onMove(MouseEventDetails mouseDetails) {
						double qx=(double)mouseDetails.getRelativeX();
						double qy=(double)mouseDetails.getRelativeY();
			if(vird){			
			
			can.lineTo(qx, qy);
			can.stroke();
			
			
			can.stroke();}
							
			}
		} );
    can.addClickDownListener(new CanvasClickDownListener() {		
		@Override
		public void onClickDown(MouseEventDetails mouseDetails) {
			double qx=(double)mouseDetails.getRelativeX();
			double qy=(double)mouseDetails.getRelativeY();
			vird=true;
			can.beginPath();
			can.setStrokeStyle(255, 75, 12);
		
			System.out.println("x:"+qx+" y:"+qy);
			oznacz(can, cantr, qx, qy);
			
		}
	});		
    can.addClickUpListener(new CanvasClickUpListener() {
		
		@Override
		public void onClickUp(MouseEventDetails mouseDetails) {
			vird=false;
			can.closePath();
			
			
		}
	});
    		
 
    	
 }	
        
  void dfC(Cantr c, CanvasPlus can){
	 int h=c.maxy ;
	 int w=c.maxx;
	 String r,g,b;
	 String rr,gg,bb;
	 String rgb="";
	 double canh=can.getHeight();
	 double canw=can.getWidth();
	 
	 double qh = canh/h;
	 double qw = canw/w;
	 can.setStrokeStyle("555555");
	 for(int i=0;i<h;i+=1){
		for(int j=0;j<w;j+=1){		 
			 //can.setStrokeStyle(c.getR(i, j), c.getG(i, j), c.getB(i, j));
			
		  r = Integer.toHexString(c.getR(i, j));
		  //System.out.println("r:"+r);
		  if (r.length() < 2) {			  
			   r="0"+r.toString();
			}
		  //System.out.println("rr:"+r);
		  
		  g = Integer.toHexString(c.getG(i, j));
		 //System.out.println("g:"+g);
		  if (g.length() < 2) {			  
			   g="0"+g.toString();
			}
		  //System.out.println("gg:"+g);
		  
		  b = Integer.toHexString(c.getB(i, j));
		  //System.out.println("b:"+b);
		  if (b.length() < 2) {			 
			   b="0"+b.toString();
			}
		  //System.out.println("bb:"+b);		  
		  rgb=r+g+b;
		  //System.out.println("rgb:"+rgb);
		  
		  //while(rgb.length()>6){rgb=rgb.substring(1,rgb.length());}
		  //System.out.println("rgbq:"+rgb);
		  can.setFillStyle(rgb);
		  //System.out.println(i+","+j+":"+rgb);
		
		  can.fillRect(qh*i, qw*j, qh, qw);
		  can.strokeRect(qh*i, qw*j, qh, qw);
		  
		 // System.out.println(rgb);
		}
	 }
	 //System.out.println("all");
	// c.drr();
  }
  void oznacz(CanvasPlus can,Cantr c,double x,double y){
	  int h=c.maxy ;
	  int w=c.maxx;
	  String r,g,b;
	  String rgb;
	  double canh=can.getHeight();
	  double canw=can.getWidth();
		 
	  double qh = canh/h;
	  double qw = canw/w;
	  int xa = (int)(x/qw);
	  int ya = (int)(y/qh);
	 c.printCurrent(xa, ya);
	  
	  c.setR(xa,ya, c.getR(xa,ya)+5);
	  System.out.println((int)(x/qw)+"|"+(int)(y/qh));
	  dfC(c,can);
	  
  }
        	
        	
        	
    	@Override
    	public void enter(ViewChangeEvent event) {
    		// TODO Auto-generated method stub
    		    		
    	}
        	
        	
        	
        }
    
}
