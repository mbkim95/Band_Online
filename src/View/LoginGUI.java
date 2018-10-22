/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Client;
import Controller.DB_Controller;
import Model.UserData;

/**
 *
 * @author K
 */
public class LoginGUI extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
	DB_Controller db_cont;
	
    public LoginGUI() {
        initComponents();
        db_cont = new DB_Controller();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        id_input = new javax.swing.JTextField();
        pw_input = new javax.swing.JPasswordField();
        id_label = new javax.swing.JLabel();
        pw_label = new javax.swing.JLabel();
        login_btn = new javax.swing.JButton();
        reg_btn = new javax.swing.JButton();
        error_Msg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("로그인");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(750, 400));
        setResizable(false);

        id_input.setText("");
        id_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_inputActionPerformed(evt);
            }
        });

        pw_input.setText("");
        pw_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pw_inputActionPerformed(evt);
            }
        });

        id_label.setFont(new java.awt.Font("Perpetua Titling MT", 1, 24)); // NOI18N
        id_label.setText(" ID :");
        id_label.setToolTipText("");

        pw_label.setFont(new java.awt.Font("Perpetua Titling MT", 1, 24)); // NOI18N
        pw_label.setText(" PW :");
        pw_label.setToolTipText("");

        login_btn.setFont(new java.awt.Font("굴림", 1, 24)); // NOI18N
        login_btn.setText("Login");
        login_btn.setToolTipText("");
        
        // 로그인 버튼 눌렀을 때 실행되는 부분
        login_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_btnActionPerformed(evt);                
            }
        });

        reg_btn.setFont(new java.awt.Font("굴림", 1, 24)); // NOI18N
        reg_btn.setText("Register");
        reg_btn.addActionListener(new java.awt.event.ActionListener() {
        	
        	// 회원가입 버튼을 눌렀을 때 실행되는 부분
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reg_btnActionPerformed(evt);
            }
        });        
        
        error_Msg.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pw_label)
                            .addComponent(id_label))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(id_input, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pw_input, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(error_Msg, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(reg_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(login_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id_input, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_label, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pw_input, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pw_label, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(error_Msg))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(login_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reg_btn)))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void id_inputActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void pw_inputActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	db_cont.Connect();
    	if(db_cont.Password_Chk(id_input.getText(), new String(pw_input.getPassword()))) {
    		System.out.println("Game Start");
        	setVisible(false);
        	UserData user = db_cont.getUser(id_input.getText());        	
            LobbyGUI lobby = new LobbyGUI(user.getNickname());                 
            lobby.start();
    	}else {
    		error_Msg.setForeground(new java.awt.Color(255, 51, 51));
            error_Msg.setText("다시 확인해주세요");
            db_cont.Disconnect();
    	}    	
    }                                        
    
    // 로그인 버튼 누르면 진행되는 부분 -> db 연동하여 가입정보 확인하고 서버에 연결해야함.
    private void login_btnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:    	
    	db_cont.Connect();
    	if(db_cont.Password_Chk(id_input.getText(), new String(pw_input.getPassword()))) {
    		System.out.println("Game Start");
        	setVisible(false);
        	UserData user = db_cont.getUser(id_input.getText());        	
            LobbyGUI lobby = new LobbyGUI(user.getNickname());                 
            lobby.start();
    	}else {
    		error_Msg.setForeground(new java.awt.Color(255, 51, 51));
            error_Msg.setText("다시 확인해주세요");
            db_cont.Disconnect();
    	}    		
    }                                         
    
    // 가입 버튼 누르면 진행되는 부분
    private void reg_btnActionPerformed(java.awt.event.ActionEvent evt) {    	
    	RegGUI reg = new RegGUI();
    	reg.start();    	
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel error_Msg;
    private javax.swing.JTextField id_input;
    private javax.swing.JLabel id_label;
    private javax.swing.JButton login_btn;
    private javax.swing.JPasswordField pw_input;
    private javax.swing.JLabel pw_label;
    private javax.swing.JButton reg_btn;
    // End of variables declaration                   
}
