package edu.brown.cs.student.main;

//import javafx.scene.paint.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
//import javafx.scene.paint.Color;
//import java.awt.Color;

public class LoginFrontEnd implements ActionListener {
  JFrame jFrame = new JFrame();
  JButton loginButton = new JButton("Login");
  JButton resetButton = new JButton("Reset");
  JTextField insertUserName = new JTextField();
  JPasswordField insertPassword = new JPasswordField();
  JLabel userID = new JLabel("Username");
  JLabel userPassword = new JLabel("Password:");
  JLabel message = new JLabel("Blueno Bills");


  HashMap<String, String> loginInfo = new HashMap<String, String>();
  public LoginFrontEnd(HashMap<String, String> ogInfo) {
    loginInfo = ogInfo;

    userID.setBounds(50, 100, 75, 25);
    userPassword.setBounds(50, 150, 75, 25);

    message.setBounds(125, 250, 250, 35);
    message.setFont(new Font(null, Font.ITALIC, 25));

    insertUserName.setBounds(125, 100, 200, 25);
    insertPassword.setBounds(125, 150, 200, 25);

    loginButton.setBounds(125, 200, 100, 25);
    loginButton.setFocusable(false);
    loginButton.addActionListener(this);

    resetButton.setBounds(225, 200, 100, 25);
    resetButton.setFocusable(false);
    resetButton.addActionListener(this);

    jFrame.add(userID);
    jFrame.add(userPassword);
    jFrame.add(message);
    jFrame.add(insertUserName);
    jFrame.add(insertPassword);
    jFrame.add(loginButton);
    jFrame.add(resetButton);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setSize(600, 600);
    jFrame.setLayout(null);
    jFrame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == resetButton) {
      insertUserName.setText("");
      insertPassword.setText("");
    }

    if(e.getSource() == loginButton) {
      String id = insertUserName.getText();
      String pass = String.valueOf(insertPassword.getPassword());
      if(loginInfo.containsKey(id)) {
        if(loginInfo.get(id).equals(pass)) {
          //message.setForeground(Color.BLUE);
          message.setText("You have Logged In");
          jFrame.dispose();
          UserAuthPage welcome = new UserAuthPage(id);
        } else {
          //message.setForeground(Color.RED);
          message.setText("Incorrect Password");
        }
      } else {
       // message.setForeground(Color.RED);
        message.setText("Incorrect Username");
      }
    }
  }
}
