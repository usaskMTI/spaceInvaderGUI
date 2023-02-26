//Name : Md Tousif Islam
//        NSID : fpy823
//        Student ID : 11311128
//        Course No : 88268

        package newview;


import javax.swing.*;
import java.awt.*;
import model.GameInfoProvider;
import view.GameInfoPanel;

/**
 *The JFrame is the new window to the companion screen
 */
public class CompanionView extends JFrame
{
    public static final int TITLE_BAR_HEIGHT = 32;
    public static final int BORDER_WIDTH = 6;

    /**
     * Initialize the window with the companion panel and connect the GameInfoProvider to the companion panel
     *
     * @param gameInfo the interface to the game used to obtain information from the game
     */
    public CompanionView(GameInfoProvider gameInfo)
    {
        setTitle("Companion Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350 + BORDER_WIDTH, 200 + GameInfoPanel.HEIGHT + TITLE_BAR_HEIGHT);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        JPanel companion_panel = new CompanionPanel(gameInfo);
        add(companion_panel);
        setVisible(true);

    }
}
