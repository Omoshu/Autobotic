package com.cionik.autoroboto.ui;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import com.cionik.autoroboto.model.Time;
import com.cionik.autoroboto.task.TextTypeTask;
import com.cionik.autoroboto.util.JNumericTextField;
import com.cionik.autoroboto.util.Listener;

import net.miginfocom.swing.MigLayout;

public class TextTypeTaskPanel extends JPanel implements TaskPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField = new JTextField();
	private JNumericTextField characterDelayTextField = new JNumericTextField(0);
	private JComboBox<TimeUnit> characterDelayComboBox = new JComboBox<TimeUnit>(TimeUnit.values());
	
	public TextTypeTaskPanel() {
		super();
		
		initComponents();
		layoutComponents();
	}
	
	private void initComponents() {
		characterDelayTextField.setColumns(7);
		characterDelayComboBox.setSelectedItem(TimeUnit.MILLISECONDS);
	}
	
	private void layoutComponents() {
		setLayout(new MigLayout());
		
		add(new JLabel("Text: "));
		add(textField, "spanx, pushx, growx, wrap");
		add(new JLabel("Character Delay: "));
		add(characterDelayTextField);
		add(characterDelayComboBox, "wrap");
	}
	
	public String getText() {
		return textField.getText();
	}
	
	public Time getCharacterDelay() {
		Integer delay = characterDelayTextField.getValue();
		return delay == null ? null : new Time(delay, (TimeUnit) characterDelayComboBox.getSelectedItem());
	}

	@Override
	public boolean hasValidInput() {
		return !textField.getText().equals("") && characterDelayTextField.getValue() != null;
	}

	@Override
	public Runnable getTask() {
		try {
			return new TextTypeTask(getText(), getCharacterDelay());
		} catch (AWTException e) {
			return null;
		}
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void addInputChangeListener(Listener<Void> listener) {
		DocumentListener documentListener = new DocumentListenerAdapter(listener);
		textField.getDocument().addDocumentListener(documentListener);
		characterDelayTextField.getDocument().addDocumentListener(documentListener);
	}
	
}