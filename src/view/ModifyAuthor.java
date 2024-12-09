	package view;
	import java.awt.BorderLayout;
	import java.awt.FlowLayout;
	import java.util.ArrayList;
	
	import javax.swing.JButton;
	import javax.swing.JDialog;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	
	import controller.AuthorController;
	import model.Author;
	
	import javax.swing.JLabel;
	import java.awt.Font;
	import javax.swing.JTextField;
	import javax.swing.JList;
	import javax.swing.JOptionPane;
	import javax.swing.JComboBox;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	
	public class ModifyAuthor extends JDialog implements ActionListener {
	
	    private final JPanel contentPanel = new JPanel();
	    private JTextField textField;
	    private JTextField textField_1;
	    private JTextField textField_2;
	    private JTextField textField_3;
	
	    // JDialog window to show the insert error
	    JDialog insertBookWindow;
	    // okButton declaration
	    JButton okButton;
	    // cancelButton declaration
	    JButton cancelButton;
	    // combo box String declaration
	    JComboBox<String> comboBox;
	    
	    // Controller for handling author-related operations
	    private AuthorController authorController;
	    private Author selectedAuthor;

	    /**
	     * Create the dialog.
	     */
	    public ModifyAuthor() {
	        authorController = new AuthorController();

	        // Set up dialog window properties
	        setVisible(true);
	        setTitle("ModifyAuthor");
	        setBounds(100, 100, 605, 302);
	        getContentPane().setLayout(new BorderLayout());
	        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	        getContentPane().add(contentPanel, BorderLayout.CENTER);
	        contentPanel.setLayout(null);
	
	        {
	            JLabel lblNewLabel = new JLabel("Name:");
	            lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
	            lblNewLabel.setBounds(10, 61, 85, 39);
	            contentPanel.add(lblNewLabel);
	        }
	        {
	            JLabel lblNewLabel = new JLabel("Surname:");
	            lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
	            lblNewLabel.setBounds(10, 97, 135, 39);
	            contentPanel.add(lblNewLabel);
	        }
	        {
	            JLabel lblSecondSurname = new JLabel("Second Surname:");
	            lblSecondSurname.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
	            lblSecondSurname.setBounds(10, 139, 180, 39);
	            contentPanel.add(lblSecondSurname);
	        }
	        {
	            JLabel lblNewLabel_1_1 = new JLabel("Country:");
	            lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
	            lblNewLabel_1_1.setBounds(10, 180, 85, 39);
	            contentPanel.add(lblNewLabel_1_1);
	        }
	        {
	            textField = new JTextField();
	            textField.setColumns(10);
	            textField.setBounds(181, 65, 380, 30);
	            contentPanel.add(textField);
	        }
	        {
	            textField_2 = new JTextField();
	            textField_2.setColumns(10);
	            textField_2.setBounds(181, 106, 380, 30);
	            contentPanel.add(textField_2);
	        }
	        {
	            textField_3 = new JTextField();
	            textField_3.setColumns(10);
	            textField_3.setBounds(181, 189, 380, 30);
	            contentPanel.add(textField_3);
	        }
	
	        JList<String> list = new JList<>();
	        list.setBounds(133, 41, 1, 1);
	        contentPanel.add(list);
	
	        // Retrieve authors from the controller
	        authorController = new AuthorController();
	        ArrayList<Author> authors = authorController.getAuthor();
	
	        // ComboBox to display the authors
	        comboBox = new JComboBox<>();
	        comboBox.setToolTipText("Authors");
	        comboBox.setBounds(209, 11, 352, 22);
	        contentPanel.add(comboBox);
	
	        // Add author names to the combo box
	        for (Author author : authors) {
	            comboBox.addItem(author.getName_auth());
	        }
	
	        // Register an ActionListener for the combo box
	        comboBox.addActionListener(this);
	
	        // Label for the registered authors
	        JLabel lblAuthorsRegistered = new JLabel("Authors Registered:");
	        lblAuthorsRegistered.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
	        lblAuthorsRegistered.setBounds(10, 7, 189, 30);
	        contentPanel.add(lblAuthorsRegistered);
	
	        // END OF GRAPHICAL ELEMENTS
	        {
	            JPanel buttonPane = new JPanel();
	            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	            getContentPane().add(buttonPane, BorderLayout.SOUTH);
	            {
	                // Ok Button to submit all inserted data to the database
	                okButton = new JButton("OK");
	                okButton.setActionCommand("OK");
	                buttonPane.add(okButton);
	                getRootPane().setDefaultButton(okButton);
	                okButton.addActionListener(this);
	            }
	            {
	                // Cancel Button to close the JDialog window
	                cancelButton = new JButton("Cancel");
	                cancelButton.setActionCommand("Cancel");
	                buttonPane.add(cancelButton);
	                getRootPane().setDefaultButton(cancelButton);
	                cancelButton.addActionListener(this);
	            }
	        }
	    }
	
	    // Handle action events
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == comboBox) {
	        	
	        	 String selectedAuthorName = (String) comboBox.getSelectedItem();
	             selectedAuthor = authorController.getAuthorByName(selectedAuthorName);
	            // Get the selected author

	            // Retrieve author data from the controller
	            selectedAuthor = authorController.getAuthorByName(selectedAuthorName);
	
	            
	            // Set the author's data in the corresponding text fields
	            if (selectedAuthor != null) {
	                String authorName = selectedAuthor.getName_auth();	
	                textField.setText(selectedAuthor.getName_auth());
	                textField_2.setText(selectedAuthor.getSurname_auth());
	                textField_3.setText(selectedAuthor.getCountry());
	            } else {
	                textField.setText("");
	                textField_2.setText("");
	                textField_3.setText("");
	                JOptionPane.showMessageDialog(null, "Author not found", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	            
	        }else if (e.getSource() == okButton) {
	            // When OK button is pressed, retrieve user input from text fields
				String name_auth = textField.getText();
				String surname_auth = textField_2.getText();
				String country = textField_3.getText();
	
				
	            // Validate the inputs
				if (name_auth == "" || surname_auth == "" || country == "" || country.length() < 2
						|| name_auth.length() < 2) {
	                // Show an error message if inputs are invalid
					JOptionPane.showMessageDialog(insertBookWindow, "ERROR: WRONG INPUTS, PLEASE TRY AGAIN",
							"Insertion Error", JOptionPane.ERROR_MESSAGE);
	
				} else {
	                // Modify the author's data using the AuthorController
					boolean success = authorController.modifyExistentAuthor(selectedAuthor.getName_auth(), name_auth, surname_auth, country);
		            if (success) {
		                JOptionPane.showMessageDialog(null, "Author data updated successfully.");
		            } else {
		                JOptionPane.showMessageDialog(null, "Failed to update author data.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
					
	                // Close the window when finished
					dispose();
	
				}
			}else if (e.getSource() == cancelButton) {
				
	            // Close the window when Cancel button is pressed
				dispose();
			}
	    }
	}
