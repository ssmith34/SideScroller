package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private KeyboardInputs keyboardInputs;
	private Game game;

	public GamePanel(Game game) {
		setFocusable(true);
		requestFocus();
		requestFocusInWindow();
		mouseInputs = new MouseInputs(this);
		keyboardInputs = new KeyboardInputs(this);
		this.game = game;
		setPanelSize();
		addMouseListener(mouseInputs);
		addKeyListener(keyboardInputs);
		addMouseMotionListener(mouseInputs);
	}

	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}

	public Game getGame() {
		return game;
	}
}