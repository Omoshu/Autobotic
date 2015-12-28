package com.cionik.autoroboto;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.lumines.laf.ui.LuminesLookAndFeel;

import com.cionik.autoroboto.ui.MouseTaskPanel;
import com.cionik.autoroboto.ui.RobotPanel;
import com.cionik.autoroboto.ui.TabbedWindow;
import com.cionik.autoroboto.ui.TaskOptionsPanel;
import com.cionik.autoroboto.ui.TextTypeTaskPanel;
import com.cionik.utils.swing.MessageDialog;

public class Initialize {
	
	public static void main(String[] args) {
		//String[] options = {"Default", "System", "Lumines"};
		//int option = JOptionPane.showOptionDialog(null, "Choose LookAndFeel", "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		try {
			//if (option == 1) {
				//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//} else if (option == 2) {
				UIManager.setLookAndFeel(new LuminesLookAndFeel());
			//}
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			MessageDialog.show(null, true, "Error", "Some features are not available for your operating system.", "autoRoboto.nativeHook");
		}
		
		EventQueue.invokeLater(() -> {
			TabbedWindow window = new TabbedWindow("AutoRoboto");
			window.addTab("Clicker", new TaskOptionsPanel(new MouseTaskPanel(MouseEvent.MOUSE_CLICKED)));
			window.addTab("Typer", new TaskOptionsPanel(new TextTypeTaskPanel()));
			window.addTab("Robot", new TaskOptionsPanel(new RobotPanel()));
			window.pack();
			window.setVisible(true);
		});
	}
	
}