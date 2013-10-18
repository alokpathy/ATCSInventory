import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InventoryInterface {
	
	Inventory inventory = new Inventory();
	JFrame frame;
	JPanel cards;
	JButton add = new JButton("ADD");
	JButton remove = new JButton("REMOVE");
	JButton edit = new JButton("EDIT");
	JButton find = new JButton("FIND");
	JButton findMax = new JButton("FINDMAX");
	JButton display = new JButton("DISPLAY");
	
	JTextField five1 = new JTextField(5);
	JTextField five2 = new JTextField(5);
	JTextField ten1 = new JTextField(10);
	JTextField ten2 = new JTextField(10);
	JTextField count1 = new JTextField(5);
	JTextField count2 = new JTextField(5);
	
	public InventoryInterface() {
		
		frame = new JFrame("Inventory");
		
		final String ADD = "Add Item";
		final String REMOVE = "Remove Item";
		final String EDIT = "Edit Item";
		final String FIND = "Find Item";
		final String FINDMAX = "Find Max";
		final String DISPLAY = "Display";
		
		JPanel card1 = new JPanel();
		card1.add(new JLabel("ID: "));
		card1.add(five1);
		card1.add(new JLabel("Count: "));
		card1.add(count1);
		card1.add(add);
		
		JPanel card2 = new JPanel();
		card2.add(new JLabel("ID: "));
		card2.add(ten1);
		card2.add(remove);
		
		JPanel card3 = new JPanel();
		card3.add(new JLabel("ID: "));
		card3.add(five2);
		card3.add(new JLabel("Count: "));
		card3.add(count2);
		card3.add(edit);
		
		JPanel card4 = new JPanel();
		card4.add(new JLabel("ID: "));
		card4.add(ten2);
		card4.add(find);
		
		JPanel card5 = new JPanel();
		card5.add(findMax);
		
		JPanel card6 = new JPanel();
		card6.add(display);
		
		cards = new JPanel(new CardLayout());
		cards.add(card1, ADD);
		cards.add(card2, REMOVE);
		cards.add(card3, EDIT);
		cards.add(card4, FIND);
		cards.add(card5, FINDMAX);
		cards.add(card6, DISPLAY);
		
		JPanel comboBoxPane = new JPanel();
		String comboBoxItems[] = {ADD, REMOVE, EDIT, FIND, FINDMAX, DISPLAY};
		JComboBox comboBox = new JComboBox(comboBoxItems);
		ActionHandler actionHandler = new ActionHandler();
		ItemHandler itemHandler = new ItemHandler();
		
		comboBox.setEditable(false);
		comboBox.addItemListener(itemHandler);
		
		//add.addActionListener(actionHandler);
		add.addActionListener(actionHandler);
		remove.addActionListener(actionHandler);
		edit.addActionListener(actionHandler);
		find.addActionListener(actionHandler);
		findMax.addActionListener(actionHandler);
		display.addActionListener(actionHandler);
		
		comboBoxPane.add(comboBox);
		
		frame.add(comboBoxPane, BorderLayout.PAGE_START);
		frame.add(cards, BorderLayout.CENTER);
		
		Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setPreferredSize(new Dimension(400, 150));
		Dimension windowSize = new Dimension(frame.getPreferredSize());
		
		int windowLeft = screenSize.width / 2 - windowSize.width / 2;
		int windowTop = screenSize.height / 2 - windowSize.height / 2;
		
		frame.pack();
		frame.setLocation(windowLeft, windowTop);
		frame.setVisible(true);
	
	}
	
	public static void main(String[] args) {
		new InventoryInterface();
	}

	private class ActionHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == add){
				int newID = Integer.parseInt(five1.getText());
				int newCount = Integer.parseInt(count1.getText());
				inventory.addNew(newID, newCount);
				inventory.update();
			}
			
			else if(e.getSource() == remove){
				int id = Integer.parseInt(ten1.getText());
				inventory.removeItem(id);
				inventory.update();
			}
			
			else if(e.getSource() == edit){
				int newID = Integer.parseInt(five2.getText());
				int newCount = Integer.parseInt(count2.getText());
				inventory.edit(newID, newCount);	
				inventory.update();
			}
			
			else if(e.getSource() == find){
				int id = Integer.parseInt(ten2.getText());
				int count = inventory.findCount(id);
				
				if(count == Integer.MIN_VALUE) {
					JOptionPane.showMessageDialog(frame, "ID not present", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(frame, "ID: " + id + "\nCount: " + count);
			}
			
			else if(e.getSource() == findMax){
				ArrayList<InventoryItem> maxItems = inventory.getMaxCountItems();
				StringBuilder builder = new StringBuilder();
				for(int i = 0; i < maxItems.size(); i++) {
					builder.append("ID: " + maxItems.get(i).getItemID() + "\nCount: " + maxItems.get(i).getItemCount() + "\n\n");
				}
				
				if(maxItems.size() == 0) {
					JOptionPane.showMessageDialog(frame, "No items");
				}
				else {
					JOptionPane.showMessageDialog(frame, builder.toString());
				}
			}
			
			else if(e.getSource() == display){
				inventory.display();
			}
			
		}
	}
	
	private class ItemHandler implements ItemListener {
		
		public void itemStateChanged(ItemEvent e) {			
			CardLayout cl = (CardLayout)(cards.getLayout());
		    cl.show(cards, (String)e.getItem());
		}
		
	}
	
}
