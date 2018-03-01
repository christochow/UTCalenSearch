import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame jf;
    JTextArea jta;
    JScrollPane jsp;
    public GUI(){
        jf = new JFrame();
        jta = new JTextArea();
        jsp = new JScrollPane(jta);
    }
    public String GetInput(String msg){
        String input = JOptionPane.showInputDialog(msg);
        if(input == null){return "-1";}
        return input;
    }
    public void setWindow(){
        jf.setSize(1200,650);
        jf.setLocation(400,250);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(jsp);
        jf.setVisible(true);
        jta.setFont(jta.getFont().deriveFont(18f));
    }
    public void add(String s){
    jta.append(s);
    }
}
