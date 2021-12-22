import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.DirectoryNotEmptyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GameForm extends JPanel implements ActionListener {
	JLabel word;
	JButton searchButton;
	Dict dict;
	JList resultList;
	JScrollPane resultPane;
	DefaultListModel<String> listModel;
	JButton deleteButton;
	JButton slangGame;
	JButton definitionGame;
	JPanel createPanel;
	JRadioButton A;
	JRadioButton B;
	JRadioButton C;
	JRadioButton D;
	JLabel messageLabel;
	ButtonGroup answerGroup;
	public GameForm(Dict d) {
		dict = d;
		setPreferredSize(new Dimension(410, 500));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints ();
		c.insets = new Insets (10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 0;
		
		slangGame = new JButton("Create new slang game");
		slangGame.addActionListener(this);
		definitionGame = new JButton("Create new definition game");
		createPanel = new JPanel();
		createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.X_AXIS));
		//createPanel.setPreferredSize(new Dimension(300, 100));
		createPanel.add(slangGame);
		createPanel.add(definitionGame);
		
		word = new JLabel();
		JPanel answerPanel = new JPanel();
		answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.X_AXIS));
		answerGroup = new ButtonGroup();
		A = new JRadioButton();
		B = new JRadioButton();
		C = new JRadioButton();
		D = new JRadioButton();
		A.addActionListener(this);
		B.addActionListener(this);
		C.addActionListener(this);
		D.addActionListener(this);
		answerGroup.add(A);
		answerGroup.add(B);
		answerGroup.add(C);
		answerGroup.add(D);
		
		answerPanel.add(A);
		answerPanel.add(B);
		answerPanel.add(C);
		answerPanel.add(D);
		
		word = new JLabel("", JLabel.CENTER);
		word.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		messageLabel = new JLabel("", JLabel.CENTER);
		messageLabel.setFont(new Font("Helvetica Neue", Font.ITALIC, 15));
		
		add(createPanel, c);
		c.gridy = 1;
		add(word, c);
		c.gridy = 2;
		add(answerPanel, c);
		c.gridy = 3;
		add(messageLabel, c);
		
	}
	String key = "";
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == slangGame) {
			
			messageLabel.setText("");
			Random r = new Random();
			HashMap<String, HashSet<String>> myGame = dict.slangGame();
			
			ArrayList<String> slangs = new ArrayList<String>(myGame.keySet());
			key = slangs.get(r.nextInt(slangs.size()));
			word.setText("Slang: " + key);
			int count = 1;
			for (Map.Entry<String, HashSet<String>> entry : myGame.entrySet()) {
				HashSet<String> value = entry.getValue();
				if (count == 1) {					
					A.setText(value.iterator().next());
				}
				else if (count == 2) {
					B.setText(value.iterator().next());
				}
				else if (count == 3) {
					C.setText(value.iterator().next());
				}
				else {
					D.setText(value.iterator().next());
				}
				count++;
			}
		}
		if(A.isSelected()) {
			
			messageLabel.setText("");
			if (A.getText() == dict.searchSlang(key).iterator().next()) {
				messageLabel.setText("correct");
				messageLabel.setForeground(Color.GREEN);
			}
			else {
				messageLabel.setForeground(Color.RED);
				messageLabel.setText("Incorrect");
			}
		}
		if(B.isSelected()) {
			messageLabel.setText("");
			if (B.getText() == dict.searchSlang(key).iterator().next()) {
				messageLabel.setText("correct");
				messageLabel.setForeground(Color.GREEN);
			}
			else {
				messageLabel.setForeground(Color.RED);
				messageLabel.setText("Incorrect");
			}
		}
		if(C.isSelected()) {
			messageLabel.setText("");
			if (C.getText() == dict.searchSlang(key).iterator().next()) {
				messageLabel.setText("correct");
				messageLabel.setForeground(Color.GREEN);
			}
			else {
				messageLabel.setForeground(Color.RED);
				messageLabel.setText("Incorrect");
			}
		}
		if(D.isSelected()) {
			messageLabel.setText("");
			if (D.getText() == dict.searchSlang(key).iterator().next()) {
				messageLabel.setText("correct");
				messageLabel.setForeground(Color.GREEN);
			}
			else {
				messageLabel.setForeground(Color.RED);
				messageLabel.setText("Incorrect");
			}
		}
	}

}
