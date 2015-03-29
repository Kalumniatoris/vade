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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.colorpicker.ColorChangeEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeListener;

/**
 *
 */
@Theme("mytheme")
@Widgetset("ka2.pr.MyAppWidgetset")
@Push
public class MyUI extends UI implements BroadcastListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2112102342095057720L;
	/**
	 * 
	 */
	
	
	static Cantr cantr = new Cantr();
	CanvasPlus can = new CanvasPlus();
	Color kolor = new Color(00, 255, 255); // TODO podpiąć pod użytkownika
		int punkty = 0;
	Label lblPunkty = new Label();

	Navigator navigator;
	protected static final String MAINVIEW = "main";

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		if(getSession().getAttribute("User")!=null)
		kolor=(Color) getSession().getAttribute("Kolor");
	
		
		getPage().setTitle("Avedacosta");

		VerticalLayout mLayout = new VerticalLayout();
		mLayout.setMargin(true);

		navigator = new Navigator(this, this);

		navigator.addView("", new StartView());
		navigator.addView(MAINVIEW, new MainView());

		new InitializerThread().start();
		Broadcaster.register(this);

	}

	@Override
	public void detach() {

		Broadcaster.unregister(this);
		super.detach();
	}

	class InitializerThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
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

		/**
		 * 
		 */
		private static final long serialVersionUID = -6257181833290857564L;

	}

	// ////Views

	public class StartView extends VerticalLayout implements View {
		/**
		 * 
		 */
		private static final long serialVersionUID = -28582932550420135L;

		public StartView() {
			//boolean register = false;
			//ColorPicker cPic = new ColorPicker();
			setSizeFull();
			FieldGroup fgAccount = new BeanFieldGroup<Account>(Account.class);
			fgAccount.setItemDataSource(new BeanItem<Account>(new Account(
					"Login", "Password", "email")));

			
			final AccountFormL afl = new AccountFormL();
			fgAccount.bindMemberFields(afl);
			

			// addComponent(sMode);
			// AccountFormL afl = new AccountFormL();

			//Label lblLogin = new Label("Login");
			for (Object propertyId : fgAccount.getUnboundPropertyIds()) {
				
				System.out.println(propertyId.toString());
				
				addComponent(fgAccount.buildAndBind(propertyId));
			}

			addComponent(afl);

			addComponent(new Button("test", new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 8586976835683579984L;

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

	public class MainView extends VerticalLayout implements View {
		/**
		 * 
		 */
		private static final long serialVersionUID = -898130971944305672L;
		boolean klikniete = false;
		ColorPicker colpick = new ColorPicker("Kolor");

		//

		public MainView() {
			HorizontalLayout hl = new HorizontalLayout();
			VerticalLayout vl1 = new VerticalLayout();
			VerticalLayout vl2 = new VerticalLayout();
			hl.addComponent(vl1);
			hl.addComponent(vl2);

			setSizeFull();
			Button btnBack = new Button("Powrót", new ClickListener() {
				/**
				 * 
				 */
				private static final long serialVersionUID = -5099477293776468281L;

				@Override
				public void buttonClick(ClickEvent event) {
					navigator.navigateTo("");

				}
			});
			vl1.addComponent(btnBack);
			vl2.addComponent(colpick);

			vl2.addComponent(lblPunkty);
			colpick.addColorChangeListener(new ColorChangeListener() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 587900936681258674L;

				@Override
				public void colorChanged(ColorChangeEvent event) {
					kolor = event.getColor();
					getSession().getAttribute("kolor");
					// TODO KOLOR

				}
			});

			vl1.addComponent(can);

			can.setWidth("400px");
			can.setHeight("400px");
			can.setFillStyle("000000");
			can.fillRect(0.0, 0.0, 200.0, 200.0);
			// TODO CANDR
			Candraw.dfC(cantr, can);

			can.setStyleName("dravca");

			can.setLineWidth(1.0);

			can.addClickDownListener(new CanvasClickDownListener() {
				@Override
				public void onClickDown(MouseEventDetails mouseDetails) {
					double qx = (double) mouseDetails.getRelativeX();
					double qy = (double) mouseDetails.getRelativeY();
					klikniete = true;

					System.out.println("x:" + qx + " y:" + qy);
					Candraw.oznacz(can, cantr, qx, qy, kolor);
					Broadcaster.broadcast(can.toString());

				}
			});

			can.addClickUpListener(new CanvasClickUpListener() {
				@Override
				public void onClickUp(MouseEventDetails mouseDetails) {
					klikniete = false;

				}
			});

			addComponent(hl);

		}

		@Override
		public void enter(ViewChangeEvent event) {
			// Broadcaster.register(this);
		}

	}

	@Override
	public void receiveBroadcast(final String message) {
		access(new Runnable() {
			@Override
			public void run() {
				/*
				System.out.println("TEST");
				Notification n = new Notification("M:", message,
						Type.TRAY_NOTIFICATION);
				n.show(getPage());
				 */
			}
		});

	}

	@Override
	public void receiveCantr(Cantr can) {
		cantr = can;
		Candraw.dfC(cantr, this.can);
	}

	@Override
	public void recChanges(int x, int y, String newCol) {

		Candraw.drawIndividual(can, cantr, x, y, newCol);
		System.out.println("kolor: " + kolor.getCSS().substring(1)
				+ " UpdtKol: " + newCol);
		if (newCol.compareTo(kolor.getCSS().substring(1)) != 0) {
			int akt = cantr.ileK(kolor);
			punkty += akt;
			lblPunkty.setValue(getSession().getAttribute("User")+"\n"+"Aktualne: " + akt + "\n" + "Razem: " + punkty);
		}

		// System.out.println(cantr.ileK(kolor));

	}

}
