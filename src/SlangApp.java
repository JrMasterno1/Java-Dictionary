import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SlangApp implements ActionListener{
	JFrame frame;
	JTabbedPane tabbedPane;
	SlangForm slangPanel;
	JPanel findDefiPanel;
	
	public SlangApp() {
		Dict dict = new Dict();
		frame = new JFrame("Slang app");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		slangPanel = new SlangForm(dict);
		SearchForm searchForm = new SearchForm(dict);
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Slang Form", slangPanel);
		tabbedPane.addTab("Search definition", searchForm);
		tabbedPane.addTab("Game Form", new GameForm(dict));
		tabbedPane.addTab("On this day", new RandomForm(dict));
		
		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
