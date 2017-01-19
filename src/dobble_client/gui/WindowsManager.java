package dobble_client.gui;




import javax.swing.JFrame;


public class WindowsManager {
    private ServerWindow serverWindow;
    private GameWindow gameWindow;
    
    public WindowsManager() {
                      
    }
    
    public void setWindows(ServerWindow fr1, GameWindow fr2) {
        this.serverWindow = fr1;
        this.gameWindow = fr2; 
        fr1.setVisible(true);
        fr2.setVisible(false);
    }
    
    public void showWindow(JFrame toBeShown){
        serverWindow.setVisible(toBeShown == serverWindow);
        gameWindow.setVisible(toBeShown == gameWindow);
    }
    
    
 /*   public JFrame getFocusWindow() {
        if (serverWindow.isVisible()) {
            return serverWindow;
        } else {
            return gameWindow;
        }
    }*/
    
    public GameWindow getGameWindow() {
        return gameWindow;
    }
    
    public ServerWindow getServerWindow() {
        return serverWindow;
    }
    
    
}
