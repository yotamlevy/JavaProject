package presenter;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import GUIview.BasicWindow;

/**
 * A class that make a GUi with the user, and sets the {@link presenter.Properties Properties} 
 * @author Yotam
 *
 */
public class PropertiesXMLCreator extends BasicWindow {
	
	private Properties properties;
	private MessageBox messageBox;
	
	/**
	 * Constructor
	 * @param title - the title of the shell 
	 * @param width - width of the shell
	 * @param height - height of the sehll
	 */
	public PropertiesXMLCreator(String title, int width, int height)
	{
		super(title, width, height);
		shell.setLayout(new GridLayout(2, false));
		messageBox = new MessageBox(shell,  SWT.ICON_INFORMATION| SWT.OK);
		messageBox.setText("Message");
		properties = new Properties();
	}
	
	/**
	 * This method starts the GUI.
	 */
	@Override
	public void run()
	{
		start();	
	}

	/**
	 * This method crates the GUI and there listeners.
	 */
	@Override
	public void initWidgets() 
	{

		GridData labels = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		GridData texts = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		
		Label ui = new Label(shell, SWT.READ_ONLY | SWT.BOTTOM);
		ui.setLayoutData(labels);
		ui.setText("Choose a UI:");
		Combo uiList = new Combo(shell, SWT.READ_ONLY);
		uiList.setLayoutData(texts);
		String[] uis = {"GUI" , "CLI"};
		uiList.setItems(uis);
		
		Label algorithm = new Label(shell, SWT.READ_ONLY | SWT.BOTTOM);
		algorithm.setLayoutData(labels);
		algorithm.setText("Choose a Search Algorithm:");
		Combo algorithmList = new Combo(shell, SWT.READ_ONLY);
		algorithmList.setLayoutData(texts);
		String[] algorithms = {"DFS" ,"BFS"};
		algorithmList.setItems(algorithms);
		
		Label generator = new Label(shell, SWT.READ_ONLY);
		generator.setText("Choose a Generator Algorithm:");
		generator.setLayoutData(labels);
		Combo generatorList = new Combo(shell, SWT.READ_ONLY);
		generatorList.setLayoutData(texts);
		String[] generators = {"Prim" , "Simple"};
		generatorList.setItems(generators);
		
//		Label port = new Label(shell, SWT.READ_ONLY);
//		port.setText("Server Port");
//		port.setLayoutData(labels);
//		Text portTXt = new Text(shell, SWT.BORDER);
//		portTXt.setLayoutData(texts);
		
		Button okB = new Button(shell, SWT.NONE);
		okB.setText("OK");
		okB.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true, 1, 1));
				
		okB.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				XMLEncoder encoder = null;
				try 
				{
					encoder = new XMLEncoder(new FileOutputStream("Properties.xml"));
				}
				catch (FileNotFoundException e)
				{
					sendMessage(e.getMessage());
				}
				properties.setTypeOfUserInterfece(uiList.getText());
				properties.setSolveMazeAlgorithm(algorithmList.getText());
				properties.setAlgorithmToGenerateMaze(generatorList.getText());
//				int port = 0;
//				try
//				{
//					port = Integer.parseInt(portTXt.getText());
//					properties.setPort(port);
//				}
//				catch(NumberFormatException e)
//				{
//					sendMessage(e.getMessage());
//				} catch (Exception e) 
//				{
//					sendMessage(e.getMessage());
//				}
				
				
				encoder.writeObject(properties);
				encoder.flush();
				sendMessage("The properties has been uploaded");
				encoder.close();
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
	}
	
	public void sendMessage(String s)
	{		
		messageBox.setMessage(s);
		messageBox.open();
	}
	
	public Properties getproperties()
	{
		return properties;
	}




}