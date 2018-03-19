package com.sivagtr.filecrypto.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sivagtr.filecrypto.exceptions.WrongPassword;
import com.sivagtr.filecrypto.manager.OperationManager;

import net.miginfocom.swing.MigLayout;

/**
 * @author sivagtr
 *
 */
public class MainApplication extends JFrame implements ActionListener, DocumentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 823865632186528962L;
	private static final String FILE_EXTENSION = "enc";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainApplication frame = new MainApplication();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;
	private JTextField origTxtField;
	private JTextField encTextField;
	private JPasswordField passwordField;
	private JPasswordField verifyPasswordField;
	private JButton origBrowseBtn;
	private JButton encrptyBrowseBtn;
	private JFileChooser fileChooser;
	private JLabel lbl_verifyPassword;
	private JLabel passwdStrengthLB;
	private JLabel verifyPasswdLB;
	private JButton btnEncrypt;
	private File origFile, encryptFile, dEncryptFile, dDecryptFile;
	private boolean[] btnEnableStatus;
	private OperationManager mgr = OperationManager.getInstance();
	private JLabel lblDecryptedFile;
	private JTextField dEncryptTxt;
	private JButton dEncryptBrowse;
	private JTextField dDecryptTxt;
	private JButton dDecryptBrowse;
	private JLabel label;
	private JLabel lblPassword;
	private JPasswordField dPassword;
	private JButton decryptBtn;
	private JCheckBox chckbxDeleteOriginalFile;

	private JCheckBox chckbxDeleteEncryptedFile;

	/**
	 * Create the frame.
	 */
	public MainApplication() {
		setTitle("File Cryptor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Encrypt", null, panel, null);
		panel.setLayout(new MigLayout("", "[][][grow][]", "[][][][][][][][][][][]"));

		JLabel lblOriginalFile = new JLabel("Original File:");
		panel.add(lblOriginalFile, "cell 0 0");

		origTxtField = new JTextField();
		panel.add(origTxtField, "cell 2 0,growx");
		origTxtField.setColumns(10);

		origBrowseBtn = new JButton("Browse");
		panel.add(origBrowseBtn, "cell 3 0");

		JLabel lblEncryptedFile = new JLabel("Encrypted File:");
		panel.add(lblEncryptedFile, "cell 0 2");

		encTextField = new JTextField();
		panel.add(encTextField, "cell 2 2,growx");
		encTextField.setColumns(10);

		encrptyBrowseBtn = new JButton("Browse");
		panel.add(encrptyBrowseBtn, "cell 3 2");

		JLabel lblNewLabel = new JLabel("Password:");
		panel.add(lblNewLabel, "cell 0 4");

		passwordField = new JPasswordField();
		panel.add(passwordField, "cell 2 4,growx");

		passwdStrengthLB = new JLabel("Strength");
		passwdStrengthLB.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel.add(passwdStrengthLB, "cell 3 4,alignx center");

		lbl_verifyPassword = new JLabel("Verify Password:");
		panel.add(lbl_verifyPassword, "cell 0 6");

		verifyPasswordField = new JPasswordField();
		panel.add(verifyPasswordField, "cell 2 6,growx");

		verifyPasswdLB = new JLabel("Not Matched");
		verifyPasswdLB.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel.add(verifyPasswdLB, "cell 3 6,alignx center");

		chckbxDeleteOriginalFile = new JCheckBox("Delete Original File ?");
		panel.add(chckbxDeleteOriginalFile, "cell 2 10");

		btnEncrypt = new JButton("Encrypt");
		panel.add(btnEncrypt, "cell 3 10");

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Decrypt", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[][][grow][]", "[][][][][][]"));

		lblDecryptedFile = new JLabel("Encrypted File:");
		panel_1.add(lblDecryptedFile, "cell 0 0");

		dEncryptTxt = new JTextField();
		panel_1.add(dEncryptTxt, "cell 2 0,growx");
		dEncryptTxt.setColumns(10);

		dEncryptBrowse = new JButton("Browse");
		panel_1.add(dEncryptBrowse, "cell 3 0,alignx right");

		label = new JLabel("Decrypted File:");
		panel_1.add(label, "cell 0 1");

		dDecryptTxt = new JTextField();
		panel_1.add(dDecryptTxt, "cell 2 1,growx");
		dDecryptTxt.setColumns(10);

		dDecryptBrowse = new JButton("Browse");
		panel_1.add(dDecryptBrowse, "cell 3 1,alignx right");

		lblPassword = new JLabel("Password:");
		panel_1.add(lblPassword, "cell 0 2");

		dPassword = new JPasswordField();
		panel_1.add(dPassword, "cell 2 2,growx");

		chckbxDeleteEncryptedFile = new JCheckBox("Delete Encrypted File ?");
		panel_1.add(chckbxDeleteEncryptedFile, "cell 2 5");

		decryptBtn = new JButton("Decrypt");
		panel_1.add(decryptBtn, "cell 3 5,growx");

		initialSetting();
		registerForEvents();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == origBrowseBtn) {
			fileChooser = new JFileChooser();
			int i = fileChooser.showOpenDialog(this);
			if (i == JFileChooser.APPROVE_OPTION) {
				File choosedFile = fileChooser.getSelectedFile();
				origTxtField.setText(choosedFile.getAbsolutePath());
				btnEnableStatus[0] = true;
				File defaultEncryptionFile = new File(choosedFile.getPath() + "." + FILE_EXTENSION);
				encTextField.setText(choosedFile.getPath() + "." + FILE_EXTENSION);
				btnEnableStatus[1] = true;
				origFile = choosedFile;
				encryptFile = defaultEncryptionFile;
			}

		} else if (e.getSource() == encrptyBrowseBtn) {
			fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("." + FILE_EXTENSION, FILE_EXTENSION));
			fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[0]);
			int userSelection = fileChooser.showSaveDialog(this);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				String fileName = fileToSave.getAbsolutePath();
				btnEnableStatus[1] = true;
				if (!fileName.endsWith("." + FILE_EXTENSION)) {
					fileToSave = new File(fileName + "." + FILE_EXTENSION);
				}
				encTextField.setText(fileToSave.getPath());
				encryptFile = fileToSave;
			}
		} else if (btnEncrypt == e.getSource()) {
			boolean status;
			try {
				status = mgr.encryptOperation(origFile, encryptFile, passwordField.getPassword());

				if (status) {
					if (chckbxDeleteOriginalFile.isSelected()) {
						origFile.delete();
					}
					JOptionPane.showMessageDialog(this, "Encryption Successful !", "Sucess",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Encryption FAILED !", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (WrongPassword e1) {
				JOptionPane.showMessageDialog(this, "Wrong Password !!!", "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		} else if (e.getSource() == dEncryptBrowse) {

			fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("." + FILE_EXTENSION, FILE_EXTENSION));
			fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[0]);
			int i = fileChooser.showOpenDialog(this);
			if (i == JFileChooser.APPROVE_OPTION) {
				File choosedFile = fileChooser.getSelectedFile();
				dEncryptTxt.setText(choosedFile.getAbsolutePath());
				String fi = choosedFile.getAbsolutePath().replaceAll("." + FILE_EXTENSION, "");
				dDecryptTxt.setText(fi);
				File defaultDecryptionFile = new File(fi);

				dEncryptFile = choosedFile;
				dDecryptFile = defaultDecryptionFile;

			}
		} else if (e.getSource() == dDecryptBrowse) {
			fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");
			int userSelection = fileChooser.showSaveDialog(this);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				String fileName = fileToSave.getAbsolutePath();
				String fi = fileName.replaceAll("." + FILE_EXTENSION, "");
				if (fileName.endsWith("." + FILE_EXTENSION)) {
					fileToSave = new File(fi);
				}
				dDecryptTxt.setText(fileToSave.getPath());
				dDecryptFile = fileToSave;
			}
		} else if (decryptBtn == e.getSource()) {
			boolean status;
			try {
				status = mgr.decryptOperation(dEncryptFile, dDecryptFile, dPassword.getPassword());

				if (status) {
					if (chckbxDeleteEncryptedFile.isSelected()) {
						dEncryptFile.delete();
					}
					JOptionPane.showMessageDialog(this, "Decryption Successful !", "Sucess",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Decryption FAILED !", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (WrongPassword e1) {
				JOptionPane.showMessageDialog(this, "Wrong Password !!!", "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}

		updateEncryptBtn();
		updateDecryptBtn();
	}

	private int calculatePasswordStrength(String passwd) {

		int passwdScore = 0;

		if (passwd.length() < 8)
			return 0;
		else if (passwd.length() >= 10)
			passwdScore += 2;
		else
			passwdScore += 1;
		if (passwd.matches("(?=.*[0-9].*[0-9]).*"))
			passwdScore += 2;
		else if (passwd.matches("(?=.*[0-9]).*"))
			passwdScore += 1;
		if (passwd.matches("(?=.*[a-z]).*"))
			passwdScore += 2;
		if (passwd.matches("(?=.*[A-Z].*[A-Z]).*"))
			passwdScore += 2;
		else if (passwd.matches("(?=.*[A-Z]).*"))
			passwdScore += 1;
		if (passwd.matches("(?=.*[~!@#$%^&*()_-].*[~!@#$%^&*()_-]).*"))
			passwdScore += 2;
		else if (passwd.matches("(?=.*[~!@#$%^&*()_-]).*"))
			passwdScore += 1;

		return passwdScore;

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	private void checkPasswordField() {
		char[] password = passwordField.getPassword();
		int strength = calculatePasswordStrength(new String(password));
		btnEnableStatus[2] = true;
		passwdStrengthLB.setText("");
		if (strength < 2) {
			passwdStrengthLB.setText("Very Weak");
			passwdStrengthLB.setForeground(Color.RED);
			btnEnableStatus[2] = false;
		} else if (strength < 4) {
			passwdStrengthLB.setText("Weak");
			passwdStrengthLB.setForeground(Color.ORANGE);
		} else if (strength < 6) {
			passwdStrengthLB.setText("Moderate");
			passwdStrengthLB.setForeground(Color.YELLOW);
		} else if (strength < 8) {
			passwdStrengthLB.setText("Strong");
			passwdStrengthLB.setForeground(new Color(127, 255, 0));
		} else {
			passwdStrengthLB.setText("Very Strong");
			passwdStrengthLB.setForeground(new Color(0, 100, 0));
		}
	}

	private void checkVerifyPasswordField() {
		char[] password = passwordField.getPassword();
		char[] verifyPassword = verifyPasswordField.getPassword();
		String pwd = new String(password);
		String verifyPwd = new String(verifyPassword);
		if (pwd.equals(verifyPwd) && pwd.length() > 0) {
			verifyPasswdLB.setText("Matched");
			verifyPasswdLB.setForeground(new Color(0, 100, 0));
			btnEnableStatus[3] = true;
		} else {
			verifyPasswdLB.setText("Not Matched");
			verifyPasswdLB.setForeground(Color.RED);
			btnEnableStatus[3] = false;
		}
	}

	@Override
	public void dispose() {
		unRegisterForEvents();
		super.dispose();
	}

	private void initialSetting() {
		/**
		 * Encryption Tab
		 */
		origTxtField.setEnabled(false);
		encTextField.setEnabled(false);
		passwdStrengthLB.setText("Very Weak");
		passwdStrengthLB.setForeground(Color.RED);
		verifyPasswdLB.setText("Not Matched");
		verifyPasswdLB.setForeground(Color.RED);
		btnEnableStatus = new boolean[4];
		updateEncryptBtn();

		/**
		 * Decryption Tab
		 */
		dEncryptTxt.setEnabled(false);
		dDecryptTxt.setEnabled(false);
		updateDecryptBtn();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		updatePasswordField();
	}

	private void registerForEvents() {

		origBrowseBtn.addActionListener(this);
		encrptyBrowseBtn.addActionListener(this);
		btnEncrypt.addActionListener(this);
		passwordField.getDocument().addDocumentListener(this);
		verifyPasswordField.getDocument().addDocumentListener(this);

		dPassword.getDocument().addDocumentListener(this);
		dEncryptBrowse.addActionListener(this);
		dDecryptBrowse.addActionListener(this);
		decryptBtn.addActionListener(this);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updatePasswordField();
	}

	private void unRegisterForEvents() {

		origBrowseBtn.removeActionListener(this);
		encrptyBrowseBtn.removeActionListener(this);
		btnEncrypt.removeActionListener(this);
		passwordField.getDocument().removeDocumentListener(this);
		verifyPasswordField.getDocument().removeDocumentListener(this);

		dPassword.getDocument().removeDocumentListener(this);
		dEncryptBrowse.removeActionListener(this);
		dDecryptBrowse.removeActionListener(this);
		decryptBtn.removeActionListener(this);
	}

	private void updateDecryptBtn() {
		if (dDecryptTxt.getText().equals("") || dEncryptTxt.getText().equals("")
				|| dPassword.getPassword().length == 0) {
			decryptBtn.setEnabled(false);
		} else {
			decryptBtn.setEnabled(true);
		}

	}

	private void updateEncryptBtn() {
		for (boolean status : btnEnableStatus) {
			if (!status) {
				btnEncrypt.setEnabled(false);
				return;
			}
		}
		btnEncrypt.setEnabled(true);
	}

	private void updatePasswordField() {
		checkPasswordField();
		checkVerifyPasswordField();
		updateEncryptBtn();
		updateDecryptBtn();
	}
}
