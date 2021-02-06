package ui;

import javax.swing.*;

/**
 * this class creates a windows with a title and message and renders them.
 */
public class Modal extends JDialog {

    private static JButton jButton = new JButton("Restart");

    /**
     * Constructor
     * @param parent the parent JFrame that will be using it.
     * @param title title of the window.
     * @param message message inside the window.
     */
    private Modal( JFrame parent, String title, String message ) {
        super(parent, title, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);

        panel.add(label);
        getContentPane().add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    private Modal(String title, String message,JFrame parent ) {
        super(parent, title, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);

        panel.add(label);
        panel.add(jButton);
        jButton.addActionListener(new RestartButton());
        getContentPane().add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Method that creates an instance of Modal class constructor.
     * @param parent the parent JFrame that will be using it.
     * @param title title of the window.
     * @param message message inside the window.
     */
    public static void render(JFrame parent, String title, String message) {
        new Modal(parent, title, message);
    }

    public static void renderEndOfGame(JFrame parent, String title, String message){
        new Modal(title,message,parent);
    }
}
