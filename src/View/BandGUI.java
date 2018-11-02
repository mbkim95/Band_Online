/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Controller.LobbyClient;
import Controller.RoomChatClient;
import Controller.RoomClient;

/**
 *
 * @author K
 */
public class BandGUI extends javax.swing.JFrame {

    /**
     * Creates new form InGameGUI
     */
	private RoomClient room;
	private RoomChatClient chat;
	private LobbyClient l_client;
	private String nickname;
	private LobbyGUI lobby;
	private String title;
	private int select;
	private int port;
	
    public BandGUI() {
        initComponents();
    }
    
    public BandGUI(LobbyGUI lobby) {
    	this.lobby = lobby;
    	initComponents();
    }
    
    public BandGUI(LobbyGUI lobby, LobbyClient l_client, int port, String title, String nickname, int select) {
    	this.lobby = lobby;
    	this.l_client = l_client;
    	this.port = port;
    	this.title = title;
    	this.nickname = nickname;
    	this.select = select;
    	initComponents();
    	changeImage(select);
    	room = new RoomClient(port);
    	room.connect(this, nickname);
    	chat = new RoomChatClient(port);
    	chat.connect(this, nickname);
    	room.sendMessage("2 "+select);
    }
    
    public void changeImage(int img) {
    	Image i;
    	ImageIcon icon;
    	switch(img) {
    	case 0:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/guitar1.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		guitar1.setIcon(icon); // NOI18N
    		break;
    	case 1:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/guitar2.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		guitar2.setIcon(icon); // NOI18N
    		break;
    	case 2:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/bass.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		bass.setIcon(icon); // NOI18N
    		break;
    	case 3:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/keyboard.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		keyboard.setIcon(icon); // NOI18N
    		break;
    	case 4:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/drum.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		drum.setIcon(icon); // NOI18N
    		break;
    	case 5:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/guitar1_black.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		guitar1.setIcon(icon); // NOI18N
    		break;
    	case 6:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/guitar2_black.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		guitar2.setIcon(icon); // NOI18N
    		break;
    	case 7:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/bass_black.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		bass.setIcon(icon); // NOI18N
    		break;
    	case 8:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/keyboard_black.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		keyboard.setIcon(icon); // NOI18N
    		break;
    	case 9:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/drum_black.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		drum.setIcon(icon); // NOI18N
    		break;
    	}
    }
    
    public void appendMsg(String msg) {
    	try {
    		StyledDocument document = (StyledDocument) chatPane.getDocument();    		
    		document.insertString(document.getLength(), msg, null);        	
    		chatPanel.getVerticalScrollBar().setValue(chatPanel.getVerticalScrollBar().getMaximum());		// 채팅창 자동 스크롤
    	} catch (BadLocationException e) {
    		e.printStackTrace();
    	}
    }
    
    public void appendSystemMsg(String msg) {
    	try {
    		StyledDocument document = (StyledDocument) chatPane.getDocument();
    		SimpleAttributeSet styleSet = new SimpleAttributeSet();
    		StyleConstants.setForeground(styleSet, new java.awt.Color(0, 204, 51));
    		StyleConstants.setItalic(styleSet, true);
        	document.insertString(document.getLength(), msg, styleSet);    		
    		chatPanel.getVerticalScrollBar().setValue(chatPanel.getVerticalScrollBar().getMaximum());		// 채팅창 자동 스크롤
    	} catch (BadLocationException e) {
    		e.printStackTrace();
    	}
    }
    
    public void clearUserList() {
    	player_list.setText("");    	
    }
    
    public void appendUserList(String nickname) {    	
    	player_list.append(nickname + "\n");
    }
    
    public void deleteRoom() {
    	System.out.println("delete room!!!");
    	System.out.println(title);
    	lobby.removeRoom(title);
    	//dispose();
    	//lobby.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        chatPanel = new javax.swing.JScrollPane();
        chatPane = new javax.swing.JTextPane();
        chatField = new javax.swing.JTextField();
        playerlistPanel = new javax.swing.JScrollPane();
        player_list = new javax.swing.JTextArea();
        exit_btn = new javax.swing.JButton();
        list = new javax.swing.JLabel();
        instruments_Panel = new javax.swing.JPanel();
        guitar1 = new javax.swing.JLabel();
        bass = new javax.swing.JLabel();
        drum = new javax.swing.JLabel();
        guitar2 = new javax.swing.JLabel();
        keyboard = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(title);
        setLocation(new java.awt.Point(600, 300));
        setResizable(false);

        chatPane.setEditable(false);
        chatPanel.setViewportView(chatPane);

        chatField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatFieldActionPerformed(evt);
            }
        });

        playerlistPanel.setHorizontalScrollBar(null);

        player_list.setEditable(false);
        player_list.setColumns(20);
        player_list.setRows(5);
        playerlistPanel.setViewportView(player_list);

        exit_btn.setText("나가기");
        exit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_btnActionPerformed(evt);
            }
        });

        list.setText("연주자 목록");
        
        Image i = Toolkit.getDefaultToolkit().getImage("rsc/images/guitar1_black.png");     //크기와 넓이에 맞게 이미지 확장        
        ImageIcon icon = new ImageIcon(i);  //이미지 넣기
        guitar1.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/bass_black.png");     //크기와 넓이에 맞게 이미지 확장
        icon = new ImageIcon(i);
        bass.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/drum_black.png");     //크기와 넓이에 맞게 이미지 확장
        icon = new ImageIcon(i);
        drum.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/guitar2_black.png");     //크기와 넓이에 맞게 이미지 확장
        icon = new ImageIcon(i);
        guitar2.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/keyboard_black.png");     //크기와 넓이에 맞게 이미지 확장
        icon = new ImageIcon(i);
        keyboard.setIcon(icon); // NOI18N

        javax.swing.GroupLayout instruments_PanelLayout = new javax.swing.GroupLayout(instruments_Panel);
        instruments_Panel.setLayout(instruments_PanelLayout);
        instruments_PanelLayout.setHorizontalGroup(
            instruments_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instruments_PanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(guitar1)
                .addGap(18, 18, 18)
                .addComponent(bass)
                .addGroup(instruments_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(instruments_PanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(drum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(instruments_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(keyboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(guitar2)
                .addGap(30, 30, 30))
        );
        instruments_PanelLayout.setVerticalGroup(
            instruments_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instruments_PanelLayout.createSequentialGroup()
                .addGroup(instruments_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(instruments_PanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(guitar2))
                    .addGroup(instruments_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(guitar1)
                        .addGroup(instruments_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(instruments_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(drum)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(keyboard))
                            .addGroup(instruments_PanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(bass)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chatField)
                    .addComponent(chatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addComponent(instruments_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(playerlistPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(list)
                                .addGap(42, 42, 42))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(list)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerlistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit_btn)
                .addGap(64, 64, 64))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instruments_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void chatFieldActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    	String msg = "2 [" + nickname + "] : " + chatField.getText() + "\n";
    	chat.sendMessage(msg);
    	chatField.setText("");
    }                                         

    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	chat.sendMessage("1 " + nickname);
    	room.sendMessage("3 " + nickname + "###" + title + "***" + select);
//    	chat = null;
//    	room = null;
    	lobby.setVisible(true);
    	dispose();
    }                                        

    /**
     * @param args the command line arguments
     */
    public void start(){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        setVisible(true);
     /*      
         //Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BandGUI().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel bass;
    private javax.swing.JTextField chatField;
    private javax.swing.JTextPane chatPane;
    private javax.swing.JScrollPane chatPanel;
    private javax.swing.JLabel drum;
    private javax.swing.JButton exit_btn;
    private javax.swing.JLabel guitar1;
    private javax.swing.JLabel guitar2;
    private javax.swing.JPanel instruments_Panel;
    private javax.swing.JLabel keyboard;
    private javax.swing.JLabel list;
    private javax.swing.JTextArea player_list;
    private javax.swing.JScrollPane playerlistPanel;
    // End of variables declaration                   
}
