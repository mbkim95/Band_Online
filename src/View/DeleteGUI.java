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

/**
 *
 * @author K
 */
public class DeleteGUI extends javax.swing.JFrame {

    /**
     * Creates new form AddGUI
     */
	private FriendsGUI friends; 
	private DB_Controller db_cont;
	private String nickname;
	
    public DeleteGUI(FriendsGUI friends, DB_Controller db_cont, String nickname) {
    	this.friends = friends;
    	this.db_cont = db_cont;
    	this.nickname = nickname;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        nick_in = new javax.swing.JTextField();
        delete_btn = new javax.swing.JButton();
        cancel_btn = new javax.swing.JButton();
        errorMsg = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(800, 300));
        setResizable(false);

        nick_in.setBackground(new java.awt.Color(231, 230, 230));
        nick_in.setFont(new java.awt.Font("���� ����", 0, 18)); // NOI18N
        nick_in.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Image i = Toolkit.getDefaultToolkit().getImage("rsc/images/delete/delete_btn.png");
        ImageIcon icon = new ImageIcon(i);
        delete_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/delete/delete_clicked.png");
        icon = new ImageIcon(i);
        delete_btn.setPressedIcon(icon); // NOI18N
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/delete/close_btn.png");
        icon = new ImageIcon(i);
        cancel_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/delete/close_clicked.png");
        icon = new ImageIcon(i);
        cancel_btn.setPressedIcon(icon); // NOI18N
        cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btnActionPerformed(evt);
            }
        });

        errorMsg.setFont(new java.awt.Font("���� ����", 0, 16)); // NOI18N
        errorMsg.setForeground(new java.awt.Color(255, 0, 0));

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/delete/delete_bg.png");
        icon = new ImageIcon(i);
        bg.setIcon(icon); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(nick_in, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(errorMsg))
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(nick_in, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(errorMsg))
            .addComponent(bg)
        );

        pack();
    }// </editor-fold>                        

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	if(db_cont.searchFriend(nickname, nick_in.getText())){
    		db_cont.deleteFriend(nickname, nick_in.getText());
    		friends.getFriends();
    		dispose();
    	}else {
    		errorMsg.setText("�ش� �г����� ���� ģ���� �����ϴ�");
    	}
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
            java.util.logging.Logger.getLogger(DeleteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        setVisible(true);
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteGUI().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel bg;
    private javax.swing.JButton cancel_btn;
    private javax.swing.JButton delete_btn;
    private javax.swing.JLabel errorMsg;
    private javax.swing.JTextField nick_in;
    // End of variables declaration                   
}
