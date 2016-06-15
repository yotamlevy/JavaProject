package GUIview;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/*
* holds the properties of maze
* @author Yotam
*
*/

public class MazeProperties extends BasicWindow
{
	int height;//x
	int width;//z
	int depth;//y
	private Shell shell;
	//String crossSection;
	
	   public MazeProperties(){
	        shell = new Shell(Display.getCurrent());
	    }


	    // ==================== 6. Action Methods =============================

	    public void open()
	    {
	        shell.open();
	    }

	    public void close()
	    {
	               // Don't call shell.close(), because then
	               // you'll have to re-create it
	        shell.setVisible(false);
	    }
	/**
	 * constructor that define default properties
	 * height=5
	 * width=10
	 * depth=10
	 */

	public MazeProperties(int height, int width, int depth) 
	{
		super();
		this.height = height;
		this.width = width;
		this.depth = depth;
		//this.crossSection = crossSection;
	}
	/**
	 * returning the height of the maze
	 * @return height height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * setting the height of the maze
	 * @param height height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * returning the width of the maze
	 * @return width width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * setting the width of the maze
	 * @param width width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * returning the depth of the maze
	 * @return depth depth
	 */
	public int getDepth() {
		return depth;
	}
	/**
	 * setting the depth of the maze
	 * @param depth depth
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initWidgets() {
		// TODO Auto-generated method stub
		
	}

	/*public String getCrossSection() {
		return crossSection;
	}
	public void setCrossSection(String crossSection) {
		this.crossSection = crossSection;
	}*/
	
	
	
}
