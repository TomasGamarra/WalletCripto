package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelRegistro extends JPanel {
	private JPanel mainPanel;
	private JLabel labelNombre ;
	private JLabel labelApellido ;
	private JTextField fieldNombre;
	private JTextField fieldApellido;
	private JLabel labelEmail;
	private JTextField fieldEmail;
	private JLabel labelContra;
	private JPasswordField fieldContra;
	private JLabel labelContra2;
	private JPasswordField fieldContra2;
	private JCheckBox termsCheckBox;
	private JButton registerButton;
	private JButton volverButton;
	
	public PanelRegistro () { 
		setLayout(new BorderLayout());
		setOpaque(false);
		
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets (5,5,5,5);
		
		//Label Nombre
		labelNombre = new JLabel("Nombre :");
		labelNombre.setForeground(Color.BLACK);
        labelNombre.setFont(new Font("Arial", Font.BOLD, 16));
        
        gbc.gridx=0;
        gbc.gridy=0;
        
        mainPanel.add(labelNombre,gbc);
        //Label Apellido
        labelApellido = new JLabel("Apellido :");
		labelApellido.setForeground(Color.BLACK);
        labelApellido.setFont(new Font("Arial", Font.BOLD, 16));
        
        gbc.gridx=0;
        gbc.gridy=1;
        
        mainPanel.add(labelApellido,gbc);
        
        //Label Email
        labelEmail = new JLabel("Email :");
		labelEmail.setForeground(Color.BLACK);
        labelEmail.setFont(new Font("Arial", Font.BOLD, 16));
        
        gbc.gridx=0;
        gbc.gridy=2;
        
        mainPanel.add(labelEmail,gbc);
        
        //Label Contra 
        
        labelContra = new JLabel("Contraseña :");
		labelContra.setForeground(Color.BLACK);
        labelContra.setFont(new Font("Arial", Font.BOLD, 16));
        
        gbc.gridx=0;
        gbc.gridy=3;
        
        mainPanel.add(labelContra,gbc);
        
        //Label Contra 2
        labelContra2 = new JLabel("Repita la contraseña :");
		labelContra2.setForeground(Color.BLACK);
        labelContra2.setFont(new Font("Arial", Font.BOLD, 16));
        
        gbc.gridx=0;
        gbc.gridy=4;
        
        mainPanel.add(labelContra2,gbc);
        
      
        
        //Field Nombre
        fieldNombre = new JTextField(15);
        gbc.gridx=1;
        gbc.gridy=0;
        mainPanel.add(fieldNombre,gbc);
        
        //Field Apellido
        fieldApellido = new JTextField(15);
        gbc.gridx=1;
        gbc.gridy=1;
        mainPanel.add(fieldApellido,gbc);
        
        
        //Field Email
        fieldEmail = new JTextField(15);
        gbc.gridx=1;
        gbc.gridy=2;
        mainPanel.add(fieldEmail,gbc);
        
        //Field Contraseña
        fieldContra = new JPasswordField(15);
        gbc.gridx=1;
        gbc.gridy=3;
        mainPanel.add(fieldContra,gbc);
        
        //Field Contra2
        fieldContra2 = new JPasswordField(15);
        gbc.gridx=1;
        gbc.gridy=4;
        mainPanel.add(fieldContra2,gbc);
        
      
        //Checkbox
        termsCheckBox = new JCheckBox("Acepto los términos y condiciones.");
        termsCheckBox.setForeground(Color.BLACK);
        termsCheckBox.setFont(new Font("Arial",Font.BOLD,13));
        gbc.gridx=1;
        gbc.gridy=5;
        gbc.insets=new Insets(20,20,20,20);
        termsCheckBox.setOpaque(false);
        termsCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
        mainPanel.add(termsCheckBox, gbc);
        
        //Registrarse Button
        registerButton = new JButton("Registrarse");
        registerButton.setBackground(Color.DARK_GRAY);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
  
        gbc.gridx=1;
        gbc.gridy=6;
        
        mainPanel.add(registerButton,gbc);
        
        //Button Volver
        volverButton = new JButton("Volver");
        volverButton.setBackground(Color.DARK_GRAY);
        volverButton.setForeground(Color.WHITE);
        volverButton.setFont(new Font("Arial", Font.BOLD, 16));
        
        gbc.gridx=0;
        
        mainPanel.add(volverButton,gbc);
        
        add(mainPanel,BorderLayout.CENTER);
   
	}
	
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar fondo con gradiente
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, new Color(47,224,189), getWidth(), getHeight(),new Color (255,127,172)));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	public String extraerNombre() {
		return fieldNombre.getText();
	}
	
	public void ActualizarNombre() {
		 fieldNombre.setText("");
	}

	public String extraerApellido() {
		return fieldApellido.getText();
	}
	public void ActualizarApellido() {
		 fieldApellido.setText("");
	}

	
	public String extraerEmail() {
		return fieldEmail.getText();
	}
	
	public void ActualizarEmail() {
		 fieldEmail.setText("");
	}

	
	public String extraerContra() {
		return new String(fieldContra.getText());
	}
	
	public void ActualizarContra() {
		 fieldContra.setText("");
	}
	public String extraerContra2() {
		return new String(fieldContra2.getText());
	}
	public void ActualizarContra2() {
		 fieldContra2.setText("");
	}
	
	public boolean extraerTerminos() {
		return termsCheckBox.isSelected();
	}
	
	public void actualizarTerminos() {
		termsCheckBox.setSelected(false);;
	}
	
	
	public JLabel getLabelNombre() {
		return labelNombre;
	}

	public void setLabelNombre(JLabel labelNombre) {
		this.labelNombre = labelNombre;
	}

	public JLabel getLabelApellido() {
		return labelApellido;
	}

	public void setLabelApellido(JLabel labelApellido) {
		this.labelApellido = labelApellido;
	}

	public JTextField getFieldNombre() {
		return fieldNombre;
	}

	public void setFieldNombre(JTextField fieldNombre) {
		this.fieldNombre = fieldNombre;
	}

	public JTextField getFieldApellido() {
		return fieldApellido;
	}

	public void setFieldApellido(JTextField fieldApellido) {
		this.fieldApellido = fieldApellido;
	}

	public JLabel getLabelEmail() {
		return labelEmail;
	}

	public void setLabelEmail(JLabel labelEmail) {
		this.labelEmail = labelEmail;
	}

	public JTextField getFieldEmail() {
		return fieldEmail;
	}

	public void setFieldEmail(JTextField fieldEmail) {
		this.fieldEmail = fieldEmail;
	}

	public JLabel getLabelContra() {
		return labelContra;
	}

	public void setLabelContra(JLabel labelContra) {
		this.labelContra = labelContra;
	}

	public JPasswordField getFieldContra() {
		return fieldContra;
	}

	public void setFieldContra(JPasswordField fieldContra) {
		this.fieldContra = fieldContra;
	}

	public JLabel getLabelContra2() {
		return labelContra2;
	}

	public void setLabelContra2(JLabel labelContra2) {
		this.labelContra2 = labelContra2;
	}

	public JPasswordField getFieldContra2() {
		return fieldContra2;
	}

	public void setFieldContra2(JPasswordField fieldContra2) {
		this.fieldContra2 = fieldContra2;
	}

	public JCheckBox getTermsCheckBox() {
		return termsCheckBox;
	}

	public void setTermsCheckBox(JCheckBox termsCheckBox) {
		this.termsCheckBox = termsCheckBox;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(JButton registerButton) {
		this.registerButton = registerButton;
	}

	public JButton getVolverButton() {
		return volverButton;
	}

	public void setVolverButton(JButton volverButton) {
		this.volverButton = volverButton;
	}
}
