package ka2.pr;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import ka2.pr.Cantr;

public class Broadcaster {
	
	private static final List<BroadcastListener> listeners = new CopyOnWriteArrayList<BroadcastListener>();
	
	public static void register(BroadcastListener listener){
		listeners.add(listener);
	}
	public static void unregister(BroadcastListener listener){
		listeners.remove(listener);
	}
	public static void broadcast(final String message){
		System.out.println("Broadcating to:");
		for (BroadcastListener listener:listeners){
			//if(l)
			System.out.println(listener.toString());
			listener.receiveBroadcast(message);
		}
	}
	
	
	public interface BroadcastListener {
		public void receiveBroadcast(String message);
		public void receiveCantr(Cantr can);
		public void recChanges(int x, int y, String newCol);	
	}
	

	
	public static void sendCantr(final Cantr can){
		for (BroadcastListener listener:listeners){
			System.out.println(listener.toString());
			listener.receiveCantr(can);
		}		
		
	}
	
	
	
	
}
