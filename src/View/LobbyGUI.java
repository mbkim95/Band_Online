/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Controller.Client;

/**
 *
 * @author K
 */
public class LobbyGUI extends javax.swing.JFrame {

    /**
     * Creates new form LobbyGUI
     */
	static String NICKNAME;
	Client client;
	
    public LobbyGUI() {
        initComponents();
    }
    
    public LobbyGUI(String nickname) {
    	NICKNAME = nickname;    	
        initComponents();
        client = new Client();
        client.connect(this, NICKNAME);
        //rate.setText(win/lose * 100 + "%");
    }    
    
    public void appendMsg(String msg) {
    	try {
    		StyledDocument document = (StyledDocument) textPane.getDocument();    		
        	document.insertString(document.getLength(), msg, null);
        	chatScroll.getVerticalScrollBar().setValue(chatScroll.getVerticalScrollBar().getMaximum());		// ä��â �ڵ� ��ũ��
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
        	chatScroll.getVerticalScrollBar().setValue(chatScroll.getVerticalScrollBar().getMaximum());		// ä��â �ڵ� ��ũ��
    	} catch (BadLocationException e) {
    		e.printStackTrace();
    	}
    }
    
    public void clearUserList() {
    	userList.setText("");    	
    }
    
    public void appendUserList(String nickname) {    	
    	userList.append(nickname + "\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        LobbyPanel = new javax.swing.JPanel();
        room1 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        enter_btn1 = new javax.swing.JButton();
        room2 = new javax.swing.JPanel();
        title2 = new javax.swing.JLabel();
        enter_btn2 = new javax.swing.JButton();
        room3 = new javax.swing.JPanel();
        title3 = new javax.swing.JLabel();
        enter_btn3 = new javax.swing.JButton();
        room4 = new javax.swing.JPanel();
        title4 = new javax.swing.JLabel();
        enter_btn4 = new javax.swing.JButton();
        next_btn = new javax.swing.JButton();
        prev_btn = new javax.swing.JButton();
        make_btn = new javax.swing.JButton();
        exit_btn = new javax.swing.JButton();
        StatusPanel = new javax.swing.JPanel();
        userLabel = new javax.swing.JLabel();
        listPanel = new javax.swing.JScrollPane();
        userList = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        record = new javax.swing.JLabel();
        win_rate = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        rec = new javax.swing.JLabel();
        rate = new javax.swing.JLabel();
        ChatPanel = new javax.swing.JPanel();
        textField = new javax.swing.JTextField();
        chatScroll = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lobby");
        setLocation(new java.awt.Point(600, 300));
        setResizable(false);

        room1.setBackground(new java.awt.Color(255, 255, 255));
        room1.setPreferredSize(new java.awt.Dimension(238, 101));

        title1.setText("1�� ��");

        enter_btn1.setText("��     ��");
        enter_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout room1Layout = new javax.swing.GroupLayout(room1);
        room1.setLayout(room1Layout);
        room1Layout.setHorizontalGroup(
            room1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(room1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, room1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(enter_btn1)))
                .addContainerGap())
        );
        room1Layout.setVerticalGroup(
            room1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(enter_btn1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        room2.setBackground(new java.awt.Color(255, 255, 255));

        title2.setText("2�� ��");

        enter_btn2.setText("��     ��");
        enter_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_btn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout room2Layout = new javax.swing.GroupLayout(room2);
        room2.setLayout(room2Layout);
        room2Layout.setHorizontalGroup(
            room2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(room2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, room2Layout.createSequentialGroup()
                        .addGap(0, 125, Short.MAX_VALUE)
                        .addComponent(enter_btn2)))
                .addContainerGap())
        );
        room2Layout.setVerticalGroup(
            room2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(enter_btn2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        room3.setBackground(new java.awt.Color(255, 255, 255));

        title3.setText("3�� ��");

        enter_btn3.setText("��     ��");
        enter_btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_btn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout room3Layout = new javax.swing.GroupLayout(room3);
        room3.setLayout(room3Layout);
        room3Layout.setHorizontalGroup(
            room3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(room3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, room3Layout.createSequentialGroup()
                        .addGap(0, 125, Short.MAX_VALUE)
                        .addComponent(enter_btn3)))
                .addContainerGap())
        );
        room3Layout.setVerticalGroup(
            room3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(enter_btn3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        room4.setBackground(new java.awt.Color(255, 255, 255));

        title4.setText("4�� ��");

        enter_btn4.setText("��     ��");
        enter_btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_btn4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout room4Layout = new javax.swing.GroupLayout(room4);
        room4.setLayout(room4Layout);
        room4Layout.setHorizontalGroup(
            room4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(room4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, room4Layout.createSequentialGroup()
                        .addGap(0, 125, Short.MAX_VALUE)
                        .addComponent(enter_btn4)))
                .addContainerGap())
        );
        room4Layout.setVerticalGroup(
            room4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(enter_btn4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        next_btn.setText("����");
        next_btn.setDefaultCapable(false);
        next_btn.setVerifyInputWhenFocusTarget(false);
        next_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_btnActionPerformed(evt);
            }
        });

        prev_btn.setText("����");
        prev_btn.setDefaultCapable(false);
        prev_btn.setVerifyInputWhenFocusTarget(false);
        prev_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prev_btnActionPerformed(evt);
            }
        });

        make_btn.setText("�� �����");
        make_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                make_btnActionPerformed(evt);
            }
        });

        exit_btn.setText("��      ��");
        exit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LobbyPanelLayout = new javax.swing.GroupLayout(LobbyPanel);
        LobbyPanel.setLayout(LobbyPanelLayout);
        LobbyPanelLayout.setHorizontalGroup(
            LobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LobbyPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(LobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LobbyPanelLayout.createSequentialGroup()
                        .addComponent(make_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(prev_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(next_btn)
                        .addGap(88, 88, 88)
                        .addComponent(exit_btn))
                    .addGroup(LobbyPanelLayout.createSequentialGroup()
                        .addGroup(LobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(room3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(room1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(LobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(room2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(room4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LobbyPanelLayout.setVerticalGroup(
            LobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LobbyPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(LobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(room2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(room1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(room4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(room3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(LobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(next_btn)
                        .addComponent(prev_btn))
                    .addComponent(make_btn)
                    .addComponent(exit_btn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        userLabel.setText("��� ���� �����");

        userList.setColumns(20);
        userList.setRows(5);
        userList.setRequestFocusEnabled(false);
        listPanel.setViewportView(userList);

        jPanel1.setToolTipText("");
        jPanel1.setName(""); // NOI18N

        name.setText("�г���");

        record.setText("����");

        win_rate.setText("�·�");
        win_rate.setToolTipText("");

        id.setText(NICKNAME);

        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(record)
                    .addComponent(name)
                    .addComponent(win_rate))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rec, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name)
                    .addComponent(id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(record)
                    .addComponent(rec))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(win_rate)
                    .addComponent(rate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        name.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout StatusPanelLayout = new javax.swing.GroupLayout(StatusPanel);
        StatusPanel.setLayout(StatusPanelLayout);
        StatusPanelLayout.setHorizontalGroup(
            StatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(StatusPanelLayout.createSequentialGroup()
                .addGroup(StatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listPanel)
                    .addComponent(userLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        StatusPanelLayout.setVerticalGroup(
            StatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StatusPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(userLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });

        textPane.setEditable(false);
        chatScroll.setViewportView(textPane);

        javax.swing.GroupLayout ChatPanelLayout = new javax.swing.GroupLayout(ChatPanel);
        ChatPanel.setLayout(ChatPanelLayout);
        ChatPanelLayout.setHorizontalGroup(
            ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textField)
            .addComponent(chatScroll)
        );
        ChatPanelLayout.setVerticalGroup(
            ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChatPanelLayout.createSequentialGroup()
                .addComponent(chatScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LobbyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StatusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ChatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LobbyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StatusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ChatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                

    private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    	String msg = "[" + NICKNAME + "] : " + textField.getText() + "\n";
    	client.sendMessage(msg);
    	textField.setText("");
    }                                         

    private void next_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void make_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	RoomSettingGUI rs = new RoomSettingGUI();
    	rs.start();
    }                                        

    private void prev_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void enter_btn1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	InGameGUI game = new InGameGUI(this);
    	game.start();
    	setVisible(false);
    }                                          

    private void enter_btn2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void enter_btn3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void enter_btn4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
       
    }
    
    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	ExitGUI exit = new ExitGUI(client);
    	exit.start();
    }  

    /**
     * @param args the command line arguments
     */
    public void start() {
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
            java.util.logging.Logger.getLogger(LobbyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LobbyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LobbyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LobbyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        setVisible(true);        
        
        /*
        // Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyGUI().setVisible(true);
            }
        });
        */
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel ChatPanel;
    private javax.swing.JPanel LobbyPanel;
    private javax.swing.JPanel StatusPanel;
    private javax.swing.JScrollPane chatScroll;
    private javax.swing.JButton enter_btn1;
    private javax.swing.JButton enter_btn2;
    private javax.swing.JButton enter_btn3;
    private javax.swing.JButton enter_btn4;
    private javax.swing.JLabel id;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane listPanel;
    private javax.swing.JButton make_btn;
    private javax.swing.JLabel name;
    private javax.swing.JButton next_btn;
    private javax.swing.JButton prev_btn;
    private javax.swing.JButton exit_btn;
    private javax.swing.JLabel rate;
    private javax.swing.JLabel rec;
    private javax.swing.JLabel record;
    private javax.swing.JPanel room1;
    private javax.swing.JPanel room2;
    private javax.swing.JPanel room3;
    private javax.swing.JPanel room4;
    private javax.swing.JTextPane textPane;
    private javax.swing.JTextField textField;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextArea userList;
    private javax.swing.JLabel win_rate;
    // End of variables declaration                   
}