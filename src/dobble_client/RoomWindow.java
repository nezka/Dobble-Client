
package dobble_client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class RoomWindow extends JFrame{
    
    private final int ROOM_COUNT;
    private final int ROOMS_PER_ROW = 5;
    
    RoomWindow(int room_count) {
        this.ROOM_COUNT = room_count;
	this.setTitle("The Dobble Game");
	this.setSize(1000, 700);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        this.setResizable(false);
	this.setLayout(new BorderLayout());
        this.add(createControlPanel(), BorderLayout.SOUTH);
        this.add(createMainPanel(), BorderLayout.EAST);
	this.setVisible(true);
    }
    
    JPanel createMainPanel() {
        JPanel mainPN = new JPanel();
        mainPN.setLayout(new BorderLayout());
        mainPN.add(createGameRooms(), BorderLayout.CENTER);
        //mainPN.add(createControlPanel(), BorderLayout.SOUTH);
        
        return mainPN;
        
    }
    
    JPanel createGameRooms() {
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
        return roomsPN;
    }
    
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
