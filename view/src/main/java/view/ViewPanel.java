package view;

import contract.IModel;
import entity.LastMove;
import entity.MapTile;
import entity.Object;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.JPanel;



/**
 * The Class ViewPanel.
 *
 * @author HugoDegrossi
 */
class ViewPanel extends JPanel implements Observer {

	/** The view frame. */
	private ViewFrame viewFrame;
	/** The Sound. */
	Sound sound = new Sound("Map");
	/** The Constant serialVersionUID. */
	private static final long    serialVersionUID    = -998294702363713521L;
	/**The actual Sprite */
	private int actualSprite = 1;
	/** The sprite Number*/
	private final int nbSprite = 4;
	/** Number of Refresh*/
	private final int refresh = 10;
	/** Counter of Loop*/
	private int loopCounter = 0;
	/** 2 New Label*/
	Label label1 = new Label();
	Label label2 = new Label();

	/**
	 * Instantiates a new view panel.
	 *
	 * @param viewFrame
	 *          the view frame
	 */
	public ViewPanel(final ViewFrame viewFrame) throws IOException {
		this.setViewFrame(viewFrame);
		viewFrame.getModel().getObservable().addObserver(this);
		try {
			sound.run();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
	 *          the new view frame
	 */
	private void setViewFrame(final ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable arg0, final java.lang.Object arg1)  { this.repaint(); }


	/**
	 * Methods who creates labels used in PaintComponent
	 */
	public void label(){
		label1.setBounds(450, 0, 150, 50 );
		label2.setBounds(600, 0, 150, 50 );
		label1.setText("REQUIRED : " + this.viewFrame.getModel().getDiamondToHave());
		label2.setText("ACTUAL : " + this.viewFrame.getModel().getDiamondCounter());
		label1.setFont(new Font("Roboto", 1, 10));
		label2.setFont(new Font("Roboto", 1, 10));
		this.viewFrame.add(label1);
		this.viewFrame.add(label2);
	}

	/**
	 *Pain everything on the window.
	 * @param graphics
	 */
	@Override
	protected void paintComponent(final Graphics graphics) {

		label();

		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		ArrayList<MapTile> drawMap = this.getViewFrame().getModel().getMap();
		try {
			this.getViewFrame().getModel().setDiamondToHave(this.getViewFrame().getModel().getDiamondNumber(this.getViewFrame().getModel().getID()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Image imageTemp = null;
		try {
			imageTemp = ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Ground_Two/Ground_Two.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


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


		this.repaint();

	}


}
