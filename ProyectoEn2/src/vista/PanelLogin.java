package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import enumerativos.ImagenesEnum;


public class PanelLogin extends JPanel {
	private JPanel panelCenter;
	private JPanel topPanel;
	private JLabel logoLabel ;
	private JLabel labelUsuario;
	private JTextField userField;
	private JLabel labelContra;
	private JPasswordField passwdField;
	private JButton loginButton;
	private JLabel registrado;
	private JButton registerButton;
	
	public PanelLogin () {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(0, 80));
		
		topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE); 
        
        logoLabel = new JLabel();
        //logoLabel.setIcon(new ImageIcon(getClass().getResource(ImagenesEnum.LOGOEMPRESA.getRutaFoto()))); // Ruta al logo
        
        topPanel.add(logoLabel);
        topPanel.setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("HODL", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.DARK_GRAY);

        topPanel.add(logoLabel, BorderLayout.WEST);
        topPanel.add(titulo, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
		
		panelCenter = new JPanel();
		panelCenter.setOpaque(false);
        panelCenter.setLayout(new GridBagLayout());
        
        // Crear una instancia de GridBagConstraints para configurar la ubicación de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);  // Espaciado entre los componentes

        // Configurar el GridBagConstraints 
        gbc.gridx = 0;  
        gbc.gridy = 0;  
        
        labelUsuario = new JLabel("Email:");
        labelUsuario.setForeground(Color.BLACK);
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        
        panelCenter.add(labelUsuario , gbc);
        

        // Configurar el JTextField para Usuario
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        userField = new JTextField(10);
        panelCenter.add(userField, gbc);

        // Configurar el segundo JLabel (Contraseña:)
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        labelContra = new JLabel("Contraseña:");
        labelContra.setForeground(Color.BLACK);
        labelContra.setFont(new Font("Arial", Font.BOLD, 16));
        panelCenter.add(labelContra, gbc);

        // Configurar el JPasswordField para Contraseña
        gbc.gridx = 1;
        gbc.gridy = 1;
        
        passwdField = new JPasswordField(10);
        panelCenter.add(passwdField, gbc);

        // Configurar el botón de login
        gbc.gridx = 1;
        gbc.gridy = 2;
        
        loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(Color.DARK_GRAY);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        panelCenter.add(loginButton, gbc);
        
        registrado = new JLabel("Aun no te has registrado ?");
        registrado.setFont(new Font("Arial", Font.BOLD, 16));
        registrado.setBackground(Color.DARK_GRAY);
        registrado.setForeground(Color.WHITE);
        
        gbc.insets = new Insets(80,20,80,20);
        gbc.gridx=0;
        gbc.gridy=3;
        
        panelCenter.add(registrado,gbc);
        
        registerButton = new JButton ("Registrarse");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(Color.DARK_GRAY);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        
        gbc.gridx=1;
        
        panelCenter.add(registerButton,gbc);
        
        add(panelCenter, BorderLayout.CENTER);
        
        
	}
	
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar fondo con gradiente
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, new Color(47,224,189), getWidth(), getHeight(),new Color (255,127,172)));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

	
	
	
	public String getUser() {
		return userField.getText();
	}
	
	

	public String getPassword() {
		return new String(passwdField.getPassword());
	}
	
	public void setUser() {
		userField.setText("");
	}
	
	public void setPassword() {
		passwdField.setText("");
	}
	
	public JPanel getPanelCenter() {
		return panelCenter;
	}

	public void setPanelCenter(JPanel panelCenter) {
		this.panelCenter = panelCenter;
	}

	public JLabel getLabelUsuario() {
		return labelUsuario;
	}

	public void setLabelUsuario(JLabel labelUsuario) {
		this.labelUsuario = labelUsuario;
	}

	public JTextField getUserField() {
		return userField;
	}

	public void setUserField(JTextField userField) {
		this.userField = userField;
	}

	public JLabel getLabelContra() {
		return labelContra;
	}

	public void setLabelContra(JLabel labelContra) {
		this.labelContra = labelContra;
	}

	public JPasswordField getPasswdField() {
		return passwdField;
	}

	public void setPasswdField(JPasswordField passwdField) {
		this.passwdField = passwdField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

	public JLabel getRegistrado() {
		return registrado;
	}

	public void setRegistrado(JLabel registrado) {
		this.registrado = registrado;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(JButton registerButton) {
		this.registerButton = registerButton;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JLabel getLogoLabel() {
		return logoLabel;
	}

	public void setLogoLabel(JLabel logoLabel) {
		this.logoLabel = logoLabel;
	}
}
