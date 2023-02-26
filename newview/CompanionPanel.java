//Name : Md Tousif Islam
//        NSID : fpy823
//        Student ID : 11311128
//        Course No : 88268

        package newview;

import model.GameInfoProvider;
import model.GameObserver;
import util.PropertiesDiskStorage;
import view.ImageCache;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;


/**
 *The JPanel for the new window called companion screen
 */
public class CompanionPanel extends JPanel implements GameObserver
{


    /**
     * To count the number of current invaders
     */
    private int invader_num;
    /**
     * To count the number of invaders just before a change
     */
    private int old_invader_num;
    /**
     * To track the level of heat
     */
    private int heat_lv = 0;

    /**
     * To alternate the index for the invader picture
     */
    private int alternator;

    public GameInfoProvider gameInfoCompanion;

    /**
     * Image list
     */
    public List<String> imageNames;
    /**
     * Store the image in buffer
     */
    public BufferedImage image;


    /**
     * Initialize the panel for the companion frame
     *
     * @param gameInfo the interface to the game used to obtain information from the game
     */
    public CompanionPanel(GameInfoProvider gameInfo)
    {

        gameInfoCompanion = gameInfo;
        gameInfoCompanion.addObserver(this);
        old_invader_num = gameInfoCompanion.currentInvaderNumber();
        invader_num = gameInfoCompanion.currentInvaderNumber();
        setBackground(Color.BLACK);


        imageNames = PropertiesDiskStorage.getInstance().getProperties("invader");
        alternator=0;


    }
    /**
     * The method to be invoked whenever the game changes.
     */
    @Override
    public void gameChanged()
    {
        heat_lv = gameInfoCompanion.currentHeatLevel();
        old_invader_num = invader_num;
        invader_num = gameInfoCompanion.currentInvaderNumber();
        repaint();


    }
    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D bufferedGraphics = (Graphics2D) g;
        bufferedGraphics.setPaint(Color.BLACK);
        bufferedGraphics.fillRect(0, 0, getWidth(), getHeight());
        bufferedGraphics.setPaint(Color.GREEN);

        // to draw the string for heat level
        if (heat_lv >= 0 && heat_lv < 5)
        {
            bufferedGraphics.drawString("Current Heat Level: " + heat_lv,20,190);
        }

        else if (heat_lv >=5)
        {
            bufferedGraphics.drawString("Current Heat Level: Overheated",20,190);
        }

        // to draw the image of the invader
        if (old_invader_num != invader_num)
        {
            if (alternator == 0)
            {
                image = ImageCache.getInstance().getImage(imageNames.get(alternator));
                bufferedGraphics.drawImage(image, 120, 50, 100, 100, null);
                alternator = 1;
            }
            else if (alternator == 1)
            {
                image = ImageCache.getInstance().getImage(imageNames.get(alternator));
                bufferedGraphics.drawImage(image, 120, 50, 100, 100, null);
                alternator = 0;
            }
        }
        else
        {
            image = ImageCache.getInstance().getImage(imageNames.get(alternator));
            bufferedGraphics.drawImage(image, 120, 50, 100, 100, null);
        }

    }
}
