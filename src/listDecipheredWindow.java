/* This is a window that prints out 26 shifted messages
 * Maria Salonga
 * Monday, February 4, 2022
 * */
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class listDecipheredWindow {

	/**
	 * This constructor opens the window that contains 26 shifted messages
	 * @param display
	 * @param array
	 */
	public listDecipheredWindow(Display display, String[] array) {
		
		//This is the shell of the window
		Shell shell = new Shell(display);
		shell.setSize(450, 816);
		shell.setText("");
		
		//This is creates the list and prints out all 26 options
		List options = new List(shell, SWT.BORDER);
		options.setBounds(32, 47, 364, 676);
		
		for(int x = 0; x < array.length;x++) {
			
			options.add(array[x]);
			
		}
		
		Label listTitle = new Label(shell, SWT.NONE);
		listTitle.setBounds(32, 16, 271, 25);
		listTitle.setText("List of all possible options:");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
