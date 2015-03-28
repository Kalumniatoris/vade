package ka2.pr;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import com.alsnightsoft.vaadin.widgets.canvasplus.CanvasPlus;
import com.vaadin.shared.ui.colorpicker.Color;

public class Candraw {

	static void dfC(Cantr c, CanvasPlus can) {
		int h = c.maxy;
		int w = c.maxx;
		String r, g, b;
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

				can.fillRect(qh * i, qw * j, qh, qw);
				can.strokeRect(qh * i, qw * j, qh, qw);

			}
		}
	}

	static void drawIndividual(CanvasPlus can, Cantr c, double x, double y,	String kolor) {
		double canh = can.getHeight();
		double canw = can.getWidth();
		int h = c.maxy;
		int w = c.maxx;
		double qh = canh / h;
		double qw = canw / w;
				
		
		int xx,yy;
		xx=(int) (qh*x);
		yy=(int) (qw*y);
		
		can.setFillStyle(kolor);
		can.fillRect(qh * xx, qw * yy, qh, qw);
		System.out.println("Draw:"+xx+" "+yy);
		
	}

	static void oznacz(CanvasPlus can, Cantr c, double x, double y, Color kolor) {
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

		System.out.println( xa + "|" + ya);
		//Candraw.dfC(c, can);
		//Broadcaster.sendCantr(c);
		Broadcaster.sendChanges(xa, ya,kolor.getCSS().substring(1,7));

	}
}
