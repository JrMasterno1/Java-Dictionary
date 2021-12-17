import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddForm extends JPanel {
	JLabel slangLabel;
	JLabel definitionLabel;
	JTextField slangField;
	JTextField definitionField;
	JButton button;
	public AddForm() {
		setPreferredSize(new Dimension(480, 500));
		setLayout(null);
		slangLabel = new JLabel("Slang word");
		slangLabel.setBounds(80, 100, 100, 30);
		definitionLabel = new JLabel("Definition");
		definitionLabel.setBounds(80, 160, 100, 30);
		slangField = new JTextField();
		slangField.setBounds(220, 100, 200, 30);
		definitionField = new JTextField();
		definitionField.setBounds(220, 160, 200, 30);
		button = new JButton("Add");
		button.setBounds(150, 250, 100, 30);
		add(slangLabel);
		add(slangField);
		add(definitionLabel);
		add(definitionField);
		add(button);
		
	}
}
