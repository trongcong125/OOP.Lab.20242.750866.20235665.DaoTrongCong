package hust.soict.hedspi.aims.screen.manager;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hust.soict.hedspi.aims.Media.Media;
import hust.soict.hedspi.aims.Media.Playable;

public class MediaStore extends JPanel {
    private Media media;
    
    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);
        
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                JDialog playDialog = new JDialog();
                playDialog.setTitle("Playing Media");
                playDialog.setSize(300, 150);
                playDialog.setLocationRelativeTo(null);
                JLabel playMessage = new JLabel("Playing: " + media.getTitle());
                playMessage.setHorizontalAlignment(JLabel.CENTER);
                playDialog.add(playMessage);
                playDialog.setVisible(true);
            });
            container.add(playButton);
        }
        
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}