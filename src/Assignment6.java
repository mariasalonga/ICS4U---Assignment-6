/*
 * Maria Salonga
 * Monday, February 4, 2022
 *  This code uses the Ceasar cipher and allows the user to:
 * 	a. Give a message to be encoded and the key to be used
	b. Give a message to be decoded and the key to be used
	c. Give an encoded message. The output will be the 26 possible decoded messages
	d. Give an encoded message, the output will be the best guessed decoded message
 */
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Assignment6 {
	private static Text messageTextBox;
	private static Text keyTextBox;
	
	/**
	 * This method encodes a String (message) using an int (key).
	 * @param message contains the message that the user enters.
	 * @param key contains the number that the password is shifted by
	 * @return the encoded message shifted by the key
	 */
	public static String encode(String message, int key) {
		
		String encodedMessage = "";
		char messageArray[] = message.toCharArray();
		
		for(int x = 0;x < messageArray.length;x++) {
			int newPosition;
			switch (messageArray[x]) {
				
				case 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90:
					newPosition = (char) (((messageArray[x] - 'A') + key) % 26);
					messageArray[x] = (char) (newPosition + 'A');
					break;
				
				case 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122:
					newPosition = (char) (((messageArray[x] - 'a') + key) % 26);
					messageArray[x] = (char) (newPosition + 'a');
					break;
					
				default:
					
					break;
					
			}
				
			
		}
		
		encodedMessage = String.valueOf(messageArray);
		return encodedMessage;
		
	}
	
	/**
	 * This method decodes a String (message) using an int (key)
	 * @param message contains the message that the user enters.
	 * @param key contains the number that the password is shifted by
	 * @return the decoded message shifted by the key
	 */
	public static String decode(String message, int key) {
		
		String decodedMessage = "";
		char messageArray[] = message.toCharArray();
		
		for(int x = 0;x < messageArray.length;x++) {
			int newPosition;
			switch (messageArray[x]) {
				
				case 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90:
					int position = (messageArray[x] - 'A') - key;
						if(position < 0) {
							position = position + 26;
						}
					newPosition = (char) ((position - key) % 26);
					messageArray[x] = (char) (newPosition + 'A');
					break;
				
				case 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122:
					int newposition = (messageArray[x] - 'a') - key;
						if(newposition < 0) {
							newposition = newposition + 26;
						}
					newPosition = (char) ((newposition) % 26);
					messageArray[x] = (char) (newPosition + 'a');
					break;
					
				default:
					
					break;
					
			}
		}
		
		decodedMessage = String.valueOf(messageArray);
		return decodedMessage;
		
	}
	
	/**
	 * This method prints out 26 possible shifted Strings
	 * @param message is a String that the user enters
	 * @return an array of 26 shifted Strings
	 */
	public static String[] guessOptions(String message) {
		String[] options = new String[26];
		
		for(int x = 0; x < 26; x++) {
			
			options[x] = "";
			
		}
		
		char messageArray[];
		
		//This for loop runs through the 26 shifting possibilities
		for(int key = 1; key < 26; key++) {
			
			messageArray = message.toCharArray();
			
			for(int x = 0;x < messageArray.length;x++) {
				int newPosition;
				switch (messageArray[x]) {
				
					case 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90:
						newPosition = (char) (((messageArray[x] - 'A') + key) % 26);
						messageArray[x] = (char) (newPosition + 'A');
						options[key - 1] = (options[key - 1])+=String.valueOf(messageArray[x]);
						break;
				
					case 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122:
						newPosition = (char) (((messageArray[x] - 'a') + key) % 26);
						messageArray[x] = (char) (newPosition + 'a');
						options[key - 1] = (options[key - 1])+=String.valueOf(messageArray[x]);
						break;
					
					default:
						options[key - 1] = (options[key - 1])+=String.valueOf(messageArray[x]);
						break;
						
				}
				
			}
			
		}
		
		return options;
		
	}
	
	/**
	 * This method guesses the most probable deciphered message
	 * @param message is a String that the user enters
	 * @return a String containing the best guess
	 */
			public static String guessOne(String message) {
				int count = 0;
				String[] options = guessOptions(message);
				for(int x = 0; x < 26; x++) {
					count = x;
					String[] tokens = options[x].split(" ");
					for(int y = 0; y < tokens.length; y++) {
						if(tokens[y].equalsIgnoreCase("the")||tokens[y].equalsIgnoreCase("of")||tokens[y].equalsIgnoreCase("and")||tokens[y].equalsIgnoreCase("to")||tokens[y].equalsIgnoreCase("is")||tokens[y].equalsIgnoreCase("in")||tokens[y].equalsIgnoreCase("you")||tokens[y].equalsIgnoreCase("it")||tokens[y].equalsIgnoreCase("that")||tokens[y].equalsIgnoreCase("suck")||tokens[y].equalsIgnoreCase("he")||tokens[y].equalsIgnoreCase("was")||tokens[y].equalsIgnoreCase("for")||tokens[y].equalsIgnoreCase("on")||tokens[y].equalsIgnoreCase("are")) {	
									return options[x];					
						}	
						
					}
					
				}
						for(int x = 0; x < 26; x++) {
							count = x;
							double vowels = 0;
							double cons = 0;
							char[] charToken = options[x].toCharArray();
							for(int y = 0; y < charToken.length; y++) {
								switch (charToken[y]) {
									case 'a','A','e','E','i','I','O','o','u','U':
										vowels++;
										break;
									case 'b','B','c','C','d','D','f','F','g','G','h','H','j','J','k','K','l','L','m','M','n','N','p','P','q','Q','r','R','s','S','t','T','v','V','w','W','x','X','y','Y','z','Z':
										cons++;
										break;
									
								}
								double average = (vowels+cons)/cons;
									if(average>=0.58) {
										
										return options[x];
								}
								
							}
							
						}
						
					return options[count];
						
				}
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		//This is the shell
		Display display = Display.getDefault();
		Shell shlAssignment = new Shell();
		shlAssignment.setSize(883, 514);
		shlAssignment.setText("Assignment 6");
		
		//This text box contains the user's input
		messageTextBox = new Text(shlAssignment, SWT.BORDER);
		messageTextBox.setBounds(35, 111, 780, 42);
		
		//These are labels
		Label messageTitle = new Label(shlAssignment, SWT.NONE);
		messageTitle.setFont(SWTResourceManager.getFont("Vivaldi", 12, SWT.NORMAL));
		messageTitle.setBounds(35, 75, 102, 25);
		messageTitle.setText("Message");
		
		Label keyTitle = new Label(shlAssignment, SWT.NONE);
		keyTitle.setText("Key");
		keyTitle.setFont(SWTResourceManager.getFont("Vivaldi", 12, SWT.NORMAL));
		keyTitle.setBounds(35, 194, 104, 25);
		
		//This txt box contains the user's key
		keyTextBox = new Text(shlAssignment, SWT.BORDER);
		keyTextBox.setBounds(35, 232, 104, 42);
		
		//This is the action listener for the encode button
		Button encodeButton = new Button(shlAssignment, SWT.NONE);
		encodeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if((messageTextBox.getText()).equals("")||(keyTextBox.getText()).equals("")) {
					
					MessageBox invalidInput = new MessageBox(shlAssignment, SWT.ICON_WARNING);
					invalidInput.setMessage("Invalid message or key. Please try again.");
					invalidInput.open();
					
				} else if((Integer.parseInt(keyTextBox.getText()) <= 0 || (Integer.parseInt(keyTextBox.getText())) > 26)) {
					
					MessageBox invalidInteger = new MessageBox(shlAssignment, SWT.ICON_WARNING);
					invalidInteger.setMessage("Invalid integer. Please try again.");
					invalidInteger.open();		
		
				} else {	
					
					int key = Integer.parseInt(keyTextBox.getText());
					String encodedMessage = encode(messageTextBox.getText(),key);
					MessageBox encodedMessageBox = new MessageBox(shlAssignment);
					encodedMessageBox.setMessage("The encoded message is: " + encodedMessage);
					encodedMessageBox.open();
					
				}
				
			}
		});
		encodeButton.setText("Encode");
		encodeButton.setBounds(35, 340, 130, 42);
		
		//This is the action listener for the decode button
		Button decipherButton = new Button(shlAssignment, SWT.NONE);
		decipherButton.setText("Decipher");
		decipherButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if((messageTextBox.getText()).equals("")||(keyTextBox.getText()).equals("")) {
					
					MessageBox invalidInput = new MessageBox(shlAssignment, SWT.ICON_WARNING);
					invalidInput.setMessage("Invalid message or key. Please try again.");
					invalidInput.open();
					
				} else if((Integer.parseInt(keyTextBox.getText()) <= 0 || (Integer.parseInt(keyTextBox.getText())) > 26)) {
					
					MessageBox invalidInteger = new MessageBox(shlAssignment, SWT.ICON_WARNING);
					invalidInteger.setMessage("Invalid integer. Please try again.");
					invalidInteger.open();		
		
				} else {
					int key = Integer.parseInt(keyTextBox.getText());
					String decodedMessage = decode(messageTextBox.getText(),key);
					MessageBox decodedMessageBox = new MessageBox(shlAssignment);
					decodedMessageBox.setMessage("The decoded message is: " + decodedMessage);
					decodedMessageBox.open();
				
				}
			}
		});
		decipherButton.setBounds(259, 340, 130, 42);
		
		//This is the action listener for the all guesses button
		Button allGuessesButton = new Button(shlAssignment, SWT.NONE);
		allGuessesButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if((messageTextBox.getText()).equals("")) {
					
					MessageBox invalidInput = new MessageBox(shlAssignment, SWT.ICON_WARNING);
					invalidInput.setMessage("Invalid message. Please try again.");
					invalidInput.open();
					
				} else {
					
					String[] allOptions = guessOptions(messageTextBox.getText());
					
					new listDecipheredWindow(display, allOptions);
					
				}	
					
			}
		});
		
		allGuessesButton.setText("All Guesses");
		allGuessesButton.setBounds(479, 340, 130, 42);
		
		//This is the action listener for the best guess button
		Button bestGuessesButton = new Button(shlAssignment, SWT.NONE);
		bestGuessesButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if((messageTextBox.getText()).equals("")) {
					
					MessageBox invalidInput = new MessageBox(shlAssignment, SWT.ICON_WARNING);
					invalidInput.setMessage("Invalid message. Please try again.");
					invalidInput.open();
					
				} else {
					
					String bestGuess = guessOne(messageTextBox.getText());
					MessageBox bestGuessWindow = new MessageBox(shlAssignment, SWT.ICON_WARNING);
					bestGuessWindow.setMessage("The best guess is: " + bestGuess);
					bestGuessWindow.open();
					
				}
				
			}
		});
		
		bestGuessesButton.setText("Best Guess");
		bestGuessesButton.setBounds(681, 340, 130, 42);

		shlAssignment.open();
		shlAssignment.layout();
		while (!shlAssignment.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
