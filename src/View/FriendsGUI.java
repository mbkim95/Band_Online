/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import Controller.DB_Controller;
import Controller.LobbyClient;

/**
 *
 * @author K
 */
public class FriendsGUI extends javax.swing.JFrame {

    /**
     * Creates new form FriendsGUI
     */
	private DB_Controller db_cont; 
	private String nickname;
	private LobbyClient lobby;
	private ArrayList<String> f_list = new ArrayList<String>();
	private ArrayList<String> online_list = new ArrayList<String>();
	
    public FriendsGUI(DB_Controller db_cont, LobbyClient lobby, String nickname) {
        initComponents();
        this.db_cont = db_cont;
        this.nickname = nickname;
        this.lobby = lobby;
        user_nick.setText(nickname);
        getFriends();
    }
    
    public void getFriends() {
    	getDB();
    	ListModel<String> list = friendList.getModel();
		DefaultListModel<String> dList = new DefaultListModel<String>();    
    	int size = f_list.size();
    	String nickname;
    	boolean online;
    	for(int i=0; i<size; i++) {
    		online = db_cont.isOnline(f_list.get(i));
    		if(online) {
    			nickname = f_list.get(i);
    			dList.addElement(nickname + " - 立加吝");
    			online_list.add(nickname);
    		}else {
    			nickname = f_list.get(i);
    			dList.addElement(nickname);
    		}
    	}
		friendList.setModel(dList);
    }
    
    public void getDB() {    	
    	f_list.clear();
    	online_list.clear();
    	try {
//    		ListModel<String> list = friendList.getModel();
//    		DefaultListModel<String> dList = new DefaultListModel<String>();    		
    		ResultSet rs = db_cont.getFriend(nickname);
    		String friend;
    		boolean online = false;
			while(rs.next()) {
				friend = rs.getString("user2");
				f_list.add(friend);				
//				dList.addElement(friend);
			}
//			friendList.setModel(dList);
		} catch (SQLException e) {
			e.printStackTrace();
		}    	    	
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        user_nick = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        friendList = new javax.swing.JList<>();
        friend_label = new javax.swing.JLabel();
        add_btn = new javax.swing.JButton();
        delete_btn = new javax.swing.JButton();
        refresh_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(1000, 300));
        setResizable(false);

        user_nick.setText("Friend List");

        friendList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        friendList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        friendList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                friendListMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(friendList);

        friend_label.setText("jLabel1");

        add_btn.setText("add");
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        delete_btn.setText("delete");
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        refresh_btn.setText("refresh");
        refresh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(user_nick))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(friend_label)
                .addGap(29, 29, 29)
                .addComponent(refresh_btn)
                .addGap(18, 18, 18)
                .addComponent(add_btn)
                .addGap(13, 13, 13)
                .addComponent(delete_btn))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(user_nick)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(friend_label))
                    .addComponent(refresh_btn)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(delete_btn)
                        .addComponent(add_btn)))
                .addGap(2, 2, 2)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>                        

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    	AddGUI add = new AddGUI(this, db_cont, nickname);
    	add.open(); 
    }                                       

    private void friendListMouseClicked(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
    	String friend = friendList.getSelectedValue();
    	if((friend != null) && !(friend.equals(nickname))) {
    		if((friend.contains(" - 立加吝"))) {
    			friend = friend.substring(0, friend.indexOf(" - 立加吝"));    		
    			SendMsgGUI sendMsg = new SendMsgGUI(db_cont, lobby, friend, nickname, true);
    			sendMsg.open();
    		}else {
    			SendMsgGUI sendMsg = new SendMsgGUI(db_cont, lobby, friend, nickname, false);
    			sendMsg.open();
    		}
    	}
    }                                       

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	DeleteGUI del = new DeleteGUI(this, db_cont, nickname);
    	del.open();
    }                                          

    private void refresh_btnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    	getFriends();
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
            java.util.logging.Logger.getLogger(FriendsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FriendsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FriendsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FriendsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        setVisible(true);
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FriendsGUI().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton add_btn;
    private javax.swing.JButton delete_btn;
    private javax.swing.JList<String> friendList;
    private javax.swing.JLabel friend_label;
    private javax.swing.JButton refresh_btn;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel user_nick;
    // End of variables declaration                   
}
