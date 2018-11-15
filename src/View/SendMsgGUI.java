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

import Controller.LobbyClient;

/**
 *
 * @author K
 */
public class SendMsgGUI extends javax.swing.JFrame {

    /**
     * Creates new form sendMsgGUI
     */
	
	private LobbyClient lobby;
	private String receiver;
	private String sender;
	
    public SendMsgGUI() {
        initComponents();
    }    

    public SendMsgGUI(LobbyClient lobby, String receiver, String sender) {
    	initComponents();
    	this.lobby = lobby;
    	this.receiver = receiver;
    	this.sender = sender;
    	user.setText(receiver);
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
        contentsPane = new javax.swing.JScrollPane();
        contentsArea = new javax.swing.JTextArea();
        send_btn = new javax.swing.JButton();
        cancel_btn = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation(new java.awt.Point(620, 200));
        setResizable(false);

        user.setFont(new java.awt.Font("���� ����", 0, 24)); // NOI18N
        user.setText("User 1");

        contentsArea.setBackground(new java.awt.Color(231, 230, 230));
        contentsArea.setColumns(20);
        contentsArea.setFont(new java.awt.Font("���� ����", 0, 18)); // NOI18N
        contentsArea.setRows(5);
        contentsPane.setViewportView(contentsArea);
        
        Image i = Toolkit.getDefaultToolkit().getImage("rsc/images/sendMsg/send_btn.png");
        ImageIcon icon = new ImageIcon(i);        		
        send_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/sendMsg/send_clicked.png");
        icon = new ImageIcon(i);        		
        send_btn.setPressedIcon(icon); // NOI18N
        send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_btnActionPerformed(evt);
            }
        });
        
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/sendMsg/cancel_btn.png");
        icon = new ImageIcon(i);        		
        cancel_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/sendMsg/cancel_clicked.png");
        icon = new ImageIcon(i);        		
        cancel_btn.setPressedIcon(icon); // NOI18N
        cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/sendMsg/sendMsg_bg.png");
        icon = new ImageIcon(i);        		
        bg.setIcon(icon); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(user))
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(contentsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(user)
                .addGap(402, 402, 402)
                .addComponent(send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(528, 528, 528)
                .addComponent(cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(contentsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bg)
        );

        pack();
    }// </editor-fold>     
    
    private void send_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	try {
    		int offset = 0;
    		int len = contentsArea.getText().length();
    		int cnt = (len / 15) + 1;
    		if(lobby == null) {
    			System.out.println("lobby null");
    		}
    		if(receiver == null) {
    			System.out.println("receiver null");
    		}
    		if(sender == null) {
    			System.out.println("sender null");
    		}    		
    		lobby.sendMessage("5 " + receiver + "###" + sender + "***" + cnt);
    		while(offset < len) {
    			if(offset+15 > len) {
    				String sender = contentsArea.getText(offset, len - offset);
    				lobby.sendMessage(sender);
    			}else {
    				String sender = contentsArea.getText(offset, 15);
    				lobby.sendMessage(sender);
    			}
    			offset += 15;
    		}
    	} catch (BadLocationException e) {
			e.printStackTrace();
		}
    	dispose();
    }                                        

    private void cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {                                           
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
            java.util.logging.Logger.getLogger(SendMsgGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SendMsgGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SendMsgGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SendMsgGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        setVisible(true);
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SendMsgGUI().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel bg;
    private javax.swing.JButton cancel_btn;
    private javax.swing.JTextArea contentsArea;
    private javax.swing.JScrollPane contentsPane;
    private javax.swing.JButton send_btn;
    private javax.swing.JLabel user;
    // End of variables declaration                   
}
