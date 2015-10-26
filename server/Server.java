import server.gui.ServerGUI;

public class Server {

	public static void main(String[] args) {
		ServerGUI server = new ServerGUI();
		server.initialize();
		System.out.println("Server has been run!");
	}

}
