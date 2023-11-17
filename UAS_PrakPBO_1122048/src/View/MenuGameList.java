package src.View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import src.Controller.Controller;
import src.Model.Games;

public class MenuGameList {
    public MenuGameList(int inputUserID) {
        showList(inputUserID);
    }

    private Controller cntrl = new Controller();
    private JFrame frame;
    private JTable gameTable;

    private void showList(int inputUserID) {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        //buat button transaction
        JButton btnTransactions = new JButton("Transactions");
        btnTransactions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuTransaction(inputUserID);
            }
        });
        frame.getContentPane().add(btnTransactions, BorderLayout.NORTH);

        //buat header tabel
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Genre");
        tableModel.addColumn("Price");

        gameTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(gameTable);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // buat tampilin data game nya dalam row table
        DisplayGames();

        //buat button transaction
        JButton btnBuyGame = new JButton("Buy Game");
        btnBuyGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buyGame(inputUserID);
            }
        });
        frame.getContentPane().add(btnBuyGame, BorderLayout.SOUTH);
        frame.setVisible(true);

    }

    private void DisplayGames() {
        List<Games> games = cntrl.getUserGame();
        DefaultTableModel tableModel = (DefaultTableModel) gameTable.getModel();

        // Add rows to the table model
        for (Games game : games) {
            Object[] rowData = {game.getId(), game.getName(), game.getGenre(), game.getPrice()};
            tableModel.addRow(rowData);
        }

    }

    private void buyGame(int inputUserID) {
        String gameIDInput = JOptionPane.showInputDialog(frame, "Masukkan Game ID :");
    
        if (gameIDInput == null || gameIDInput.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Silakan isi Game ID");
            return;
        }
    
        try {
            int gameID = Integer.parseInt(gameIDInput);
    
            boolean success = cntrl.buyGame(inputUserID, gameID);
    
            if (success) {
                JOptionPane.showMessageDialog(frame, "Game berhasil dibeli!");
            } else {
                JOptionPane.showMessageDialog(frame, "Gagal membeli game.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Game ID tidak valid. Masukkan angka yang benar.");
        }
    }
}
