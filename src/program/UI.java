package program;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import tagger.HMMTagger;
import tagger.IPOSTagger;

@SuppressWarnings("serial")
public class UI extends JFrame implements ActionListener{

	private JTextArea tinput, toutput;
	private JButton baction;
	private IPOSTagger tagger;
	
	public UI() {
		super("Gán nhãn câu");
		tinput = new JTextArea();
		tinput.setBorder(BorderFactory.createTitledBorder("Nhập câu"));
		
		toutput = new JTextArea();
		toutput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Kết quả"));
		toutput.setEditable(false);
		
		baction = new JButton("Bắt đầu");
		baction.addActionListener(this);
		baction.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.add(tinput);
		this.add(Box.createRigidArea(new Dimension(20, 10)));
		this.add(baction);
		this.add(Box.createRigidArea(new Dimension(10, 10)));
		this.add(toutput);
		this.add(Box.createRigidArea(new Dimension(20, 10)));
		
		this.setSize(350, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tagger = new HMMTagger("data");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tests = tinput.getText();
		
		ArrayList<String> tokens = getToken(tests);
		String[] tags = tagger.parse(tests);
		
		String out="";
		for (int i=0; i<tokens.size(); i++) {
			out += tokens.get(i)+"/"+tags[i]+" ";
		}
		toutput.setText(out);
	}
	
	private ArrayList<String> getToken(String s) {
		ArrayList<String> list = new ArrayList<>();
		int start=-1, end=0;
		String sub;
		
		while (end < s.length()) {
			if (s.charAt(end) == ' ' && start != end) {
				sub = s.substring(start+1, end);
				list.add(sub);
				System.out.println(sub);
				start = end;
			}
			end++;
		}
		
		sub = s.substring(start+1);
		System.out.println(sub);
		list.add(sub);
		
		return list;
	}

	public static void main(String[] args) {
		UI ui = new UI();
		ui.setVisible(true);
	}
}
