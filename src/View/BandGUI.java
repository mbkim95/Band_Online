/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Controller.DB_Controller;
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
	private DB_Controller db_cont;
	private String nickname;
	private LobbyGUI lobby;
	private String title;
	private int select;
	private int port;
	private PlayGUI play;
	private boolean check = false;
	
    public BandGUI() {
        initComponents();
    }
    
    public BandGUI(LobbyGUI lobby) {
    	this.lobby = lobby;
    	initComponents();
    }
    
    public BandGUI(String ip, LobbyGUI lobby, LobbyClient l_client, DB_Controller db_cont, int port, String title, String nickname) {
    	initComponents();
    	this.lobby = lobby;
    	this.l_client = l_client;
    	this.db_cont = db_cont;
    	this.port = port;
    	this.title = title;
    	this.nickname = nickname;
    	room = new RoomClient(port);
    	room.connect(ip, this, nickname);
    	chat = new RoomChatClient(port);
    	chat.connect(ip, this, nickname);
//    	room.sendMessage("2 "+select);
    	play = new PlayGUI(room);
    	play.open();
    	play.setVisible(false);
    	setTitle(title);
    	setNotificationBtn();
    }
    
    public void setNotificationBtn() {
    	int cnt = db_cont.getMail(nickname);
    	if(cnt != 0) {
    		Image i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/notification_btn2.png");
    		ImageIcon icon = new ImageIcon(i);
    		notification_btn.setIcon(icon);
    	}else {
    		Image i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/notification_btn.png");
    		ImageIcon icon = new ImageIcon(i);
    		notification_btn.setIcon(icon);
    	}
    }
    
    public void setCheck(boolean check) {
    	this.check = check;
    }
    
    public void setSelect(int select) {
    	if(check) {
    		room.sendMessage("5 "+ this.select + "###" + select);
    		chat.sendMessage("4 " + nickname + "###" + this.select + "***" + select);
    	}else {
    		room.sendMessage("2 "+select);
    		chat.sendMessage("3 " + nickname + "###" + select);
    	}
    	this.select = select;
    	play.setSelect(select);
    }
    
    public void changeImage(int img) {
    	Image i;
    	ImageIcon icon;
    	switch(img) {
    	case 0:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar1.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		guitar1.setIcon(icon); // NOI18N
    		break;
    	case 1:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar2.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		guitar2.setIcon(icon); // NOI18N
    		break;
    	case 2:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/bass.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		bass.setIcon(icon); // NOI18N
    		break;
    	case 3:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/keyboard.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		keyboard.setIcon(icon); // NOI18N
    		break;
    	case 4:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/drum.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		drum.setIcon(icon); // NOI18N
    		break;
    	case 5:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar1_black.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		guitar1.setIcon(icon); // NOI18N
    		break;
    	case 6:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar2_black.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		guitar2.setIcon(icon); // NOI18N
    		break;
    	case 7:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/bass_black.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		bass.setIcon(icon); // NOI18N
    		break;
    	case 8:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/keyboard_black.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		keyboard.setIcon(icon); // NOI18N
    		break;
    	case 9:
    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/drum_black.png");
    		icon = new ImageIcon(i);  //이미지 넣기
    		drum.setIcon(icon); // NOI18N
    		break;
    	}
    }
    
    public void appendMsg(String msg) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				StyledDocument document = (StyledDocument) chatPane.getDocument();    		
    				document.insertString(document.getLength(), msg, null);        	
    			} catch (BadLocationException e) {
    				
    			}
    			try {
    				chatPanel.getVerticalScrollBar().setValue(chatPanel.getVerticalScrollBar().getMaximum());		// 채팅창 자동 스크롤
    			}catch(ArrayIndexOutOfBoundsException | NullPointerException e) {
    				
    			}    			
    		}
    	});
    }
    
    public void appendSystemMsg(String msg) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				StyledDocument document = (StyledDocument) chatPane.getDocument();
    				SimpleAttributeSet styleSet = new SimpleAttributeSet();
    				StyleConstants.setForeground(styleSet, new java.awt.Color(0, 204, 51));
    				StyleConstants.setItalic(styleSet, true);
    				document.insertString(document.getLength(), msg, styleSet);    		
    			} catch (BadLocationException e) {
    				e.printStackTrace();
    			}
    			try {
    				chatPanel.getVerticalScrollBar().setValue(chatPanel.getVerticalScrollBar().getMaximum());		// 채팅창 자동 스크롤
    			}catch(ArrayIndexOutOfBoundsException | NullPointerException e) {
    				
    			}    			
    		}
    	});
    }
    
    public void clearUserList() {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			String str[] = {""};
    			player_list.setListData(str);    	    			
    		}
    	});
    }
    
    public void appendUserList(String nickname) {    	    	
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			ListModel<String> list = player_list.getModel();
    			DefaultListModel<String> dList = new DefaultListModel<String>();
    			for(int i=0; i<list.getSize(); i++) {
    				dList.addElement(list.getElementAt(i));
    			}
    			dList.addElement(nickname);    	
    			player_list.setModel(dList);    			
    		}
    	});
    }
    
    public void deleteRoom() {
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

        jLabel9 = new javax.swing.JLabel();
        chatPanel = new javax.swing.JScrollPane();
        chatPane = new javax.swing.JTextPane();
        chatField = new javax.swing.JTextField();
        playerlistPanel = new javax.swing.JScrollPane();
        player_list = new javax.swing.JList<>();
        user_btn = new javax.swing.JButton();
        friend_btn = new javax.swing.JButton();
        notification_btn = new javax.swing.JButton();
        exit_btn = new javax.swing.JButton();
        play_btn = new javax.swing.JButton();
        select_btn = new javax.swing.JButton();
        spotlight = new javax.swing.JLabel();
        guitar1 = new javax.swing.JLabel();
        guitar2 = new javax.swing.JLabel();
        bass = new javax.swing.JLabel();
        drum = new javax.swing.JLabel();
        keyboard = new javax.swing.JLabel();
        band_bg = new javax.swing.JLabel();

        jLabel9.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jLabel9.setText("낮");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("합주실");
        setLocation(new java.awt.Point(500, 200));
        setResizable(false);

        chatPane.setEditable(false);
        chatPane.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        chatPane.setForeground(new java.awt.Color(102, 102, 102));
        chatPanel.setViewportView(chatPane);

        chatField.setBackground(new java.awt.Color(231, 230, 230));
        chatField.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        chatField.setForeground(new java.awt.Color(102, 102, 102));
        chatField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatFieldActionPerformed(evt);
            }
        });

        player_list.setBackground(new java.awt.Color(231, 230, 230));
        player_list.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        player_list.setForeground(new java.awt.Color(102, 102, 102));
        player_list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        player_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        player_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player_listMouseClicked(evt);
            }
        });
        playerlistPanel.setViewportView(player_list);

        Image i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/user_btn.png");
        ImageIcon icon = new ImageIcon(i);
        user_btn.setIcon(icon); // NOI18N
        user_btn.setContentAreaFilled(false);
        user_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_btnActionPerformed(evt);
            }
        });
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/friends_btn.png");
        icon = new ImageIcon(i);
        friend_btn.setIcon(icon); // NOI18N
        friend_btn.setContentAreaFilled(false);
        friend_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friend_btnActionPerformed(evt);
            }
        });
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/notification_btn.png");
        icon = new ImageIcon(i);
        notification_btn.setIcon(icon); // NOI18N
        notification_btn.setContentAreaFilled(false);
        notification_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notification_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/exit_btn.png");
        icon = new ImageIcon(i);
        exit_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/exit_clicked.png");
        icon = new ImageIcon(i);
        exit_btn.setPressedIcon(icon); // NOI18N
        exit_btn.setContentAreaFilled(false);
        exit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/play_btn.png");
        icon = new ImageIcon(i);
        play_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/play_clicked.png");
        icon = new ImageIcon(i);
        play_btn.setPressedIcon(icon); // NOI18N
        play_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/select_btn.png");
        icon = new ImageIcon(i);
        select_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/select_clicked.png");
        icon = new ImageIcon(i);
        select_btn.setPressedIcon(icon); // NOI18N
        select_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/spotlight.png");
        icon = new ImageIcon(i);
        spotlight.setIcon(icon); // NOI18N

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar1_black.png");
        icon = new ImageIcon(i);
        guitar1.setIcon(icon); // NOI18N

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar2_black.png");
        icon = new ImageIcon(i);
        guitar2.setIcon(icon); // NOI18N

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/bass_black.png");
        icon = new ImageIcon(i);
        bass.setIcon(icon); // NOI18N

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/drum_black.png");
        icon = new ImageIcon(i);
        drum.setIcon(icon); // NOI18N

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/keyboard_black.png");
        icon = new ImageIcon(i);
        keyboard.setIcon(icon); // NOI18N

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/band_bg.png");
        icon = new ImageIcon(i);
        band_bg.setIcon(icon) ;// NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(guitar1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(drum))
                    .addComponent(spotlight)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(bass))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(guitar2)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(user_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(friend_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(notification_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(play_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(select_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(playerlistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(keyboard))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(band_bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(guitar1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(drum))
                    .addComponent(spotlight)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(bass))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(guitar2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(user_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(friend_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(notification_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(play_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76)
                        .addComponent(playerlistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(keyboard))
            .addGroup(layout.createSequentialGroup()
                .addGap(573, 573, 573)
                .addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(band_bg, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        

    private void chatFieldActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    	if(!chatField.getText().equals("")) {
    		String msg = "2 [" + nickname + "] : " + chatField.getText() + "\n";
    		chat.sendMessage(msg);
    		chatField.setText("");
    	}
    }                                         

    private void play_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	if(check)
    		play.setVisible(true);
    }                                        

    private void select_btnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	SelectGUI select = new SelectGUI(this);
    	select.open();
    }                                          

    private void user_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	UserGUI userStatus = new UserGUI(db_cont, lobby, nickname);
    	userStatus.open();
    }                                        

    private void friend_btnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	FriendsGUI friends = new FriendsGUI(db_cont, l_client, nickname);
    	friends.open();
    }                                          

    private void notification_btnActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    	MsgBoxGUI msgBox = new MsgBoxGUI(l_client, db_cont, lobby, this, nickname, false);
    	msgBox.open();
    }                                                

    private void player_listMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
    	String receiver = player_list.getSelectedValue();
    	if((receiver != null) && (!receiver.equals(nickname))) {
    		SendMsgGUI sendMsg = new SendMsgGUI(db_cont, l_client, receiver, nickname, true);
    		sendMsg.open();
    	}
    }                                        

    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	// TODO add your handling code here:
    	chat.sendMessage("1 " + nickname);
    	room.sendMessage("3 " + nickname + "###" + title + "***" + select);
//    	chat = null;
//    	room = null;
    	lobby.setVisible(true);
    	play.dispose();
    	dispose();
    }                                        

    /**
     * @param args the command line arguments
     */
    public void open() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        setVisible(true);
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BandGUI().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel band_bg;
    private javax.swing.JLabel bass;
    private javax.swing.JTextField chatField;
    private javax.swing.JTextPane chatPane;
    private javax.swing.JScrollPane chatPanel;
    private javax.swing.JLabel drum;
    private javax.swing.JButton exit_btn;
    private javax.swing.JButton friend_btn;
    private javax.swing.JLabel guitar1;
    private javax.swing.JLabel guitar2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel keyboard;
    private javax.swing.JButton notification_btn;
    private javax.swing.JButton play_btn;
    private javax.swing.JList<String> player_list;
    private javax.swing.JScrollPane playerlistPanel;
    private javax.swing.JButton select_btn;
    private javax.swing.JLabel spotlight;
    private javax.swing.JButton user_btn;
    // End of variables declaration                   
}
