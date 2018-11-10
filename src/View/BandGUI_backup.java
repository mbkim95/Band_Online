///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package View;
//
//import java.awt.Image;
//import java.awt.Toolkit;
//
//import javax.swing.ImageIcon;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.SimpleAttributeSet;
//import javax.swing.text.StyleConstants;
//import javax.swing.text.StyledDocument;
//
//import Controller.LobbyClient;
//import Controller.RoomChatClient;
//import Controller.RoomClient;
//
///**
// *
// * @author K
// */
//public class BandGUI_backup extends javax.swing.JFrame {
//
//    /**
//     * Creates new form InGameGUI
//     */
//	private RoomClient room;
//	private RoomChatClient chat;
//	private LobbyClient l_client;
//	private String nickname;
//	private LobbyGUI lobby;
//	private String title;
//	private int select;
//	private int port;
//	private PlayGUI play;
//	
//    public BandGUI_backup() {
//        initComponents();
//    }
//    
//    public BandGUI_backup(LobbyGUI lobby) {
//    	this.lobby = lobby;
//    	initComponents();
//    }
//    
//    public BandGUI_backup(String ip, LobbyGUI lobby, LobbyClient l_client, int port, String title, String nickname, int select) {
//    	this.lobby = lobby;
//    	this.l_client = l_client;
//    	this.port = port;
//    	this.title = title;
//    	this.nickname = nickname;
//    	this.select = select;
//    	initComponents();
//    	changeImage(select);
//    	room = new RoomClient(port);
//    	room.connect(ip, this, nickname);
//    	chat = new RoomChatClient(port);
//    	chat.connect(ip, this, nickname);
//    	room.sendMessage("2 "+select);
//    	play = new PlayGUI(room, select);
//    	play.open();
//    	play.setVisible(false);
//    	setTitle(title);
//    }
//    
//    public void changeImage(int img) {
//    	Image i;
//    	ImageIcon icon;
//    	switch(img) {
//    	case 0:
//    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar1.png");
//    		icon = new ImageIcon(i);  //이미지 넣기
//    		guitar1.setIcon(icon); // NOI18N
//    		break;
//    	case 1:
//    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar2.png");
//    		icon = new ImageIcon(i);  //이미지 넣기
//    		guitar2.setIcon(icon); // NOI18N
//    		break;
//    	case 2:
//    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/bass.png");
//    		icon = new ImageIcon(i);  //이미지 넣기
//    		bass.setIcon(icon); // NOI18N
//    		break;
//    	case 3:
//    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/keyboard.png");
//    		icon = new ImageIcon(i);  //이미지 넣기
//    		keyboard.setIcon(icon); // NOI18N
//    		break;
//    	case 4:
//    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/drum.png");
//    		icon = new ImageIcon(i);  //이미지 넣기
//    		drum.setIcon(icon); // NOI18N
//    		break;
//    	case 5:
//    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar1_black.png");
//    		icon = new ImageIcon(i);  //이미지 넣기
//    		guitar1.setIcon(icon); // NOI18N
//    		break;
//    	case 6:
//    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar2_black.png");
//    		icon = new ImageIcon(i);  //이미지 넣기
//    		guitar2.setIcon(icon); // NOI18N
//    		break;
//    	case 7:
//    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/bass_black.png");
//    		icon = new ImageIcon(i);  //이미지 넣기
//    		bass.setIcon(icon); // NOI18N
//    		break;
//    	case 8:
//    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/keyboard_black.png");
//    		icon = new ImageIcon(i);  //이미지 넣기
//    		keyboard.setIcon(icon); // NOI18N
//    		break;
//    	case 9:
//    		i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/drum_black.png");
//    		icon = new ImageIcon(i);  //이미지 넣기
//    		drum.setIcon(icon); // NOI18N
//    		break;
//    	}
//    }
//    
//    public void appendMsg(String msg) {
//    	try {
//    		StyledDocument document = (StyledDocument) chatPane.getDocument();    		
//    		document.insertString(document.getLength(), msg, null);        	
//    		chatPanel.getVerticalScrollBar().setValue(chatPanel.getVerticalScrollBar().getMaximum());		// 채팅창 자동 스크롤
//    	} catch (BadLocationException e) {
//    		e.printStackTrace();
//    	}
//    }
//    
//    public void appendSystemMsg(String msg) {
//    	try {
//    		StyledDocument document = (StyledDocument) chatPane.getDocument();
//    		SimpleAttributeSet styleSet = new SimpleAttributeSet();
//    		StyleConstants.setForeground(styleSet, new java.awt.Color(0, 204, 51));
//    		StyleConstants.setItalic(styleSet, true);
//        	document.insertString(document.getLength(), msg, styleSet);    		
//    		chatPanel.getVerticalScrollBar().setValue(chatPanel.getVerticalScrollBar().getMaximum());		// 채팅창 자동 스크롤
//    	} catch (BadLocationException e) {
//    		e.printStackTrace();
//    	}
//    }
//    
//    public void clearUserList() {
//    	player_list.setText("");    	
//    }
//    
//    public void appendUserList(String nickname) {    	
//    	player_list.append(nickname + "\n");
//    }
//    
//    public void deleteRoom() {
//    	System.out.println("delete room!!!");
//    	System.out.println(title);
//    	lobby.removeRoom(title);
//    	//dispose();
//    	//lobby.setVisible(true);
//    }
//
//    /**
//     * This method is called from within the constructor to initialize the form.
//     * WARNING: Do NOT modify this code. The content of this method is always
//     * regenerated by the Form Editor.
//     */
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
//    private void initComponents() {
//
//        chatPanel = new javax.swing.JScrollPane();
//        chatPane = new javax.swing.JTextPane();
//        chatField = new javax.swing.JTextField();
//        playerlistPanel = new javax.swing.JScrollPane();
//        player_list = new javax.swing.JTextArea();
//        exit_btn = new javax.swing.JButton();
//        play_btn = new javax.swing.JButton();
//        spotlight = new javax.swing.JLabel();
//        guitar2 = new javax.swing.JLabel();
//        drum = new javax.swing.JLabel();
//        bass = new javax.swing.JLabel();
//        keyboard = new javax.swing.JLabel();
//        guitar1 = new javax.swing.JLabel();
//        band_bg = new javax.swing.JLabel();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
//        setTitle("합주실");
//        setLocation(new java.awt.Point(500, 200));
//        setResizable(false);
//
//        chatPane.setEditable(false);
//        chatPane.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
//        chatPane.setForeground(new java.awt.Color(102, 102, 102));
//        chatPanel.setViewportView(chatPane);
//
//        chatField.setBackground(new java.awt.Color(231, 230, 230));
//        chatField.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
//        chatField.setForeground(new java.awt.Color(102, 102, 102));
//        chatField.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                chatFieldActionPerformed(evt);
//            }
//        });
//
//        playerlistPanel.setHorizontalScrollBar(null);
//
//        player_list.setEditable(false);
//        player_list.setBackground(new java.awt.Color(231, 230, 230));
//        player_list.setColumns(20);
//        player_list.setFont(new java.awt.Font("맑은 고딕", 0, 15)); // NOI18N
//        player_list.setForeground(new java.awt.Color(102, 102, 102));
//        player_list.setRows(5);
//        playerlistPanel.setViewportView(player_list);
//        
//
//        Image i;
//        ImageIcon icon;
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/exit_btn.png");
//        icon = new ImageIcon(i);
//        exit_btn.setIcon(icon); // NOI18N
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/exit_clicked.png");
//        icon = new ImageIcon(i);
//        exit_btn.setPressedIcon(icon); // NOI18N
//        exit_btn.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                exit_btnActionPerformed(evt);
//            }
//        });
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/play_btn.png");
//        icon = new ImageIcon(i);
//        play_btn.setIcon(icon); // NOI18N
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/play_clicked.png");
//        icon = new ImageIcon(i);
//        play_btn.setPressedIcon(icon); // NOI18N
//        play_btn.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                play_btnActionPerformed(evt);
//            }
//        });
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/spotlight.png");
//        icon = new ImageIcon(i);
//        spotlight.setIcon(icon); // NOI18N
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar2_black.png");
//        icon = new ImageIcon(i);
//        guitar2.setIcon(icon); // NOI18N
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/drum_black.png");
//        icon = new ImageIcon(i);
//        drum.setIcon(icon); // NOI18N
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/bass_black.png");
//        icon = new ImageIcon(i);
//        bass.setIcon(icon); // NOI18N
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/keyboard_black.png");
//        icon = new ImageIcon(i);
//        keyboard.setIcon(icon); // NOI18N
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/guitar1_black.png");
//        icon = new ImageIcon(i);
//        guitar1.setIcon(icon); // NOI18N
//        i = Toolkit.getDefaultToolkit().getImage("rsc/images/band/band_bg.png");
//        icon = new ImageIcon(i);
//        band_bg.setIcon(icon) ;// NOI18N
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(36, 36, 36)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(128, 128, 128)
//                        .addComponent(drum))
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(266, 266, 266)
//                        .addComponent(bass))
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(258, 258, 258)
//                        .addComponent(keyboard))
//                    .addComponent(spotlight))
//                .addGap(23, 23, 23)
//                .addComponent(playerlistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(28, 28, 28)
//                .addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(368, 368, 368)
//                .addComponent(guitar1))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(188, 188, 188)
//                .addComponent(guitar2))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(658, 658, 658)
//                .addComponent(play_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(28, 28, 28)
//                .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(658, 658, 658)
//                .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
//            .addComponent(band_bg)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(34, 34, 34)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(99, 99, 99)
//                        .addComponent(drum)
//                        .addGap(7, 7, 7)
//                        .addComponent(bass))
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(156, 156, 156)
//                        .addComponent(keyboard))
//                    .addComponent(spotlight)
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(156, 156, 156)
//                        .addComponent(playerlistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                .addGap(197, 197, 197)
//                .addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(178, 178, 178)
//                .addComponent(guitar1))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(234, 234, 234)
//                .addComponent(guitar2))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(75, 75, 75)
//                .addComponent(play_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(413, 413, 413)
//                .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
//            .addGroup(layout.createSequentialGroup()
//                .addGap(352, 352, 352)
//                .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
//            .addComponent(band_bg, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
//        );
//
//        pack();
//    }// </editor-fold>                        
//
//    private void chatFieldActionPerformed(java.awt.event.ActionEvent evt) {                                          
//        // TODO add your handling code here:
//    	String msg = "2 [" + nickname + "] : " + chatField.getText() + "\n";
//    	chat.sendMessage(msg);
//    	chatField.setText("");
//    }                                         
//
//    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
//        // TODO add your handling code here:
//    	chat.sendMessage("1 " + nickname);
//    	room.sendMessage("3 " + nickname + "###" + title + "***" + select);
////    	chat = null;
////    	room = null;
//    	lobby.setVisible(true);
//    	play.dispose();
//    	dispose();
//    }                                        
//
//    private void play_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
//        // TODO add your handling code here:
//    	play.setVisible(true);
//    }                                        
//
//    /**
//     * @param args the command line arguments
//     */
//    public void open() {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(BandGUI_backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BandGUI_backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BandGUI_backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BandGUI_backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        setVisible(true);
//        /* Create and display the form */
//        /*java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BandGUI().setVisible(true);
//            }
//        });*/
//    }
//
//    // Variables declaration - do not modify                     
//    private javax.swing.JLabel band_bg;
//    private javax.swing.JLabel bass;
//    private javax.swing.JTextField chatField;
//    private javax.swing.JTextPane chatPane;
//    private javax.swing.JScrollPane chatPanel;
//    private javax.swing.JLabel drum;
//    private javax.swing.JButton exit_btn;
//    private javax.swing.JLabel guitar1;
//    private javax.swing.JLabel guitar2;
//    private javax.swing.JLabel keyboard;
//    private javax.swing.JButton play_btn;
//    private javax.swing.JTextArea player_list;
//    private javax.swing.JScrollPane playerlistPanel;
//    private javax.swing.JLabel spotlight;
//    // End of variables declaration                   
//}
