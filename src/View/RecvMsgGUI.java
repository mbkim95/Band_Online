/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import Controller.DB_Controller;
import Controller.LobbyClient;

/**
 *
 * @author K
 */
public class RecvMsgGUI extends javax.swing.JFrame {

    /**
     * Creates new form sendMsgGUI
     */
	private LobbyClient lobby;
	private String sender;
	private String receiver;
	private DB_Controller db_cont;
	
    public RecvMsgGUI() {
        initComponents();
    }   
    
    public RecvMsgGUI(DB_Controller db_cont, LobbyClient lobby, String sender, String receiver, String date, String time) {
    	initComponents();
    	this.lobby = lobby;
    	this.sender = sender;
    	this.receiver = receiver;
    	this.date.setText(date);
    	this.time.setText(time);
    	user.setText(sender);
    }
    
    public RecvMsgGUI(DB_Controller db_cont, LobbyClient lobby, String sender, String receiver, String date, String time, String contents) {
    	initComponents();
    	this.lobby = lobby;
    	this.sender = sender;
    	this.receiver = receiver;
    	this.date.setText(date);
    	this.time.setText(time);
    	user.setText(sender);
    	contentsArea.setText(contents);
    }

	public void appendLetter(String msg) {
    	contentsArea.append(msg);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        user = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        contentsPane = new javax.swing.JScrollPane();
        contentsArea = new javax.swing.JTextArea();
        reply_btn = new javax.swing.JButton();
        close_btn = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(620, 200));

        user.setFont(new java.awt.Font("���� ����", 0, 24)); // NOI18N
        user.setText("User 1");
        user.setToolTipText("");

        date.setFont(new java.awt.Font("���� ����", 0, 18)); // NOI18N
        date.setText("2018/11/11");

        time.setFont(new java.awt.Font("���� ����", 0, 18)); // NOI18N
        time.setText("���� 2�� 22�� 22��");

        contentsArea.setEditable(false);
        contentsArea.setBackground(new java.awt.Color(231, 230, 230));
        contentsArea.setColumns(20);
        contentsArea.setFont(new java.awt.Font("���� ����", 0, 18)); // NOI18N
        contentsArea.setRows(5);
        contentsPane.setViewportView(contentsArea);

        Image i = Toolkit.getDefaultToolkit().getImage("rsc/images/recvMsg/reply_btn.png");
        ImageIcon icon = new ImageIcon(i);
        reply_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/recvMsg/reply_clicked.png");
        icon = new ImageIcon(i);
        reply_btn.setPressedIcon(icon); // NOI18N
        reply_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reply_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/recvMsg/close_btn.png");
        icon = new ImageIcon(i);
        close_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/recvMsg/close_clicked.png");
        icon = new ImageIcon(i);
        close_btn.setPressedIcon(icon); // NOI18N
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/recvMsg/recvMsg_bg.png");
        icon = new ImageIcon(i);
        bg.setIcon(icon); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(reply_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(420, 420, 420)
                .addComponent(time))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(contentsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(450, 450, 450)
                .addComponent(date))
            .addGroup(layout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(close_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(user))
            .addGroup(layout.createSequentialGroup()
                .addGap(528, 528, 528)
                .addComponent(reply_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(time))
            .addGroup(layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(contentsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(date))
            .addGroup(layout.createSequentialGroup()
                .addGap(528, 528, 528)
                .addComponent(close_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bg)
        );

        pack();
    }// </editor-fold>                        

    private void reply_btnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    	boolean online = db_cont.isOnline(sender);
    	SendMsgGUI sendMsg = new SendMsgGUI(db_cont, lobby, sender, receiver, online);
    	sendMsg.open();
    	dispose();
    }                                         

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(RecvMsgGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecvMsgGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecvMsgGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecvMsgGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        setVisible(true);
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecvMsgGUI().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel bg;
    private javax.swing.JButton close_btn;
    private javax.swing.JTextArea contentsArea;
    private javax.swing.JScrollPane contentsPane;
    private javax.swing.JLabel date;
    private javax.swing.JButton reply_btn;
    private javax.swing.JLabel time;
    private javax.swing.JLabel user;
    // End of variables declaration                   
}
