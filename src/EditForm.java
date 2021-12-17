import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditForm extends JPanel {
	JComboBox<String> chooseBox;
	JLabel slangLabel;
	JLabel definitionLabel;
	JTextField slangField;
	JTextField definitionField;
	JButton button;
	public EditForm() {
		setPreferredSize(new Dimension(480, 500));
		setLayout(null);
		chooseBox = new JComboBox<String>();
		chooseBox.setBounds(150, 50, 100, 30);
		slangLabel = new JLabel("Slang word");
		slangLabel.setBounds(80, 150, 100, 30);
		definitionLabel = new JLabel("Definition");
		definitionLabel.setBounds(80, 210, 100, 30);
		slangField = new JTextField();
		slangField.setBounds(220, 150, 200, 30);
		definitionField = new JTextField();
		definitionField.setBounds(220, 210, 200, 30);
		button = new JButton("Edit");
		button.setBounds(150, 300, 100, 30);
		add(slangLabel);
		add(slangField);
		add(definitionLabel);
		add(definitionField);
		add(button);
		add(chooseBox);
		
	}
}
