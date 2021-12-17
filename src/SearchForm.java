import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractListModel;
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
	JLabel slangLabel;
	JTextField slangField;
	JButton searchButton;
	Dict dict;
	JList resultList;
	JScrollPane resultPane;
	DefaultListModel<String> listModel;
	JTextField editField;
	JButton editButton;
	JButton deleteButton;
	public SearchForm(Dict d) {
		dict = d;
		setPreferredSize(new Dimension(480, 500));
		setLayout(null);
		slangLabel = new JLabel("Slang word");
		slangLabel.setBounds(40, 100, 100, 30);
		slangField = new JTextField();
		slangField.setBounds(150, 100, 200, 30);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		searchButton.setBounds(370, 100, 100, 30);
		
		listModel = new DefaultListModel<String>();
		
		resultList = new JList(listModel);
		resultList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				editField.setText((String) resultList.getSelectedValue());
			}
		});
		resultPane = new JScrollPane(resultList);
		resultPane.setBounds(55, 160, 150, 200);
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(55, 390, 150, 30);
		deleteButton.addActionListener(this);
		
		editField = new JTextField();
		editField.setBounds(230, 180, 150, 30);
		editButton = new JButton("Edit");
		editButton.setBounds(255, 250, 100, 30);
		editButton.addActionListener(this);
		
		
		
		add(slangLabel);
		add(slangField);
		add(resultPane);
		add(searchButton);
		add(deleteButton);
		add(editField);
		add(editButton);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			listModel.removeAllElements();
			HashSet<String> defSet = dict.findBySlang(slangField.getText());
			if (defSet != null) {				
				for(String s : defSet) {
					listModel.addElement(s);
				}
			}
			else {
				JOptionPane.showMessageDialog(resultList, "Slang is not in dictionary");
			}
		}
		else if (e.getSource() == editButton) {
			dict.EditSlang(slangField.getText(), (String) resultList.getSelectedValue(), editField.getText());
			listModel.addElement(editField.getText());
			listModel.removeElement(resultList.getSelectedValue());
			editField.setText("");
		}
		else if (e.getSource() == deleteButton) {
			int choice = JOptionPane.showConfirmDialog(deleteButton, "This slang will remove from dictionary");
			if(choice == 0) {
				dict.deleteSlang(slangField.getText());
				editField.setText("");
				listModel.removeAllElements();
				slangField.setText("");
			}
			else {
				System.out.println("No delete");
			}
		}
		
	}
}
