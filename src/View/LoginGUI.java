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
import Model.UserData;

/**
 *
 * @author K
 */
public class LoginGUI extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
	private DB_Controller db_cont;
	private String ip;
	
    public LoginGUI(String ip) {
        initComponents();
        this.ip = ip;
        db_cont = new DB_Controller(ip);
    	db_cont.Connect();
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
        login_btn = new javax.swing.JButton();
        reg_btn = new javax.swing.JButton();
        error_Msg = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        
        Image i;
        ImageIcon icon;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(780, 300));
        setMinimumSize(new java.awt.Dimension(320, 423));
        setResizable(false);

        id_input.setBackground(new java.awt.Color(231, 230, 230));
        id_input.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        id_input.setForeground(new java.awt.Color(102, 102, 102));
        id_input.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        id_input.setText("username");
        id_input.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                id_inputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                id_inputFocusLost(evt);
            }
        });

        pw_input.setBackground(new java.awt.Color(231, 230, 230));
        pw_input.setFont(new java.awt.Font("맑은 고딕", 0, 20)); // NOI18N
        pw_input.setForeground(new java.awt.Color(102, 102, 102));
        pw_input.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pw_input.setText("password");
        pw_input.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pw_inputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pw_inputFocusLost(evt);
            }
        });
        pw_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pw_inputActionPerformed(evt);
            }
        });
        
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/Login/enter_btn.png");
		icon = new ImageIcon(i);  //이미지 넣기
        login_btn.setIcon(icon); // NOI18N
        login_btn.setToolTipText("");
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/Login/enter_clicked.png");
		icon = new ImageIcon(i);  //이미지 넣기
        login_btn.setPressedIcon(icon); // NOI18N
        login_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_btnActionPerformed(evt);
            }
        });

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/Login/register_btn.png");
		icon = new ImageIcon(i);  //이미지 넣기
        reg_btn.setIcon(icon); // NOI18N
        i = Toolkit.getDefaultToolkit().getImage("rsc/images/Login/register_clicked.png");
		icon = new ImageIcon(i);  //이미지 넣기
        reg_btn.setPressedIcon(icon); // NOI18N
        reg_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reg_btnActionPerformed(evt);
            }
        });

        error_Msg.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        error_Msg.setForeground(new java.awt.Color(255, 51, 51));
        error_Msg.setText(" ");

        i = Toolkit.getDefaultToolkit().getImage("rsc/images/Login/login_bg.png");
		icon = new ImageIcon(i);  //이미지 넣기
        background.setIcon(icon); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(id_input, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(login_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(reg_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(error_Msg, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(pw_input, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(background)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(id_input, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(login_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(reg_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(error_Msg))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(pw_input, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(background)
        );
        
        login_btn.requestFocus();        

        pack();
    }// </editor-fold>     
    
    private void id_inputFocusGained(java.awt.event.FocusEvent evt) {                                     
        // TODO add your handling code here:
    	if(id_input.getText().equals("")) {
    		id_input.setText("user ID");
    	}else {
    		id_input.setText("");
    	}
    }                                    

    private void id_inputFocusLost(java.awt.event.FocusEvent evt) {                                   
        // TODO add your handling code here:
    	if(id_input.getText().equals(""))
    		id_input.setText("user ID");   
    }

    private void pw_inputActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	if(db_cont.Password_Chk(id_input.getText(), new String(pw_input.getPassword()))) {
    		System.out.println("Game Start");
        	setVisible(false);
        	UserData user = db_cont.getUser(id_input.getText());        	
            LobbyGUI lobby = new LobbyGUI(ip, db_cont, user.getNickname());                 
            lobby.open();
    	}else {
    		error_Msg.setForeground(new java.awt.Color(255, 51, 51));
            error_Msg.setText("다시 확인해주세요");
    	}  
    }
    
    private void pw_inputFocusGained(java.awt.event.FocusEvent evt) {                                     
        // TODO add your handling code here:
    	if(new String(pw_input.getPassword()).equals("")) {
    		pw_input.setText("password");
    	}else {
    		pw_input.setText("");
    	}
    }                                    

    private void pw_inputFocusLost(java.awt.event.FocusEvent evt) {                                   
        // TODO add your handling code here:
    	if(new String(pw_input.getPassword()).equals("")) {
    		pw_input.setText("password");
    		
    	}
    }

    private void login_btnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    	if(db_cont.Password_Chk(id_input.getText(), new String(pw_input.getPassword()))) {
    		if(db_cont.isOnline(db_cont.getUser(id_input.getText()).getNickname())) {
    			error_Msg.setForeground(new java.awt.Color(255, 51, 51));
                error_Msg.setText("접속중인 ID입니다");
    		}else {
    			System.out.println("Game Start");
    			setVisible(false);
    			UserData user = db_cont.getUser(id_input.getText());        	
    			LobbyGUI lobby = new LobbyGUI(ip, db_cont, user.getNickname());                 
    			lobby.open();
    		}
    	}else {
    		error_Msg.setForeground(new java.awt.Color(255, 51, 51));
            error_Msg.setText("다시 확인해주세요");
    	}
    }                                         

    private void reg_btnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    	RegGUI reg = new RegGUI(ip, db_cont);
    	reg.open();
    }                               

    /**
     * @param args the command line arguments
     */
    public void open(){
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        setVisible(true);
        /* Create and display the form */
        /*ava.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI(ip).setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel background;
    private javax.swing.JLabel error_Msg;
    private javax.swing.JTextField id_input;
    private javax.swing.JButton login_btn;
    private javax.swing.JPasswordField pw_input;
    private javax.swing.JButton reg_btn;
    // End of variables declaration                   
}
