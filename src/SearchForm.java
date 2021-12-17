import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchForm extends JPanel implements ActionListener {
	JLabel slangLabel;
	JTextField slangField;
	JButton button;
	JLabel resultLabel;
	Dict dict;
	public SearchForm(Dict d) {
		dict = d;
		setPreferredSize(new Dimension(480, 500));
		setLayout(null);
		slangLabel = new JLabel("Slang word");
		slangLabel.setBounds(80, 100, 100, 30);
		slangField = new JTextField();
		slangField.setBounds(220, 100, 200, 30);
		resultLabel = new JLabel();
		resultLabel.setBounds(150, 160, 150, 30);
		button = new JButton("Search");
		button.addActionListener(this);
		button.setBounds(150, 250, 100, 30);
		add(slangLabel);
		add(slangField);
		add(resultLabel);
		add(button);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			Set<String> defSet = dict.findBySlang(slangField.getText());
			String res = "";
			for(String s : defSet) {
				res += s + " ";
			}
			resultLabel.setText(res);
		}
		
	}
}
