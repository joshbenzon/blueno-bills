package edu.brown.cs.student.main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class UserAuthPage {
  JFrame jFrame = new JFrame();
  JLabel welcomeScreen = new JLabel("Welcome, ");

  public UserAuthPage(String username) {
    welcomeScreen.setBounds(0, 0, 500, 35);
    welcomeScreen.setFont(new Font(null, Font.BOLD, 25));
    welcomeScreen.setText("Welcome " + username);

    jFrame.add(welcomeScreen);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setSize(600, 600);
    jFrame.setLayout(null);
    jFrame.setVisible(true);

  }
}
