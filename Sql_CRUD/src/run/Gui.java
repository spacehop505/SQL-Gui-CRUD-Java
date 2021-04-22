package run;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import msql.Data;
import msql.MySql;

public class Gui {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel defaultTableModel;
	private JTextField name1;
	private JTextField price1;
	private JTextField quantity1;
	private JTextField id3;
	private JTextField id4;
	private JTextField name2;
	private JTextField price2;
	private JTextField quantity2;
	private JLabel warning1;
	private JLabel warning2;
	private JLabel warning4;
	private JTextField id2;

	public Gui() {
		frame = new JFrame();
		frame.setBounds(100, 100, 873, 669);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(228, 107, 400, 250);
		frame.getContentPane().add(scrollPane);

		defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Price");
		defaultTableModel.addColumn("Quantity");
		table = new JTable(defaultTableModel);

		scrollPane.setViewportView(table);

		JButton createBtn = new JButton("Create");
		createBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					warning1.setText("");
					String name = name1.getText();
					Double price = Double.parseDouble(price1.getText());
					Integer quantity = Integer.parseInt(quantity1.getText());

					Data dataObj = new Data(name, price, quantity);
					MySql sqlObj = new MySql();
					sqlObj.insertData(dataObj);
					sqlObj.closeSQL();

					name1.setText("");
					price1.setText("");
					quantity1.setText("");
					table();
				} catch (NumberFormatException e1) {
					warning1.setText("NumberFormatException");
				} catch (SQLException e1) {
					warning1.setText("SQLException");
				}
			}
		});
		createBtn.setBounds(20, 220, 180, 30);
		frame.getContentPane().add(createBtn);

		name1 = new JTextField();
		name1.setBounds(100, 100, 100, 30);
		frame.getContentPane().add(name1);
		name1.setColumns(10);

		price1 = new JTextField();
		price1.setColumns(10);
		price1.setBounds(100, 140, 100, 30);
		frame.getContentPane().add(price1);

		quantity1 = new JTextField();
		quantity1.setColumns(10);
		quantity1.setBounds(100, 180, 100, 30);
		frame.getContentPane().add(quantity1);

		JLabel lblname1 = new JLabel("Name:");
		lblname1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname1.setBounds(20, 100, 80, 30);
		frame.getContentPane().add(lblname1);

		JLabel lnlprice1 = new JLabel("Price:");
		lnlprice1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lnlprice1.setBounds(20, 140, 80, 30);
		frame.getContentPane().add(lnlprice1);

		JLabel lblQuantity1 = new JLabel("Quantity:");
		lblQuantity1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantity1.setBounds(20, 180, 80, 30);
		frame.getContentPane().add(lblQuantity1);

		JLabel lblCreateFruit = new JLabel("Create");
		lblCreateFruit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCreateFruit.setBounds(20, 70, 180, 30);
		frame.getContentPane().add(lblCreateFruit);

		JLabel lblSelectAFruit = new JLabel("Data");
		lblSelectAFruit.setForeground(new Color(0, 0, 0));
		lblSelectAFruit.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblSelectAFruit.setBounds(393, 70, 62, 30);
		frame.getContentPane().add(lblSelectAFruit);

		JLabel lblDeleteFruit = new JLabel("Delete");
		lblDeleteFruit.setForeground(new Color(0, 0, 0));
		lblDeleteFruit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDeleteFruit.setBounds(659, 70, 180, 30);
		frame.getContentPane().add(lblDeleteFruit);

		JLabel lblname1_1 = new JLabel("ID:");
		lblname1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname1_1.setBounds(659, 100, 80, 30);
		frame.getContentPane().add(lblname1_1);

		id3 = new JTextField();
		id3.setColumns(10);
		id3.setBounds(739, 100, 100, 30);
		frame.getContentPane().add(id3);

		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(id3.getText());

					Data dataObj = new Data(id);
					MySql sqlObj = new MySql();
					sqlObj.deleteData(dataObj);
					sqlObj.closeSQL();

					id3.setText("");
					table();
				} catch (NumberFormatException e1) {
					warning1.setText("NumberFormatException");
				} catch (SQLException e1) {
					warning1.setText("SQLException");
				}
			}
		});
		deleteBtn.setBounds(659, 140, 180, 30);
		frame.getContentPane().add(deleteBtn);

		JButton searchBtn = new JButton("Search");
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				defaultTableModel.setRowCount(0);
				try {
					int id = Integer.parseInt(id4.getText());
					MySql sqlObject = new MySql();
					ResultSet rs = sqlObject.printData();
					while (rs.next()) {
						if (rs.getInt(1) == id) {
							Object rowData[] = new Object[4];
							rowData[0] = rs.getString(1);
							rowData[1] = rs.getString(2);
							rowData[2] = rs.getString(3);
							rowData[3] = rs.getString(4);
							defaultTableModel.addRow(rowData);
						}
					}
					id4.setText("");
					sqlObject.closeSQL();
				} catch (SQLException e1) {
					System.err.println("Error SQLException\n" + e1.toString());
				} catch (NumberFormatException e1) {
					warning1.setText("NumberFormatException");
				}

			}
		});
		searchBtn.setBounds(659, 350, 180, 30);
		frame.getContentPane().add(searchBtn);

		id4 = new JTextField();
		id4.setColumns(10);
		id4.setBounds(739, 310, 100, 30);
		frame.getContentPane().add(id4);

		JLabel lblname1_1_1 = new JLabel("ID:");
		lblname1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname1_1_1.setBounds(659, 310, 80, 30);
		frame.getContentPane().add(lblname1_1_1);

		JLabel lblSearchSold = new JLabel("Search");
		lblSearchSold.setForeground(new Color(0, 0, 0));
		lblSearchSold.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchSold.setBounds(659, 280, 180, 30);
		frame.getContentPane().add(lblSearchSold);

		JLabel lblUpdateFruit = new JLabel("Update");
		lblUpdateFruit.setForeground(new Color(0, 0, 0));
		lblUpdateFruit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUpdateFruit.setBounds(20, 310, 180, 30);
		frame.getContentPane().add(lblUpdateFruit);

		JLabel lblname1_2 = new JLabel("Name:");
		lblname1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname1_2.setBounds(20, 380, 80, 30);
		frame.getContentPane().add(lblname1_2);

		name2 = new JTextField();
		name2.setColumns(10);
		name2.setBounds(100, 380, 100, 30);
		frame.getContentPane().add(name2);

		price2 = new JTextField();
		price2.setColumns(10);
		price2.setBounds(100, 420, 100, 30);
		frame.getContentPane().add(price2);

		JLabel lnlprice1_1 = new JLabel("Price:");
		lnlprice1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lnlprice1_1.setBounds(20, 420, 80, 30);
		frame.getContentPane().add(lnlprice1_1);

		JLabel lblQuantity1_1 = new JLabel("Quantity:");
		lblQuantity1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantity1_1.setBounds(20, 460, 80, 30);
		frame.getContentPane().add(lblQuantity1_1);

		quantity2 = new JTextField();
		quantity2.setColumns(10);
		quantity2.setBounds(100, 460, 100, 30);
		frame.getContentPane().add(quantity2);

		JButton updateBtn = new JButton("Update");
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					warning2.setText("");

					int id = Integer.parseInt(id2.getText());
					String name = name2.getText();
					Double price = Double.parseDouble(price2.getText());
					Integer quantity = Integer.parseInt(quantity2.getText());

					Data dataObj = new Data(id, name, price, quantity);
					MySql sqlObj = new MySql();
					sqlObj.updateData(dataObj);
					sqlObj.closeSQL();

					id2.setText("");
					name2.setText("");
					price2.setText("");
					quantity2.setText("");
					table();
				} catch (NumberFormatException e1) {
					warning1.setText("NumberFormatException");
				} catch (SQLException e1) {
					warning1.setText("SQLException");
				}
			}
		});
		updateBtn.setBounds(20, 500, 180, 30);
		frame.getContentPane().add(updateBtn);

		warning1 = new JLabel("");
		warning1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		warning1.setBounds(20, 250, 180, 30);
		frame.getContentPane().add(warning1);

		warning2 = new JLabel("");
		warning2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		warning2.setBounds(20, 530, 180, 30);
		frame.getContentPane().add(warning2);

		JLabel warning3 = new JLabel("");
		warning3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		warning3.setBounds(659, 170, 180, 30);
		frame.getContentPane().add(warning3);

		warning4 = new JLabel("");
		warning4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		warning4.setBounds(659, 379, 180, 30);
		frame.getContentPane().add(warning4);

		JButton btnNewButton_2_1 = new JButton("Refresh");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table();
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2_1.setBounds(338, 368, 180, 30);
		frame.getContentPane().add(btnNewButton_2_1);

		id2 = new JTextField();
		id2.setColumns(10);
		id2.setBounds(100, 340, 100, 30);
		frame.getContentPane().add(id2);

		JLabel lblname1_2_1 = new JLabel("ID:");
		lblname1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname1_2_1.setBounds(20, 340, 80, 30);
		frame.getContentPane().add(lblname1_2_1);

		table();
		frame.setVisible(true);
	}

	public void table() {
		defaultTableModel.setRowCount(0);
		try {
			MySql sqlObject = new MySql();
			ResultSet rs = sqlObject.printData();
			while (rs.next()) {
				Object rowData[] = new Object[4];
				rowData[0] = rs.getString(1);
				rowData[1] = rs.getString(2);
				rowData[2] = rs.getString(3);
				rowData[3] = rs.getString(4);
				defaultTableModel.addRow(rowData);
			}
			sqlObject.closeSQL();
		} catch (SQLException e1) {
			System.out.println("Error: SQLException");
		} catch (NullPointerException e1) {
			System.out.println("Error: NullPointerException");
		}
	}
}
