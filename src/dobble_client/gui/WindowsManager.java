package dobble_client.gui;




import javax.swing.JFrame;


public class WindowsManager {
    private JFrame serverWindow;
    private JFrame gameWindow;
    
    public WindowsManager() {
                      
    }
    
    public void setWindows(JFrame fr1, JFrame fr2) {
        this.serverWindow = fr1;
        this.gameWindow = fr2; 
        fr1.setVisible(true);
        fr2.setVisible(false);
    }
    
    public void switchWindows() {
        if (serverWindow.isVisible()) {
            serverWindow.setVisible(false);
            gameWindow.setVisible(true);
        } else {
            serverWindow.setVisible(true);
            gameWindow.setVisible(false);
        }
    }
    
    public JFrame getFocusWindow() {
        if (serverWindow.isVisible()) {
            return serverWindow;
        } else {
            return gameWindow;
        }
    }
    
    public GameWindow getGameWindow() {
        return (GameWindow)gameWindow;
    }
}
