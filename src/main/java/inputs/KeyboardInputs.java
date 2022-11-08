package inputs;

import Main.Game;
import Main.GamePanel;
import gamestates.Gamestate;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
switch(Gamestate.state) {
    case PLAYING:
        gamePanel.getGame().getPlaying().keyReleased(e);
        break;
    case MENU:
        gamePanel.getGame().getMenu().keyReleased(e);
        break;
    default:
        break;
}
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
}
