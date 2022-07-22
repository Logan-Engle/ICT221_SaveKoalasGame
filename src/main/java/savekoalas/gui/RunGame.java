package savekoalas.gui;

import java.io.Serializable;

/**
 * This class is just to start up the GUI without requiring any VM options.
 *
 */
public class RunGame implements Serializable{

    public static void main(String[] args) {
        GameGUI.main(args);
    }
}
