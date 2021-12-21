import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
	JTextField definitionField;
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
	public GameForm(Dict d) {
		dict = d;
		setPreferredSize(new Dimension(410, 500));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		slangGame = new JButton("Create new slang game");
		definitionGame = new JButton("Create new definition game");
		createPanel = new JPanel();
		createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.X_AXIS));
		//createPanel.setPreferredSize(new Dimension(300, 100));
		createPanel.add(slangGame);
		createPanel.add(definitionGame);
		
		word = new JLabel();
		JPanel answerPanel = new JPanel();
		answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.X_AXIS));
		A = new JRadioButton();
		B = new JRadioButton();
		C = new JRadioButton();
		D = new JRadioButton();
		answerPanel.add(A);
		answerPanel.add(B);
		answerPanel.add(C);
		answerPanel.add(D);
		
		add(createPanel);
		add(word);
		add(answerPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
