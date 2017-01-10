
package dobble_client.gui;

import dobble_client.network.Network;
import dobble_client.game.Symbol;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public final class RoomWindow extends JFrame{
    
    private Network nw;
    private WindowsManager wm;
    
    public RoomWindow(Network nw, WindowsManager wm) {
        this.nw = nw;
        this.wm = wm;
        setUpFrame();
    }
    
    private void setUpFrame() {
       // System.out.println("akt sl: "+System.getProperty("user.dir"));
        this.setTitle("The Dobble Game");
	this.setSize(1000, 700);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
        this.setResizable(false);
	this.setLayout(new BorderLayout());
        JPanel pn = new JPanel();
        /*Icon icon = new Symbol("yuri.jpg");
        JLabel lb = new JLabel(icon);
        lb.setHorizontalTextPosition(SwingConstants.LEFT);
        lb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("image clicked");
            }
        });
        pn.add(lb);*/
       
        this.add(pn, BorderLayout.CENTER);
        //this.add(createControlPanel(), BorderLayout.SOUTH);
        //this.add(createMainPanel(), BorderLayout.CENTER);
        this.addOnCloseAction();
       
        
	this.setVisible(false);
    }
    
    JPanel createMainPanel() {
        JPanel mainPN = new JPanel();
        mainPN.setLayout(new BorderLayout());
       /* mainPN.add(createGameRooms(), BorderLayout.CENTER);*/
        mainPN.add(createControlPanel(), BorderLayout.CENTER);
        
        return mainPN;
        
    }
    
    void addOnCloseAction() {
         this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            int answer = JOptionPane.showConfirmDialog(null, 
                "Closing this window will exit game.\nDo you want to close it?", "Exit game", 
                 JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
        });
    }
    
    /*JPanel createGameRooms() {/*
        int rowCount = ROOM_COUNT / ROOMS_PER_ROW;
        if (ROOM_COUNT % ROOMS_PER_ROW != 0) {
            rowCount++;
        }
        JPanel roomsPN = new JPanel();
        roomsPN.setPreferredSize(new Dimension(ROOMS_PER_ROW * 150, rowCount * 20));
        roomsPN.setLayout(new GridLayout(rowCount, ROOMS_PER_ROW, 20, 20));
        for (int i = 1; i <= ROOM_COUNT; i++) {
            roomsPN.add(createRoomButton(i));
        }
        JPanel roomsPN = new JPanel();
        roomsPN.add()
        return roomsPN;*/
    
    
    JButton createRoomButton(int index) {
        JButton cur = new JButton("Room #" + index);
        //cur.setPreferredSize(new Dimension(10,10));
        //cur.setMaximumSize(new Dimension(10,10));
        return cur;
    }
    
    JPanel createControlPanel() {
        JPanel controlPN = new JPanel();
        controlPN.add(new JButton("TIMEOUT"));
        controlPN.add(new JButton("DISCONNECT"));
        return controlPN;
    }
    
    
}
