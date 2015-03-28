package ka2.pr;

import javax.servlet.annotation.WebServlet;

import ka2.pr.Broadcaster.BroadcastListener;

import com.alsnightsoft.vaadin.widgets.canvasplus.CanvasPlus;
import com.alsnightsoft.vaadin.widgets.canvasplus.CanvasPlus.CanvasClickDownListener;
import com.alsnightsoft.vaadin.widgets.canvasplus.CanvasPlus.CanvasClickUpListener;
import com.vaadin.annotations.Push;
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
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.colorpicker.ColorChangeEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeListener;
/*
 import org.vaadin.hezamu.canvas.Canvas;
 import org.vaadin.hezamu.canvas.Canvas.CanvasMouseDownListener;
 import org.vaadin.hezamu.canvas.Canvas.CanvasMouseMoveListener;
 */

/**
 *
 */
@Theme("mytheme")
@Widgetset("ka2.pr.MyAppWidgetset")
public class MyUI extends UI implements BroadcastListener{
	static Cantr cantr = new Cantr();
	
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
		
		new InitializerThread().start();
		

	}
	
	  @Override
	    public void detach() {
		  //TODO how to do on exit of view
	        Broadcaster.unregister(this);
	        super.detach();
	    }


	
	class InitializerThread extends Thread{
		@Override
		public void run(){ 
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
		}
		access(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("loaded");
				
			}
		});
			
	}
	}

	
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

	}

	// ////Views

	public class StartView extends VerticalLayout implements View {
		public StartView() {
			boolean register = false;

			setSizeFull();
			FieldGroup fgAccount = new BeanFieldGroup<Account>(Account.class);
			fgAccount.setItemDataSource(new BeanItem<Account>(new Account(
					"Login", "Password", "email")));
			final AccountFormL afl = new AccountFormL();
			fgAccount.bindMemberFields(afl);

			// addComponent(sMode);
			// AccountFormL afl = new AccountFormL();

			Label lblLogin = new Label("Login");
			for (Object propertyId : fgAccount.getUnboundPropertyIds()) {
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
	@Push
	public class MainView extends VerticalLayout implements View,BroadcastListener {
		Color kolor= new Color(0, 0, 0);
		boolean klikniete = false;
		CanvasPlus can = new CanvasPlus();
		ColorPicker colpick = new ColorPicker();
		

		public MainView() {
			HorizontalLayout hl = new HorizontalLayout();
			VerticalLayout vl1 = new VerticalLayout();
			VerticalLayout vl2 = new VerticalLayout();
			hl.addComponent(vl1);
			hl.addComponent(vl2);
			
			
			
			setSizeFull();

			Button btnBack = new Button("Powrót", new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					navigator.navigateTo("");

				}
			});
			vl1.addComponent(btnBack);
			vl2.addComponent(colpick);
			colpick.addColorChangeListener(new ColorChangeListener() {
				
				@Override
				public void colorChanged(ColorChangeEvent event) {
					kolor=event.getColor();
				//TODO KOLOR
					
					
					
				}
			});
			cantr.setG(3, 5, 255);
			cantr.setB(8, 4, 120);
			System.out.println(cantr.getG(3, 5) + "%%" + cantr.getB(8, 4));
			
			vl1.addComponent(can);

			can.setWidth("400px");
			can.setHeight("400px");
			can.setFillStyle("000000");
			can.fillRect(0.0, 0.0, 200.0, 200.0);
			dfC(cantr, can);

			can.setStyleName("dravca");

			can.setLineWidth(1.0);

	
			
			can.addClickDownListener(new CanvasClickDownListener() {
				@Override
				public void onClickDown(MouseEventDetails mouseDetails) {
					double qx = (double) mouseDetails.getRelativeX();
					double qy = (double) mouseDetails.getRelativeY();
					klikniete = true;
					

					System.out.println("x:" + qx + " y:" + qy);
					oznacz(can, cantr, qx, qy);
					
					Broadcaster.broadcast(can.toString());

				}
			});
			can.addClickUpListener(new CanvasClickUpListener() {

				@Override
				public void onClickUp(MouseEventDetails mouseDetails) {
					klikniete = false;
					can.closePath();

				}
			});
			
			addComponent(hl);

		}

		void dfC(Cantr c, CanvasPlus can) {
			int h = c.maxy;
			int w = c.maxx;
			String r, g, b;
			// String rr,gg,bb;
			String rgb = "";
			double canh = can.getHeight();
			double canw = can.getWidth();

			double qh = canh / h;
			double qw = canw / w;
			can.setStrokeStyle("555555");
			for (int i = 0; i < h; i += 1) {
				for (int j = 0; j < w; j += 1) {

					r = Integer.toHexString(c.getR(i, j));
					if (r.length() < 2) {
						r = "0" + r.toString();
					}
					// System.out.println("rr:"+r);

					g = Integer.toHexString(c.getG(i, j));
					// System.out.println("g:"+g);
					if (g.length() < 2) {
						g = "0" + g.toString();
					}

					b = Integer.toHexString(c.getB(i, j));
					if (b.length() < 2) {
						b = "0" + b.toString();
					}
					rgb = r + g + b;
					can.setFillStyle(rgb);

					can.fillRect(qh * i, qw * j, qh, qw);
					can.strokeRect(qh * i, qw * j, qh, qw);

					// System.out.println(rgb);
				}
			}
			// System.out.println("all");
			// c.drr();
		}

		void oznacz(CanvasPlus can, Cantr c, double x, double y) {
			int h = c.maxy;
			int w = c.maxx;
			String r, g, b;
			String rgb;
			double canh = can.getHeight();
			double canw = can.getWidth();

			double qh = canh / h;
			double qw = canw / w;
			int xa = (int) (x / qw);
			int ya = (int) (y / qh);
			c.printCurrent(xa, ya);
			
		
			c.setR(xa, ya, kolor.getRed());
			c.setG(xa, ya, kolor.getGreen());
			c.setB(xa, ya, kolor.getBlue());

			System.out.println((int) (x / qw) + "|" + (int) (y / qh));
			dfC(c, can);
			Broadcaster.sendCantr(c);

		}

		@Override
		public void enter(ViewChangeEvent event) {
			// TODO Auto-generated method stub
			Broadcaster.register(this);
		}
		
		

		@Override
		public void receiveBroadcast(final String message) {
			access(new Runnable() {
				@Override
				public void run() {
					System.out.println("TEST");
					Notification n = new Notification("M:",message, Type.TRAY_NOTIFICATION);
					n.show(getPage());
					
				}
			});
			
		}

		@Override
		public void receiveCantr(Cantr can) {
			// TODO Auto-generated method stub
			cantr = can;
			dfC(cantr, this.can);
		}

	}
	@Override
	public void receiveBroadcast(String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void receiveCantr(Cantr can) {
		// TODO Auto-generated method stub
		
	}
	
	

}
