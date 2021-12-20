import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SearchForm extends JPanel implements ActionListener {
	JLabel definitionLabel;
	JTextField definitionField;
	JButton searchButton;
	Dict dict;
	JList resultList;
	JScrollPane resultPane;
	DefaultListModel<String> listModel;
	JButton deleteButton;
	public SearchForm(Dict d) {
		dict = d;
		setPreferredSize(new Dimension(410, 500));
		setLayout(new BorderLayout());
		
		JPanel searchPanel = new JPanel();
		searchPanel.setPreferredSize(new Dimension(300, 100));
		
		definitionLabel = new JLabel("Definition word");
		//definitionLabel.setBounds(10, 100, 80, 30);
		definitionField = new JTextField();
		definitionField.setPreferredSize(new Dimension(150, 30));
		//definitionField.setBounds(90, 100, 150, 30);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		searchPanel.add(definitionLabel);
		searchPanel.add(definitionField);
		searchPanel.add(searchButton);
		//searchButton.setBounds(260, 100, 90, 30);
		
		listModel = new DefaultListModel<String>();
		
		resultList = new JList(listModel);
		resultPane = new JScrollPane(resultList);
		resultPane.setMaximumSize(new Dimension(200, 100));
		
		JPanel blankWest = new JPanel();
		blankWest.setPreferredSize(new Dimension(200,300));
		JPanel blankEast = new JPanel();
		blankEast.setPreferredSize(new Dimension(200,300));
		JPanel blankSOUTH = new JPanel();
		blankSOUTH.setPreferredSize(new Dimension(200,300));
		add(searchPanel, BorderLayout.NORTH);
		add(resultPane, BorderLayout.CENTER);
		add(blankSOUTH, BorderLayout.SOUTH);
		add(blankWest, BorderLayout.WEST);
		add(blankEast, BorderLayout.EAST);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			listModel.removeAllElements();
			HashSet<String> defSet = dict.searchSlang(definitionField.getText());
			if (defSet != null) {				
				for(String s : defSet) {
					listModel.addElement(s);
				}
			}
			else {
				JOptionPane.showMessageDialog(resultList, "Slang is not in dictionary");
			}
		}
		
	}
}
