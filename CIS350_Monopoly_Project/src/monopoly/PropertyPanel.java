package monopoly;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * A panel to display the properties owned by a player.
 * There is a property panel for every player in the game.
 * 
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis
 * @version	8/5/2019 
 */
@SuppressWarnings("serial")
public class PropertyPanel extends JPanel {
	
	/**
	 * Default constructor for a PropertyPanel.
	 */
	public PropertyPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
}
