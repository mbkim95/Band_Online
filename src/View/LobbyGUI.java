/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.ListModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Controller.LobbyChatClient;
import Controller.LobbyClient;

/**
 *
 * @author K
 */
public class LobbyGUI extends javax.swing.JFrame {

    /**
     * Creates new form LobbyGUI
     */
	private static String NICKNAME;
	private LobbyChatClient chat;
	private LobbyClient lobby;
	private ArrayList<String> roomList = new ArrayList<String>();		// 게임방 목록 저장 배열
	private int idx = 0;												// 게임방 목록을 위한 인덱스
	private BandGUI band;
	private String ip;
	private RecvMsgGUI recvMsg;
	
    public LobbyGUI() {
        initComponents();
    }
    
    public LobbyGUI(String ip, String nickname) {
    	this.ip = ip;
    	NICKNAME = nickname;    	
    	initComponents();
    	chat = new LobbyChatClient();
    	chat.connect(ip, this, NICKNAME);
    	lobby = new LobbyClient();
    	lobby.connect(ip, this, NICKNAME);
    	id.setText(NICKNAME);
    }
    
    public void receiveLetter(String nickname) {
    	recvMsg = new RecvMsgGUI(lobby, nickname, NICKNAME);
    	recvMsg.open();
    	recvMsg.setVisible(false);
    }
    
    public void appendLetter(String msg) {
    	recvMsg.appendLetter(msg);
    }
    
    public void showMessage() {
    	recvMsg.setVisible(true);
    }
    
    public void appendMsg(String msg) {
    	try {
    		StyledDocument document = (StyledDocument) textPane.getDocument();    		
    		document.insertString(document.getLength(), msg, null);        	
    		chatScroll.getVerticalScrollBar().setValue(chatScroll.getVerticalScrollBar().getMaximum());		// 채팅창 자동 스크롤
    	} catch (BadLocationException e) {
    		e.printStackTrace();
    	}
    }
    
    public void appendSystemMsg(String msg) {
    	try {
    		StyledDocument document = (StyledDocument) textPane.getDocument();
    		SimpleAttributeSet styleSet = new SimpleAttributeSet();
    		StyleConstants.setForeground(styleSet, new java.awt.Color(0, 204, 51));
    		StyleConstants.setItalic(styleSet, true);
        	document.insertString(document.getLength(), msg, styleSet);    		
    		chatScroll.getVerticalScrollBar().setValue(chatScroll.getVerticalScrollBar().getMaximum());		// 채팅창 자동 스크롤
    	} catch (BadLocationException e) {
    		e.printStackTrace();
    	}
    }
    
    public void clearUserList() {
    	String str[] = {""};
    	userList.setListData(str);
//    	userList.setText("");    	
    }
    
    public void appendUserList(String nickname) {    	
    	ListModel<String> list = userList.getModel();
    	DefaultListModel<String> dList = new DefaultListModel<String>();
    	
    	for(int i=0; i<list.getSize(); i++) {
    		dList.addElement(list.getElementAt(i));
    	}
    	dList.addElement(nickname);    	
    	userList.setModel(dList);
    }    
    
    public void clearGameRoom() {
    	roomList.clear();
    	clearRoomList();    	
    }
    
    public void addGameRoom(String room) {
    	roomList.add(room);
    	int n = roomList.size();
    	if(n == 1) {
    		title1.setText(roomList.get(0));
    		enter_btn1.setEnabled(true);
    	}else if(n == 2) {
    		title2.setText(roomList.get(1));
    		enter_btn2.setEnabled(true);
    	}else if(n == 3) {
    		title3.setText(roomList.get(2));
    		enter_btn3.setEnabled(true);
    	}else if(n == 4) {
    		title4.setText(roomList.get(3));
    		enter_btn4.setEnabled(true);
    	}else if(n > 4){
    		next_btn.setEnabled(true);
    	}
    }
    
    public void sendRoomInfo(String title) {
    	lobby.sendMessage("0 "+ NICKNAME + "###" + title);    	    	
    }
    
    public void sendRoomInfo(String title, String password) {
    	lobby.sendMessage("1 "+ NICKNAME + "###" + title + "***" + password);    	    	
    }
    
    public void makeRoom(String title, int room) {
    	setVisible(false);
    	band = new BandGUI(ip, this, lobby, room, title, NICKNAME);
    	band.open();
    }
    
    public void enterRoom(String title, int room) {
    	setVisible(false);
    	band = new BandGUI(ip, this, lobby, room, title, NICKNAME);
    	band.open();
    }
    
    public void enterPrivateRoom(String title, int room) {
    	EnterRoomGUI enterRoom = new EnterRoomGUI(this, lobby, title, room, NICKNAME);
    	enterRoom.open();
    }
    
    public void removeRoom(String title) {
    	lobby.sendMessage("2 " + title);
    }
        
    public void clearRoomList() {
    	title1.setText("      ");
        enter_btn1.setEnabled(false);
        title2.setText("      ");
        enter_btn2.setEnabled(false);
        title3.setText("      ");
        enter_btn3.setEnabled(false);
        title4.setText("      ");
        enter_btn4.setEnabled(false);
        prev_btn.setEnabled(false);
        next_btn.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        chatScroll = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        listPanel = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();
        chatField = new javax.swing.JTextField();
        id = new javax.swing.JLabel();
        title1 = new javax.swing.JLabel();
        title2 = new javax.swing.JLabel();
        title3 = new javax.swing.JLabel();
        title4 = new javax.swing.JLabel();
        enter_btn1 = new javax.swing.JToggleButton();
        enter_btn2 = new javax.swing.JToggleButton();
        enter_btn3 = new javax.swing.JToggleButton();
        enter_btn4 = new javax.swing.JToggleButton();
        make_btn = new javax.swing.JToggleButton();
        prev_btn = new javax.swing.JToggleButton();
        next_btn = new javax.swing.JToggleButton();
        exit_btn = new javax.swing.JToggleButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lobby");
        setLocation(new java.awt.Point(450, 200));
        setResizable(false);

        textPane.setEditable(false);
        textPane.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        textPane.setForeground(new java.awt.Color(102, 102, 102));
        chatScroll.setViewportView(textPane);

        userList.setBackground(new java.awt.Color(231, 230, 230));
        userList.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        userList.setForeground(new java.awt.Color(102, 102, 102));
        userList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "user1", "user2", "user3" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        userList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userListMouseClicked(evt);
            }
        });
        listPanel.setViewportView(userList);

        chatField.setBackground(new java.awt.Color(231, 230, 230));
        chatField.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        chatField.setForeground(new java.awt.Color(102, 102, 102));
        chatField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatFieldActionPerformed(evt);
            }
        });

        id.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        id.setText("Developer");

        title1.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        title1.setText("TITLE1");

        title2.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        title2.setText("TITLE2");

        title3.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        title3.setText("TITLE3");

        title4.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        title4.setText("TITLE4");

        Image i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/room_enter_btn.png");
		ImageIcon icon = new ImageIcon(i);  //이미지 넣기
        enter_btn1.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/room_enter_clicked.png");
		icon = new ImageIcon(i);  //이미지 넣기
        enter_btn1.setPressedIcon(icon); // NOI18N
        enter_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_btn1ActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/room_enter_btn.png");
		icon = new ImageIcon(i);  //이미지 넣기
        enter_btn2.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/room_enter_clicked.png");
        icon = new ImageIcon(i);  //이미지 넣기
        enter_btn2.setPressedIcon(icon); // NOI18N
        enter_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_btn2ActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/room_enter_btn.png");
		icon = new ImageIcon(i);  //이미지 넣기
        enter_btn3.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/room_enter_clicked.png");
        icon = new ImageIcon(i);  //이미지 넣기
        enter_btn3.setPressedIcon(icon); // NOI18N
        enter_btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_btn3ActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/room_enter_btn.png");
		icon = new ImageIcon(i);  //이미지 넣기
        enter_btn4.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/room_enter_clicked.png");
        icon = new ImageIcon(i);  //이미지 넣기
        enter_btn4.setPressedIcon(icon); // NOI18N
        enter_btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_btn4ActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/create_btn.png");
		icon = new ImageIcon(i);  //이미지 넣기
        make_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/create_clicked.png");
        icon = new ImageIcon(i);  //이미지 넣기
        make_btn.setPressedIcon(icon); // NOI18N
        make_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                make_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/left_btn.png");
		icon = new ImageIcon(i);  //이미지 넣기
        prev_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/left_clicked.png");
        icon = new ImageIcon(i);  //이미지 넣기
        prev_btn.setPressedIcon(icon); // NOI18N
        prev_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prev_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/right_btn.png");
		icon = new ImageIcon(i);  //이미지 넣기
        next_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/right_clicked.png");
		icon = new ImageIcon(i);  //이미지 넣기
        next_btn.setPressedIcon(icon); // NOI18N
        next_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/exit_btn.png");
		icon = new ImageIcon(i);  //이미지 넣기
        exit_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/exit_clicked.png");
		icon = new ImageIcon(i);  //이미지 넣기
        exit_btn.setPressedIcon(icon); // NOI18N
        exit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/lobby/lobby_bg.png");
		icon = new ImageIcon(i);  //이미지 넣기
        bg.setIcon(icon); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(title1)
                .addGap(251, 251, 251)
                .addComponent(title2))
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(enter_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250)
                .addComponent(enter_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(id))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(title3))
                    .addComponent(make_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(prev_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(title4))
                    .addComponent(next_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enter_btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addComponent(listPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(chatScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(enter_btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title1)
                    .addComponent(title2))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enter_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enter_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title3)
                        .addGap(107, 107, 107)
                        .addComponent(make_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(prev_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title4)
                        .addGap(107, 107, 107)
                        .addComponent(next_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(enter_btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(listPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(chatScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(enter_btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(570, 570, 570)
                .addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bg)
        );

        pack();
    }// </editor-fold>                        

    private void enter_btn1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
//    	enterRoom(title1.getText(), idx);
    	lobby.sendMessage("3 " + title1.getText() + "###" + NICKNAME);
    }                                          

    private void enter_btn2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
//    	enterRoom(title2.getText(), idx+1);
    	lobby.sendMessage("3 " + title2.getText() + "###" + NICKNAME);
    }                                          

    private void enter_btn3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	lobby.sendMessage("3 " + title3.getText() + "###" + NICKNAME);
    }                                          

    private void enter_btn4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	lobby.sendMessage("3 " + title4.getText() + "###" + NICKNAME);
    }                                          

    private void make_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	RoomSettingGUI rs = new RoomSettingGUI(this);
    	rs.open();
    }                                        

    private void prev_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	int limit = roomList.size();
    	clearRoomList();
    	if(idx < limit) {
    		idx -= 4;
    		next_btn.setEnabled(true);
        	title1.setText(roomList.get(idx));
        	enter_btn1.setEnabled(true);
        	if(idx + 1 < limit) {
        		title2.setText(roomList.get(idx+1));
        		enter_btn2.setEnabled(true);
        	}if(idx + 2 < limit) {
        		title3.setText(roomList.get(idx+2));
        		enter_btn3.setEnabled(true);
        	}if(idx + 3 < limit) {
        		title4.setText(roomList.get(idx+3));
        		enter_btn4.setEnabled(true);
        	}if(idx == 0){
        		prev_btn.setEnabled(false);
        	}        	
    	} 
    }                                        

    private void next_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	int limit = roomList.size();
    	clearRoomList();
    	if(idx < limit) {
    		idx += 4;
    		prev_btn.setEnabled(true);
        	title1.setText(roomList.get(idx));
        	enter_btn1.setEnabled(true);
        	if(idx + 1 < limit) {
        		title2.setText(roomList.get(idx+1));
        		enter_btn2.setEnabled(true);
        	}if(idx + 2 < limit) {
        		title3.setText(roomList.get(idx+2));
        		enter_btn3.setEnabled(true);
        	}if(idx + 3 < limit) {
        		title4.setText(roomList.get(idx+3));
        		enter_btn4.setEnabled(true);
        	}if(idx + 4 < limit){
        		next_btn.setEnabled(true);
        	}if(idx + 4 < limit){
        		next_btn.setEnabled(true);
        	}else {
        		next_btn.setEnabled(false);
        	}
    	}
    }                                        

    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	ExitGUI exit = new ExitGUI(lobby, chat);
    	exit.open();
    }                                        

    private void chatFieldActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    	String msg = "[" + NICKNAME + "] : " + chatField.getText() + "\n";
    	chat.sendMessage("1 " + msg);    	
    	chatField.setText("");
    }                                         

    private void userListMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
    	String receiver = userList.getSelectedValue();
    	SendMsgGUI sendMsg = new SendMsgGUI(lobby, receiver, NICKNAME);
    	sendMsg.open();
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
            java.util.logging.Logger.getLogger(LobbyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LobbyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LobbyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LobbyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        setVisible(true);
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyGUI().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel bg;
    private javax.swing.JTextField chatField;
    private javax.swing.JScrollPane chatScroll;
    private javax.swing.JToggleButton enter_btn1;
    private javax.swing.JToggleButton enter_btn2;
    private javax.swing.JToggleButton enter_btn3;
    private javax.swing.JToggleButton enter_btn4;
    private javax.swing.JToggleButton exit_btn;
    private javax.swing.JLabel id;
    private javax.swing.JScrollPane listPanel;
    private javax.swing.JToggleButton make_btn;
    private javax.swing.JToggleButton next_btn;
    private javax.swing.JToggleButton prev_btn;
    private javax.swing.JTextPane textPane;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    private javax.swing.JList<String> userList;
    // End of variables declaration                   
}
