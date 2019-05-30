package view;

import contract.IModel;
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
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer {

	/** The view frame. */
	private ViewFrame viewFrame;
	/** The Sound. */
	Sound sound = new Sound("Map");
	/** The Constant serialVersionUID. */
	private static final long    serialVersionUID    = -998294702363713521L;
	/** the texture pack **/
	private String texturePack = "NES";
	private int actualSprite = 1;
	private final int nbSprite = 4;
	private final int refresh = 1;
	private int loopCounter = 0;


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

	public String getTexturePack() {
		return this.texturePack;
	}

	public void setTexturePack(String string) {
		this.texturePack = string;
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


	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(final Graphics graphics) {
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		ArrayList<MapTile> drawMap = this.getViewFrame().getModel().getMap();
		BufferedImage imageTemp = null;
		//Path path = FileSystems.getDefault().getPath("user.dir");
		//String path = System.getProperty("user.dir");



		try {
			imageTemp = ImageIO.read(new File("C:\\Users\\hugod\\Documents\\JPU-BlankProject\\view\\src\\main\\resources\\sprites\\" + this.texturePack +"\\Ground_Two\\Ground_Two.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		for(int i = 0; i < drawMap.size(); i++) {
			graphics.drawImage(imageTemp, drawMap.get(i).getX() * 3, drawMap.get(i).getY() * 3, 48, 48, null);
		}

		for(int i = 0; i < drawMap.size(); i++) {

			try {

				if (drawMap.get(i).getObject().getName().equals("Player_One") || drawMap.get(i).getObject().getName().equals("Enemy_One")) {
					imageTemp = ImageIO.read(new File("C:\\Users\\hugod\\Documents\\JPU-BlankProject\\view\\src\\main\\resources\\sprites\\" + this.texturePack + "\\" + drawMap.get(i).getObject().getName() + "\\" + drawMap.get(i).getObject().getName() + "_"+ drawMap.get(i).getObject().getLastMove() +".png"));
					//imageTemp = ImageIO.read(getClass().getClassLoader().getResource("./sprites/" + this.texturePack + "/" + drawMap.get(i).getObject().getName() + "/" + drawMap.get(i).getObject().getName() + "_"+ drawMap.get(i).getObject().getLastMove() +".png"));
				}
				else if(drawMap.get(i).getObject().getName().equals("Wall_One") || drawMap.get(i).getObject().getName().equals("Ground_One") || drawMap.get(i).getObject().getName().equals("Ground_Two")){
					imageTemp = ImageIO.read(new File("C:\\Users\\hugod\\Documents\\JPU-BlankProject\\view\\src\\main\\resources\\sprites\\" + this.texturePack + "\\" + drawMap.get(i).getObject().getName() + "\\" + drawMap.get(i).getObject().getName() + ".png"));
					//imageTemp = ImageIO.read(getClass().getClassLoader().getResource("./sprites/" + this.texturePack + "/" + drawMap.get(i).getObject().getName() + "/" + drawMap.get(i).getObject().getName() + ".png"));
				}
				else {
					imageTemp = ImageIO.read(new File("C:\\Users\\hugod\\Documents\\JPU-BlankProject\\view\\src\\main\\resources\\sprites\\" + this.texturePack + "\\" + drawMap.get(i).getObject().getName() + "\\" + drawMap.get(i).getObject().getName() + "_"+ this.actualSprite + ".png"));
					//imageTemp = ImageIO.read(getClass().getClassLoader().getResource("./sprites/" + this.texturePack + "/" + drawMap.get(i).getObject().getName() + "/" + drawMap.get(i).getObject().getName() + "_"+ this.actualSprite + ".png"));
				}

			} catch (IOException e) {
				e.printStackTrace();
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