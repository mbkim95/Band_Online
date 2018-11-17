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
public class UserGUI extends javax.swing.JFrame {

    /**
     * Creates new form userGUI
     */
	private DB_Controller db_cont;
	private LobbyGUI lobby;
	private String nickname;
	private boolean chk_pass = false;
	private boolean chk_pass2 = false;
	
    public UserGUI(DB_Controller db_cont, LobbyGUI lobby, String nickname) {
    	initComponents();
    	this.db_cont = db_cont;
    	this.lobby = lobby;    	
    	this.nickname = nickname;
    	user_id.setText(db_cont.getID(nickname));
    	System.out.println(user_id.getText());
    	user_nick.setText(nickname);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        user_id = new javax.swing.JLabel();
        user_nick = new javax.swing.JLabel();
        errorMsg = new javax.swing.JLabel();
        errorMsg2 = new javax.swing.JLabel();
        errorMsg3 = new javax.swing.JLabel();
        pw_in1 = new javax.swing.JPasswordField();
        pw_in2 = new javax.swing.JPasswordField();
        pw_in3 = new javax.swing.JPasswordField();
        pw_in4 = new javax.swing.JPasswordField();
        save_btn = new javax.swing.JButton();
        close_btn = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informtion");
        setLocation(new java.awt.Point(800, 200));
        setResizable(false);

        user_id.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        user_id.setText("Admin");

        user_nick.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        user_nick.setText("TESTER");

        errorMsg.setFont(new java.awt.Font("맑은 고딕", 0, 19)); // NOI18N
        errorMsg.setForeground(new java.awt.Color(255, 0, 0));
        errorMsg.setText(" ");

        errorMsg2.setFont(new java.awt.Font("맑은 고딕", 0, 19)); // NOI18N
        errorMsg2.setForeground(new java.awt.Color(255, 0, 0));
        errorMsg2.setText(" ");

        errorMsg3.setFont(new java.awt.Font("맑은 고딕", 0, 19)); // NOI18N
        errorMsg3.setForeground(new java.awt.Color(255, 0, 0));
        errorMsg3.setText(" ");

        pw_in1.setBackground(new java.awt.Color(231, 230, 230));
        pw_in1.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        pw_in1.setText("password");

        pw_in2.setBackground(new java.awt.Color(231, 230, 230));
        pw_in2.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        pw_in2.setText("password");
        pw_in2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pw_in2KeyReleased(evt);
            }
        });

        pw_in3.setBackground(new java.awt.Color(231, 230, 230));
        pw_in3.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        pw_in3.setText("password");

        pw_in4.setBackground(new java.awt.Color(231, 230, 230));
        pw_in4.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        pw_in4.setText("password");
        pw_in4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pw_in4KeyReleased(evt);
            }
        });

        Image i = Toolkit.getDefaultToolkit().getImage("rsc/images/user/save_btn.png");
        ImageIcon icon = new ImageIcon(i);
        save_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/user/save_clicked.png");
        icon = new ImageIcon(i);
        save_btn.setPressedIcon(icon); // NOI18N
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/user/cancel_btn.png");
        icon = new ImageIcon(i);
        close_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/user/cancel_clicked.png");
        icon = new ImageIcon(i);
        close_btn.setPressedIcon(icon); // NOI18N
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/user/user_bg.png");
        icon = new ImageIcon(i);
        bg.setIcon(icon); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(user_id))
            .addGroup(layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(user_nick))
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(pw_in1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(pw_in2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(errorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(pw_in4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(errorMsg2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(errorMsg3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(close_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(pw_in3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(user_id)
                .addGap(29, 29, 29)
                .addComponent(user_nick)
                .addGap(36, 36, 36)
                .addComponent(pw_in1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(pw_in2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(errorMsg)
                .addGap(66, 66, 66)
                .addComponent(pw_in4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(errorMsg2)
                .addGap(18, 18, 18)
                .addComponent(errorMsg3)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(close_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addComponent(pw_in3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bg)
        );

        pack();
    }// </editor-fold>                        

    private void pw_in2KeyReleased(java.awt.event.KeyEvent evt) {                                   
        // TODO add your handling code here:
    	if(new String(pw_in1.getPassword()).equals(new String(pw_in2.getPassword()))) {    		
    		errorMsg.setForeground(new java.awt.Color(0, 204, 51));
    		errorMsg.setText("비밀번호가 일치합니다");
    		chk_pass = true;
    	}else {    		
    		errorMsg.setForeground(new java.awt.Color(255, 0, 0));	
    		errorMsg.setText("비밀번호가 일치하지 않습니다");
    		chk_pass = false;
    	}
    }                                  

    private void pw_in4KeyReleased(java.awt.event.KeyEvent evt) {                                   
        // TODO add your handling code here:
    	if(new String(pw_in3.getPassword()).equals(new String(pw_in4.getPassword()))) {    		
    		errorMsg2.setForeground(new java.awt.Color(0, 204, 51));
    		errorMsg2.setText("비밀번호가 일치합니다");
    		chk_pass2 = true;
    	}else {    		
    		errorMsg2.setForeground(new java.awt.Color(255, 0, 0));	
    		errorMsg2.setText("비밀번호가 일치하지 않습니다");
    		chk_pass2 = false;
    	}
    }                                  

    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	if(chk_pass && chk_pass2) {
    		if(new String(pw_in1.getPassword()).equals(new String(pw_in3.getPassword()))) {
    			errorMsg3.setText("기존의 비밀번호와 같습니다");
    		}else {
    			String id = db_cont.getID(nickname);
    			if(db_cont.Password_Chk(id, new String(pw_in1.getPassword()))){
    				if(db_cont.change(nickname, new String(pw_in3.getPassword()))) {
    					System.out.println("Success");
    					dispose();
    				}
    			}else {
    				errorMsg3.setText("기존 비밀번호를  확인해주세요");
    			}
    		}
    	}else {
    		errorMsg3.setText("다시한번 확인해주세요");
    	}
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
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (opbg      /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         /* For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        setVisible(true);
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserGUI().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel bg;
    private javax.swing.JButton close_btn;
    private javax.swing.JLabel errorMsg;
    private javax.swing.JLabel errorMsg2;
    private javax.swing.JLabel errorMsg3;
    private javax.swing.JPasswordField pw_in1;
    private javax.swing.JPasswordField pw_in2;
    private javax.swing.JPasswordField pw_in3;
    private javax.swing.JPasswordField pw_in4;
    private javax.swing.JButton save_btn;
    private javax.swing.JLabel user_id;
    private javax.swing.JLabel user_nick;
    // End of variables declaration                   
}
