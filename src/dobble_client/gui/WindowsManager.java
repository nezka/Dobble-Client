package dobble_client.gui;




import javax.swing.JFrame;


public class WindowsManager {
    private JFrame fr1;
    private JFrame fr2;
    
    public WindowsManager() {
                      
    }
    
    public void setWindows(JFrame fr1, JFrame fr2) {
        this.fr1 = fr1;
        this.fr2 = fr2;                
    }
    
    public void switchWindows() {
        if (fr1.isVisible()) {
            fr1.setVisible(false);
            fr2.setVisible(true);
        } else {
            fr1.setVisible(true);
            fr2.setVisible(false);
        }
    }
    
    public ServerWindow getServerWindow() {
        return (ServerWindow)fr1;
    }
}
