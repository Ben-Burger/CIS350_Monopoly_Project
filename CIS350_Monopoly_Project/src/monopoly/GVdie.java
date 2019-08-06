package monopoly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*****************************************************************
A graphical representation of a six-sided die with various controls 
over the appearance.  Current value is constrained between 1 and 6. 
You can control the size and color of the dice. 
<h4>An Example</h4>
<blockquote><pre><code>
GVdice die1 = new GVdice();
die1.roll( );
int result = die1.getValue(); 
</code></pre></blockquote>
<p> 
@author Scott Grissom
@version 1.4 October 10, 2006
*****************************************************************/
@SuppressWarnings("serial")
public class GVdie extends JPanel implements MouseListener, Comparable<Object> {
/** Current value of the die. */
private int myValue, displayValue; 

/** Is the dice currently on hold? */
private boolean held;

/** Current size in pixels. */
private int mySize;

/** Dot size in pixels defined by overall die size. */ 
private int dotSize;

/** Offset in pixels for the left row of dots. */ 
private int left;

/** Offset in pixels for the right row of dots. */ 
private int right;

/** Offset in pixels for the middle dot. */ 
private int middle;

/** Color of the dice when held. */
private Color heldColor = Color.pink;

/** Default color of dice. */
private Color background = Color.WHITE;

/** Repeats for animation. */
private int numRolls;

/** Timer for animation. */
private javax.swing.Timer myTimer;


/*****************************************************************
Constructor creates a die of specified size X size pixels.

@param size the length of each side in pixels
*****************************************************************/
public GVdie(final int size) {
// initialize the die and determine display characteristics 
mySize = size;
held = false; 
dotSize = mySize / 5;
int spacer = (mySize - (3 * dotSize)) / 4; 
left = spacer; 
right = mySize - spacer - dotSize; 
middle = (mySize - dotSize) / 2;
setBackground(background);
setForeground(Color.black);
setSize(size, size); 
setPreferredSize(new Dimension(size, size));
setMinimumSize(new Dimension(size, size));
setMaximumSize(new Dimension(size, size));

// create the fancy border
Border raised = BorderFactory.createRaisedBevelBorder();
Border lowered = BorderFactory.createLoweredBevelBorder();
Border compound = BorderFactory.createCompoundBorder(raised, lowered);
setBorder(compound);

// set default values
displayValue = (int) (Math.random() * 6) + 1;
myValue = (int) (Math.random() * 6) + 1;
setNumRolls(0);
myTimer = new javax.swing.Timer(250, new Animator());
addMouseListener(this);
}


/*****************************************************************
* Default constructor creates a die of size 100 X 100 pixels.
*****************************************************************/
public GVdie() {
    this(100);
}

/*****************************************************************
Is the dice currently held?
@return true if the die is held. Otherwise, false.
*****************************************************************/
public boolean isHeld() {
    return held;
}

/*****************************************************************
Set the die face to blank.
*****************************************************************/
public void setBlank() {
    displayValue = 0;
    repaint();
}

/*****************************************************************
Set whether the die is held or not.
@param h true if die is currently held
*****************************************************************/
public void setHeld(final boolean h) {
    held = h;
    if (held) {
        setBackground(heldColor);
    } else {
        setBackground(background);
    }
    repaint();    
}

/*****************************************************************
Sets the color of the dots.
@param c a Java Color object such as Color.red
*****************************************************************/
public void setForeground(final Color c) {
    super.setForeground(c);
}

/*****************************************************************
Updates the image after obtaining a random value in the range 1 - 6.
@param none
*****************************************************************/
public void roll() {
    myValue = (int) (Math.random() * 6) + 1; 

    // start the animated roll
     myTimer.restart();
    
}

/*****************************************************************
Set the delay in milliseconds between frames of the animation.
Default value is 250.
@param msec milliseconds to delay
*****************************************************************/
public void setDelay(final int msec) {
   if (msec > 0) {
       myTimer = new javax.swing.Timer(msec, new Animator());
   }
}

/*****************************************************************
Set the number of rolls before stopping the animation. 
Default value is 6.
@param num number of rolls before stopping
*****************************************************************/
public void setNumRolls(final int num) {
   numRolls = 0;
   if (num > 0) {
       numRolls = num;
   }
}

/*****************************************************************
Gets the current value of the die (1 - 6).

@return the current value of the die
*****************************************************************/
public int getValue() {
    return myValue;
}

   
/*****************************************************************
Display the current value of the die.  Called automatically
after rolling.  There is no need to call this method directly.
@param g the graphics context for the panel
*****************************************************************/
public void paintComponent(final Graphics g) { 
    super.paintComponent(g);

// paint dots    
switch (displayValue) {   
    case 1:
        g.fillOval(middle, middle, dotSize, dotSize); 
        break;
    case 2:
        g.fillOval(left, left, dotSize, dotSize); 
        g.fillOval(right, right, dotSize, dotSize); 
        break;
    case 3:
        g.fillOval(middle, left, dotSize, dotSize); 
        g.fillOval(middle, middle, dotSize, dotSize); 
        g.fillOval(middle, right, dotSize, dotSize); 
        break;
    case 5:     g.fillOval(middle, middle, dotSize, dotSize);
        // fall through and paint four more dots
    case 4:
        g.fillOval(left, left, dotSize, dotSize); 
        g.fillOval(left, right, dotSize, dotSize); 
        g.fillOval(right, left, dotSize, dotSize); 
        g.fillOval(right, right, dotSize, dotSize); 
        break;
    case 6:
        g.fillOval(left, left, dotSize, dotSize); 
        g.fillOval(left, middle, dotSize, dotSize); 
        g.fillOval(left, right, dotSize, dotSize); 
        g.fillOval(right, left, dotSize, dotSize); 
        g.fillOval(right, middle, dotSize, dotSize); 
        g.fillOval(right, right, dotSize, dotSize); 
        break;
default:
	break;
    }   

}

/*****************************************************************
Respond to the dice being clicked.

@param e the mouse event
*****************************************************************/
public void mouseClicked(final MouseEvent e) {
    if (held) {
        held = false;
        setBackground(background);
    } else {
        held = true;
        setBackground(heldColor);
    }
    repaint();
    
}

/** Had to override. 
 * @param e 
 */
public void mousePressed(final MouseEvent e) { }

/** Had to override. 
 * @param e 
 */
public void mouseReleased(final MouseEvent e) { }

/** Had to override. 
 * @param e 
 */
public void mouseExited(final MouseEvent e) { }

/** Had to override. 
 * @param e 
 */
public void mouseEntered(final MouseEvent e) { }

/*****************************************************************
Allows dice to be compared if necessary.

@param o compare the dice with this object
@return -1 if dice is less than passed object
*****************************************************************/
public int compareTo(final Object o) {
    GVdie d = (GVdie) o;
    return getValue() - d.getValue();
} 

/******************************************************
INNER class to roll the dice as an animation.
******************************************************/
	private class Animator implements ActionListener {
		/** Count. */
		private int count = 0;

		/**
		 * Tells the GUI what to do when an action happens.
		 * @param e - ActionEvent used to indicate action happened
		 */
		public void actionPerformed(final ActionEvent e) {
			displayValue = (int) (Math.random() * 6) + 1;
			repaint();
			// count++;
			// Should we stop rolling?
			if (count == numRolls) {
				count = 0;
				myTimer.stop();
				displayValue = myValue;
				repaint();
			}
		}
	}
}
