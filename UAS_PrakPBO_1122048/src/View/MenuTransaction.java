package src.View;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import src.Controller.Controller;
import src.Model.Transaction;

public class MenuTransaction {
    public MenuTransaction (int inputUserID) {
        showUserTrans(inputUserID);
    }

    private Controller cntrl = new Controller();
    private JFrame frame;
    private JTable transactionTable;

    private void showUserTrans (int inputUserID) {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Trans ID");
        tableModel.addColumn("User ID");
        tableModel.addColumn("User Name");
        tableModel.addColumn("Game ID");
        tableModel.addColumn("Game Name");
        tableModel.addColumn("Total Price");

        transactionTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

}
