package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MenuGUI extends JFrame {
    private JTextArea textArea;
    private Random random = new Random();
    private float hue = random.nextFloat();

    public MenuGUI() {
        setTitle("Simple User Interface");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        setJMenuBar(createMenuBar());
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        menu.add(createMenuItem("Show Date & Time", e -> showDateTime()));
        menu.add(createMenuItem("Log to File", e -> logToFile()));
        menu.add(createMenuItem("Change Background Color", e -> changeBackgroundColor()));
        menu.add(createMenuItem("Exit", e -> System.exit(0)));

        menuBar.add(menu);
        return menuBar;
    }

    private JMenuItem createMenuItem(String title, ActionListener action) {
        JMenuItem item = new JMenuItem(title);
        item.addActionListener(action);
        return item;
    }

    private void showDateTime() {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        textArea.setText(currentTime);
    }

    private void logToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(textArea.getText());
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Logged to log.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file");
        }
    }

    private void changeBackgroundColor() {
        // Generate a random hue specifically for green (between 0.25 and 0.4)
        hue = 0.25f + random.nextFloat() * 0.15f; // Adjusting the range for green
        Color color = Color.getHSBColor(hue, 0.5f, 0.5f);
        getContentPane().setBackground(color);
        textArea.setBackground(color);
        JOptionPane.showMessageDialog(this, "Background color changed to hue: " + hue);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuGUI::new);
    }
}

