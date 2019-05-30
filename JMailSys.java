
//package com.JMail.JMailSysMail.mainApp;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;


public class JMailSys implements ActionListener, MouseListener{
    
    public static void main(String[]args){
        JMailSys jms = new JMailSys();
        jms.createGui();
    }

    private JFrame frame = new JFrame("JMailSys");
    private JPanel panel = new JPanel();
    private JButton send = new JButton("SendMessage");
    //TextAreas are here
    private JTextField from = new JTextField();
    private JTextField to = new JTextField();
    private JTextArea message = new JTextArea();
    //Labels are here
    private JLabel welcome = new JLabel("Welcome to JMail System...");
    private JLabel from_lbl = new JLabel("From...");
    private JLabel to_lbl = new JLabel("To...");
    private JLabel messege_lbl = new JLabel("Message...");
    private JLabel result = new JLabel();


    private void addMenu(JFrame frame){

        //Creation area####

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuBar menubar = new JMenuBar();

        //Setting properties area####

        exit.setMnemonic('x');
        exit.addActionListener(this);

        file.setMnemonic('F');
        file.add(exit);

        about.setMnemonic('A');
        about.addActionListener(this);

        help.setMnemonic('H');
        help.add(about);

        menubar.add(file);
        menubar.add(help);
        frame.setJMenuBar(menubar);
    }
    private void arrangeComponent(JFrame frame){

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        message.setPreferredSize(new Dimension(200,50));
        from.setAlignmentX(Component.CENTER_ALIGNMENT);
        to.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        from_lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        to_lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        messege_lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        send.setAlignmentX(Component.CENTER_ALIGNMENT);
        result.setAlignmentX(Component.CENTER_ALIGNMENT);
        send.addMouseListener(this);
        panel.add(welcome);
        panel.add(from_lbl);
        panel.add(from);
        panel.add(to_lbl);
        panel.add(to);
        panel.add(messege_lbl);
        panel.add(message);
        panel.add(send);
        panel.add(result);
        frame.add(panel);
    }
    private void createGui(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300,300));
        frame.pack();
        arrangeComponent(frame);
        addMenu(frame);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        if(e.getActionCommand().equals("About")){
            JOptionPane.showMessageDialog(frame,"This software was written by EssamMohamed", JOptionPane.PLAIN_MESSAGE);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){
        if(e.getSource() == send)
        {
            
            String to = to.getText();
            String from = from.getText();
            String host = "localhost";
            String message = messege.getText();

            Properties pro = System.getProperties();
            pro.setProperty("mail.smtp.host", host);

            Session sen = Session.getDefaultInstance(pro);

            try
            {
                MimeMessage msg = new MimeMessage(sen);
                msg.setFrom(new InternetAdress(from));
                msg.addRecipient(Message.RecipientType.TO, new InternetAdress(to));
                msg.setSubject("JMailSysMail");
                msg.setText(messege);
                Transport.send(msg);

                result.setText("The message has been successfully sent.");

            }catch(MessagingException ex){
                result.setText("There has been a problem, please try again latter.");
            }
            
        }
    }
}
