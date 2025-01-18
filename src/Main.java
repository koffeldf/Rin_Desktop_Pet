import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Timer;
import javax.sound.sampled.*;


public class Main extends JFrame{
 public Main() throws IOException{
  //declare some things 
  Random ran = new Random();
  Timer timer = new Timer();
  ImageIcon ic = new ImageIcon("icon.jpg");

  //panel
  JLayeredPane panell = new JLayeredPane();
  panell.setPreferredSize(new Dimension(257,281));

  //background setup
  ImageIcon bgi = new ImageIcon("bg.png");
  JLabel bg = new JLabel();
  bg.setBounds(0,0,257,281);
  bg.setIcon(bgi);

  //rin
  ImageIcon rinI = new ImageIcon("rin.png");
  ImageIcon rinhI =new ImageIcon("rin-happy.png");
  JLabel rin = new JLabel();
  rin.setBounds(0,35,143,191);
  rin.setIcon(rinI);

  //jframe config
  setSize(257,281);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setResizable(false);
  setLocationRelativeTo(null);
  setAlwaysOnTop(true);
  setIconImage(ic.getImage());

  //add stuff
  add(panell);
  panell.add(bg, JLayeredPane.DEFAULT_LAYER);
  panell.add(rin, JLayeredPane.PALETTE_LAYER);

  //movement
   timer.schedule(new TimerTask() {
    @Override
    public void run() {
    rin.setBounds(ran.nextInt(180),35,143,191); 
    }
   },0,3000);  


  //pet
    rin.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
        try {
        File samp = new File("rin-happy.wav");
        AudioInputStream audio_stream = AudioSystem.getAudioInputStream(samp);
        Clip clip = AudioSystem.getClip(); 
        clip.open(audio_stream);
        rin.setIcon(rinhI);
        clip.start();

      clip.addLineListener(new LineListener() {
        @Override
        public void update(LineEvent myLineEvent) {
         if (myLineEvent.getType() == LineEvent.Type.STOP){
          clip.close();
          rin.setIcon(rinI);
         }
        }
      });
        }
        catch(IOException | UnsupportedAudioFileException | LineUnavailableException r){
         r.printStackTrace();
        }
     }
    });}


//Starting the app
 public static void main(String[] args) {
  SwingUtilities.invokeLater(() -> {
   Main appH;
     try {
     appH = new Main();
     appH.setUndecorated(true);
     appH.setVisible(true);
     } 
     catch (IOException e) {
       e.printStackTrace();
     }
    });
  }
}


