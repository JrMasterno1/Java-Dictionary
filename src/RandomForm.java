import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RandomForm extends JPanel {
	JButton getWord;
	JLabel label;
	JLabel slang;
	Dict dict;
	JLabel definition;
	public RandomForm(Dict d) {
		dict = d;
		setLayout(new GridBagLayout());
		getWord = new JButton("Get a word");
		label = new JLabel("Word for today is: ", JLabel.CENTER);
		slang = new JLabel();
		slang.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		slang.setForeground(Color.BLUE);
		definition = new JLabel();
		definition.setFont(new Font("Helvetica Neue", Font.ITALIC, 20));
		
		
		
		GridBagConstraints c = new GridBagConstraints ();
		c.insets = new Insets (10, 0, 0, 0);
		add(getWord, c);
		c.gridy = 1;
        add(label, c);
        c.gridy = 2;
        add(slang, c);
        c.gridy++;
        add(definition, c);
        
		//add(getWord, BorderLayout.NORTH);
		getWord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				slang.setText(dict.randomSlang());
				String defs = "";
				for (Iterator iterator = dict.searchSlang(slang.getText()).iterator(); iterator.hasNext();) {
					String str = (String) iterator.next();
					defs += str + ", ";
				}
				definition.setText(defs.substring(0, defs.length()-2));
			}
		});
	}
}
