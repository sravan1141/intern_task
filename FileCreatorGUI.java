import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreatorGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("File Creator");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel typeLabel = new JLabel("File Type:");
        JTextField typeField = new JTextField();

        JLabel nameLabel = new JLabel("File Name:");
        JTextField nameField = new JTextField();

        JLabel extLabel = new JLabel("File Extension:");
        JTextField extField = new JTextField();

        JLabel contentLabel = new JLabel("File Content:");
        JTextArea contentArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(contentArea);

        JButton saveButton = new JButton("Save File");


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileType = typeField.getText();
                String fileName = nameField.getText();
                String fileExt = extField.getText();
                String fileContent = contentArea.getText();

                if (fileName.isEmpty() || fileExt.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "File Name and Extension are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String fullFileName = fileName + "." + fileExt;
                File file = new File(fullFileName);

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("File Type: " + fileType + "\n\n");
                    writer.write(fileContent);
                    JOptionPane.showMessageDialog(frame, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(typeLabel);
        frame.add(typeField);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(extLabel);
        frame.add(extField);
        frame.add(contentLabel);
        frame.add(scrollPane);
        frame.add(new JLabel()); // Empty placeholder
        frame.add(saveButton);

        frame.setVisible(true);
    }
}
