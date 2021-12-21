import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RandomForm extends JPanel {
	JButton getWord;
	JLabel label;
	JLabel result;
	Dict dict;
	public RandomForm(Dict d) {
		dict = d;
		setLayout(new GridBagLayout());
		getWord = new JButton("Get a word");
		label = new JLabel("Word for today is: ", JLabel.CENTER);
		result = new JLabel();
		result.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		result.setForeground(Color.BLUE);
		
		GridBagConstraints c = new GridBagConstraints ();
		c.insets = new Insets (10, 0, 0, 0);
		add(getWord, c);
		c.gridy = 1;
        add(label, c);
        c.gridy = 2;
        add(result, c);
        
		//add(getWord, BorderLayout.NORTH);
		getWord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				result.setText(dict.randomSlang());
			}
		});
	}
}
