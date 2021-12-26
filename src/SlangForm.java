import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SlangForm extends JPanel implements ActionListener {
	JLabel slangLabel;
	JLabel definitionLabel;
	JTextField slangField;
	JTextField definitionField;
	JButton addButton;
	Dict dict;
	JButton editButton;
	JButton clearButton;
	JPanel addForm;
	JPanel searchForm;
	JButton searchButton;
	JButton deleteButton;
	JList resultList;
	JScrollPane resultPane;
	JLabel searchSlangLabel;
	JTextField searchSlangField;
	DefaultListModel<String> listModel;
	JPanel historyPanel;
	JScrollPane historyPane;
	JTextArea historyArea;
	public SlangForm(Dict d) {
		dict = d;
		
		setLayout(new BorderLayout());
		
		addForm = new JPanel();
		addForm.setPreferredSize(new Dimension(400, 500));
		addForm.setLayout(null);
		slangLabel = new JLabel("Slang word");
		slangLabel.setBounds(60, 100, 80, 30);
		definitionLabel = new JLabel("Definition");
		definitionLabel.setBounds(60, 160, 100, 30);
		slangField = new JTextField();
		slangField.setBounds(160, 100, 180, 30);
		definitionField = new JTextField();
		definitionField.setBounds(160, 160, 180, 30);
		addButton = new JButton("Add");
		addButton.setBounds(60, 250, 80, 30);
		addButton.addActionListener(this);
		
		editButton = new JButton("Edit");
		editButton.setBounds(160, 250, 80, 30);
		editButton.addActionListener(this);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(260, 250, 80, 30);
		clearButton.addActionListener(this);
		addForm.add(slangLabel);
		addForm.add(slangField);
		addForm.add(definitionLabel);
		addForm.add(definitionField);
		addForm.add(addButton);
		addForm.add(editButton);
		addForm.add(clearButton);
		addForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(addForm, BorderLayout.CENTER);
		
		searchForm = new JPanel();
		searchForm.setPreferredSize(new Dimension(400, 0));
		searchForm.setLayout(null);
		searchSlangLabel = new JLabel("Slang word");
		searchSlangLabel.setBounds(10, 100, 80, 30);
		searchSlangField = new JTextField();
		searchSlangField.setBounds(90, 100, 150, 30);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		searchButton.setBounds(260, 100, 90, 30);
		
		listModel = new DefaultListModel<String>();
		
		resultList = new JList(listModel);
		resultList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				definitionField.setText((String) resultList.getSelectedValue());
				slangField.setText(searchSlangField.getText());
			}
		});
		resultPane = new JScrollPane(resultList);
		resultPane.setBounds(90, 160, 150, 200);
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(90, 390, 150, 30);
		deleteButton.addActionListener(this);
		
		searchForm.add(searchSlangLabel);
		searchForm.add(searchSlangField);
		searchForm.add(resultPane);
		searchForm.add(searchButton);
		searchForm.add(deleteButton);
		searchForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(searchForm, BorderLayout.WEST);
		
		
		historyPanel = new JPanel();
		historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
		historyArea = new JTextArea();
		//historyArea.setText("Time\t        |  Search history");
		JLabel historyLabel = new JLabel("Search history");
		//historyPanel.add(historyArea);
		// historyArea.setPreferredSize(new Dimension(0,200));
		historyArea.setEditable(false);
		historyPane = new JScrollPane(historyArea);
		historyPane.setPreferredSize(new Dimension(500, 100));
		historyPanel.add(historyLabel);
		historyPanel.add(historyPane);
		LinkedList<String> history = dict.getHistory();
		for (Iterator iterator = history.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			historyArea.append("\n" + string);
			
		}
		add(historyPanel, BorderLayout.SOUTH);
	}
	public void clearHistory() {
		historyArea.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			String slang = slangField.getText();
			String definition = definitionField.getText();
			if(dict.hasSlang(slang)) {
				String[] options = {"Duplicate", "Overwrite"};
				JOptionPane pane = new JOptionPane();
				int choice = pane.showOptionDialog(null, "This slang has already existed", "Existed slang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if(choice == 0) {
					dict.AddDefinition(slang, definition);
					if(slang.equals(searchSlangField.getText())) {						
						listModel.addElement(definition);
					}
				}
				else {
					dict.AddNew(slang, definition);
					if(slang.equals(searchSlangField.getText())) {
						listModel.removeAllElements();
						listModel.addElement(definition);
					}
				}
				
			}
			else {
				dict.AddNew(slang, definition);
				JOptionPane.showMessageDialog(null, "Added successfully");
			}
		}
		else if (e.getSource() == editButton) {
			if (dict.EditSlang(searchSlangField.getText(), (String) resultList.getSelectedValue(), definitionField.getText())) {
				listModel.addElement(definitionField.getText());
				listModel.removeElement(resultList.getSelectedValue());
			}
			else {
				JOptionPane.showMessageDialog(addButton, "Slang word not found");
			}
		}
		else if (e.getSource() == deleteButton) {
			int choice = JOptionPane.showConfirmDialog(deleteButton, "This slang will remove from dictionary", "Delete", JOptionPane.YES_NO_OPTION);
			if(choice == 0) {
				dict.deleteSlang(searchSlangField.getText());
				listModel.removeAllElements();
				slangField.setText("");
				searchSlangField.setText("");
			}
		}
		else if (e.getSource() == searchButton) {
			listModel.removeAllElements();
			HashSet<String> defSet = dict.searchSlang(searchSlangField.getText());
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			Date date = new Date();  
			String time = formatter.format(date).toString();
			String history = time + " |   " + searchSlangField.getText();
			if (defSet != null) {				
				for(String s : defSet) {
					listModel.addElement(s.strip());
				}
			}
			else {
				JOptionPane.showMessageDialog(resultList, "Slang is not in dictionary");
			}
			dict.AddHistory(history);
			historyArea.append("\n" + history);
		}
		else if (e.getSource() == clearButton) {
			slangField.setText("");
			definitionField.setText("");
		}
	}
}
