package com.axian.ui;

import com.axian.materials.MaterialMaps;
import com.axian.materials.types.BaseMaterial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

public class Window implements ActionListener {

    public JFrame window = new JFrame("Crafting tree generator");
    private JPanel bottomPanel = new JPanel(new BorderLayout());
    private JPanel topPanel = new JPanel(new BorderLayout());

    private JTextArea output = new JTextArea();

    private Dimension windowSize = new Dimension(1080, 720);

    private final File iconsFolder;

    public Window(File givenIconsFolder) {
        iconsFolder = givenIconsFolder;

        window.setSize(windowSize);
        topPanel.setPreferredSize(new Dimension(windowSize.width, windowSize.height/3));
        bottomPanel.setPreferredSize(new Dimension(windowSize.width, windowSize.height/3));

        // The output area
        output.setText("Click a recipe to see it's tree");
        output.setEditable(false);

        // Make buttons
        setButtons();

        // A label to ensure the last button added doesn't have a stupid size, because Swing is dumb
        JLabel fillLabel = new JLabel();
        topPanel.add(fillLabel, BorderLayout.CENTER);

        window.getContentPane().add(topPanel, BorderLayout.PAGE_START);
        window.getContentPane().add(bottomPanel, BorderLayout.PAGE_END);

        // Make output scrollable
        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        window.setVisible(true);
    }

    private void setButtons(){

        File[] iconFiles = iconsFolder.listFiles();
        for (Map.Entry<String, BaseMaterial> entry : MaterialMaps.materials.entrySet()) {
            for (File file : iconFiles) {
                if (file.getName().replaceAll("\\..*", "")
                        .equals(
                                entry.getValue().getId())) {
                    Buttons.buttonIcons.put(entry.getValue().getId(), file.getPath());
                }
            }
        }


        // Use this forEach loop instead of .forEach(()->{}) because I need an iterator (i)
        int buttonXMod = 0;
        int buttonYMod = 0;
        for (Map.Entry<String, BaseMaterial> entry : MaterialMaps.materials.entrySet()) {

            String id = entry.getValue().getId();
            String name = entry.getValue().getReadableName();
            int iconScaleTo = 75;

            if (Buttons.buttonIcons.containsKey(id)) {
                Image image = Toolkit.getDefaultToolkit().getImage(Buttons.buttonIcons.get(id));
                ImageIcon icon = new ImageIcon(
                        image.getScaledInstance(iconScaleTo, iconScaleTo, Image.SCALE_DEFAULT),
                        "");

                Buttons.buttons.put(id, new JButton(icon));

                // Move the buttons down a row if they would go off the screen
                if (buttonXMod + iconScaleTo > 1080) {
                    buttonYMod += iconScaleTo;
                    buttonXMod = 0;
                }

                Buttons.buttons.get(id).setBounds(buttonXMod, buttonYMod, iconScaleTo, iconScaleTo);
                Buttons.buttons.get(id).addActionListener(this);

                topPanel.add(Buttons.buttons.get(id), BorderLayout.CENTER);

                buttonXMod += iconScaleTo;

            }else {
                Buttons.buttons.put(id, new JButton(name));

                // Move the buttons down a row if they would go off the screen
                if (buttonXMod + (10 * name.length()) > 1080) {
                    buttonYMod += iconScaleTo;
                    buttonXMod = 0;
                }

                Buttons.buttons.get(id).setBounds(buttonXMod, buttonYMod, 10 * name.length(), iconScaleTo);
                Buttons.buttons.get(id).addActionListener(this);

                topPanel.add(Buttons.buttons.get(id), BorderLayout.CENTER);
                buttonXMod += (10 * name.length()); // Set their size/x position relative to each other
            }
        }
    }

    // When an action happens (button, checkbox, etc.)
    @Override
    public void actionPerformed(ActionEvent event) {
        // Check if it's any of the buttons, set the output to the button's crafting tree
        Buttons.buttons.forEach((id, button) -> {
            if (event.getSource() == button) {
                output.setText(MaterialMaps.materials.get(id).getTree());
            }
        });
    }
}
