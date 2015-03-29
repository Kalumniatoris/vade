package ka2.pr;

import com.alsnightsoft.vaadin.widgets.canvasplus.CanvasPlus;
import com.vaadin.shared.ui.colorpicker.Color;

public class Candraw {

	static void dfC(Cantr c, CanvasPlus can) {
		int cantrHeight = c.maxy;  
		int cantrWidth = c.maxx;	 
		String r, g, b;
		String rgb = "";
		double canvasHeight = can.getHeight();
		double canvasWidth = can.getWidth();

		double boxHeight = canvasHeight / cantrHeight;
		double boxWidth = canvasWidth / cantrWidth;
		can.setStrokeStyle("555555");
		for (int i = 0; i < cantrHeight; i += 1) {
			for (int j = 0; j < cantrWidth; j += 1) {

				r = Integer.toHexString(c.getR(i, j));
				if (r.length() < 2) {
					r = "0" + r.toString();
				}
				g = Integer.toHexString(c.getG(i, j));

				if (g.length() < 2) {
					g = "0" + g.toString();
				}
				b = Integer.toHexString(c.getB(i, j));
				if (b.length() < 2) {
					b = "0" + b.toString();
				}
				rgb = r + g + b;
				can.setFillStyle(rgb);

				can.fillRect(boxHeight * i, boxWidth * j, boxHeight, boxWidth);
				can.strokeRect(boxHeight * i, boxWidth * j, boxHeight, boxWidth);

			}
		}
	}

	static void drawIndividual(CanvasPlus canvas, Cantr cantr, int x, int y, String kolor) {
		double canvasHeight = canvas.getHeight();
		double canvasWidth = canvas.getWidth();
		int cantrHeight = cantr.maxy;
		int cantrWidth = cantr.maxx;
		double boxHeight = canvasHeight / cantrHeight;
		double boxWidth = canvasWidth / cantrWidth;
				
		
		int xx,yy;
		xx=(int) (x);
		yy=(int) (y);
		
		canvas.setFillStyle(kolor);
		canvas.fillRect(boxHeight * xx, boxWidth * yy, boxHeight, boxWidth);
		
		
		System.out.println("Draw:"+xx+" "+yy);
		
	}

	static void oznacz(CanvasPlus canvas, Cantr cantr, double x, double y, Color kolor) {
		int cantrHeight = cantr.maxy;
		int cantrWidth = cantr.maxx;
		//String r, g, b;
		//String rgb;
		double canvasHeight = canvas.getHeight();
		double canvasWidth = canvas.getWidth();

		double boxHeight = canvasHeight / cantrHeight;
		double boxWidth = canvasWidth / cantrWidth;
		int xa = (int) (x / boxWidth);
		int ya = (int) (y / boxHeight);
		cantr.printCurrent(xa, ya);

		cantr.setR(xa, ya, kolor.getRed());
		cantr.setG(xa, ya, kolor.getGreen());
		cantr.setB(xa, ya, kolor.getBlue());

		System.out.println( xa + "|" + ya);
		//Candraw.dfC(c, can);
		//Broadcaster.sendCantr(cantr);
		Broadcaster.sendChanges(xa, ya,kolor.getCSS().substring(1,7));

	}
	
}
