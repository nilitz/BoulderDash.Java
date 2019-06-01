package view;
import entity.MapTile;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.JPanel;



/**
 * The Class ViewPanel.
 *
 * @author De Grossi Hugo And Geraldi Guillaume
 */
class ViewPanel extends JPanel implements Observer {

	/** The view frame. */
	private ViewFrame viewFrame;
	/** The Sound. */
	Sound sound = new Sound("Map");
	/** The Constant serialVersionUID. */
	private static final long    serialVersionUID    = -998294702363713521L;
	/** actual sprite number **/
	private int actualSprite = 1;
	/** number of sprites in the animation**/
	private final int nbSprite = 4;
	/** refresh speed **/
	private final int refresh = 8;
	/** number of loop **/
	private int loopCounter = 0;
	/** resources Pack **/
	private String resourcesPack = "NES2";
	/** The Background Image **/
	private Image image = ImageIO.read(getClass().getClassLoader().getResource("./sprites/" + this.resourcesPack + "/Ground_Two/Ground_Two.png"));

	/**
	 * Instantiates a new view panel.
	 *
	 * @param viewFrame
	 * the view frame
	 * @throws IOException
	 * throws image related exception
	 */
	public ViewPanel(final ViewFrame viewFrame) throws IOException {
		this.setViewFrame(viewFrame);
		viewFrame.getModel().getObservable().addObserver(this);
	}

	/**
	 * Gets the view frame.
	 *
	 * @return the view frame
	 */
	private ViewFrame getViewFrame() {
		return this.viewFrame;
	}

	/**
	 * Sets the view frame.
	 *
	 * @param viewFrame
	 * the new view frame
	 */
	private void setViewFrame(final ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		sound.run();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable arg0, final java.lang.Object arg1)  { this.repaint(); }

	/**
	 * display the diamonds counter
	 * @param graphics
	 * graphics
	 */
	public void showCounter(Graphics graphics){
		graphics.setColor(Color.WHITE);
		graphics.fillRect(getWidth() / 2 - 120, 10, 240, 30);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(getWidth() / 2 - 119, 11, 238, 28);

		graphics.setColor(Color.BLUE);
		graphics.fillRect(getWidth() / 2 - 119, 11, (this.viewFrame.getModel().getDiamondCounter() * 238) / this.viewFrame.getModel().getDiamondToHave(), 28);
}

	/**
	 * display a death message
	 * @param graphics
	 * graphics
	 */
	public void showDeath(Graphics graphics){
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, this.viewFrame.getHeight() / 2 - 100, this.viewFrame.getWidth(), 100);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Verdana", Font.BOLD, 80));
		graphics.drawString("YOU ARE DEAD", this.viewFrame.getHeight() / 7, this.viewFrame.getHeight() / 2 - 20);
	}

	/**
	 * display a win message
	 * @param graphics
	 * graphics
	 */
	public void showWin(Graphics graphics){
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, this.viewFrame.getHeight() / 2 - 100, this.viewFrame.getWidth(), 100);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Verdana", Font.BOLD, 80));
		graphics.drawString("YOU WON", this.viewFrame.getHeight() / 4, this.viewFrame.getHeight() / 2 - 20);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(final Graphics graphics) {
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		ArrayList<MapTile> drawMap = this.getViewFrame().getModel().getMap();
		try {
			this.getViewFrame().getModel().setDiamondToHave(this.getViewFrame().getModel().getDiamondNumber(this.getViewFrame().getModel().getID()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Image imageTemp;
		imageTemp = this.image;

		for(int i = 0; i < drawMap.size(); i++) {
			graphics.drawImage(imageTemp, drawMap.get(i).getX() * 3, drawMap.get(i).getY() * 3, 48, 48, null);
		}
		for(int i = 0; i < drawMap.size(); i++) {

			if (drawMap.get(i).getObject().getName().equals("Player_One") || drawMap.get(i).getObject().getName().equals("Enemy_One")) {
				switch (drawMap.get(i).getObject().getLastMove()){
					case UP:
						imageTemp = drawMap.get(i).getObject().getImage2();
						break;
					case LEFT:
						imageTemp = drawMap.get(i).getObject().getImage1();
						break;
					case RIGHT:
						imageTemp = drawMap.get(i).getObject().getImage3();
						break;
					case NOTHING:
					case DOWN :
						imageTemp = drawMap.get(i).getObject().getImage4();
						break;
				}
			}
			else if(drawMap.get(i).getObject().getName().equals("Wall_One") || drawMap.get(i).getObject().getName().equals("Ground_One") || drawMap.get(i).getObject().getName().equals("Ground_Two")){
				imageTemp = drawMap.get(i).getObject().getImage1();
			}
			else {
				switch (this.actualSprite){
					case 1 :
						imageTemp = drawMap.get(i).getObject().getImage1();
						break;
					case 2 :
						imageTemp = drawMap.get(i).getObject().getImage2();
						break;
					case 3:
						imageTemp = drawMap.get(i).getObject().getImage3();
						break;
					case 4:
						imageTemp = drawMap.get(i).getObject().getImage4();
						break;
				}
			}
			graphics.drawImage(imageTemp, drawMap.get(i).getX() * 3, drawMap.get(i).getY() * 3, 48, 48, null);
		}

		//SPRITES REFRESH (LOOP)
		if(this.refresh == this.loopCounter){
			this.actualSprite++;
			this.loopCounter = 0;
		}
		else {this.loopCounter++;}
		if(this.actualSprite == (this.nbSprite + 1)) {
			this.actualSprite = 1;
		}
		try {
			this.getViewFrame().adaptWindow();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//FINAL PRINT METHODS CALL
		if (this.viewFrame.getModel().playerStatus()){ showDeath(graphics); }
		else if(this.viewFrame.getModel().getWin()){ showWin(graphics);}
		showCounter(graphics);
		this.repaint();
	}
}