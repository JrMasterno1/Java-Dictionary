import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddForm extends JPanel implements ActionListener {
	JLabel slangLabel;
	JLabel definitionLabel;
	JTextField slangField;
	JTextField definitionField;
	JButton button;
	Dict dict;
	public AddForm(Dict d) {
		dict = d;
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
		button.addActionListener(this);
		add(slangLabel);
		add(slangField);
		add(definitionLabel);
		add(definitionField);
		add(button);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			String slang = slangField.getText();
			String definition = definitionField.getText();
			if(dict.hashSlang(slang)) {
				String[] options = {"Add new definition", "Overwrite"};
				JOptionPane pane = new JOptionPane();
				int choice = pane.showOptionDialog(null, "This slang has already existed", "Existed slang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if(choice == 0) {
					dict.AddDefinition(slang, definition);
				}
				else {
					System.out.println("else");
				}
				
			}
		}
	}
}
