import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SlangApp implements ActionListener{
	JFrame frame;
	JTabbedPane tabbedPane;
	AddForm addPanel;
	JPanel editPanel;
	JPanel searchSlangPanel;
	JPanel findDefiPanel;
	
	public SlangApp() {
		Dict dict = new Dict();
		frame = new JFrame("Slang app");
		frame.setSize(500,700);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		addPanel = new AddForm(dict);
		editPanel = new EditForm();
		tabbedPane = new JTabbedPane();
		searchSlangPanel = new SearchForm(dict);
		tabbedPane.addTab("Search slang", searchSlangPanel);
		tabbedPane.addTab("Add slang", addPanel);
		tabbedPane.addTab("Edit slang", editPanel);
		
		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
