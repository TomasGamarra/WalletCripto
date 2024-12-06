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
}
