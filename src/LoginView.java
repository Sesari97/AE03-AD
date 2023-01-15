import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class LoginView {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtID;
	private JTextField txtId;
	private JTextField txtTitulo;
	private JTextField txtAnyoNac;
	private JTextField txtAnyoPub;
	private JTextField txtEditorial;
	private JTextField txtNoPag;
	private JTextField txtThumbnail;
	private JTextField txtAutor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 884, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(66, 37, 277, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(10, 43, 46, 14);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPwd = new JLabel("Password");
		lblPwd.setBounds(10, 85, 46, 14);
		frame.getContentPane().add(lblPwd);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(165, 195, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(66, 82, 277, 20);
		frame.getContentPane().add(passwordField);
		
		txtID = new JTextField();
		txtID.setBounds(165, 63, 86, 20);
		frame.getContentPane().add(txtID);
		txtID.setColumns(10);
		txtID.setVisible(false);
		
		JLabel lblID = new JLabel("Introduce el ID del libro");
		lblID.setBounds(120, 42, 178, 14);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblID);
		lblID.setVisible(false);
		
		JButton btnRead = new JButton("Leer");
		btnRead.setBounds(165, 113, 89, 23);
		frame.getContentPane().add(btnRead);
		btnRead.setVisible(false);
		
		JButton btnRemove = new JButton("Borrar");
		btnRemove.setBounds(165, 147, 89, 23);
		frame.getContentPane().add(btnRemove);
		btnRemove.setVisible(false);
		
		txtId = new JTextField();
		txtId.setBounds(499, 11, 86, 20);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(499, 43, 86, 20);
		frame.getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		txtTitulo.setVisible(false);
		
		txtAnyoNac = new JTextField();
		txtAnyoNac.setBounds(499, 114, 86, 20);
		frame.getContentPane().add(txtAnyoNac);
		txtAnyoNac.setColumns(10);
		txtAnyoNac.setVisible(false);
		
		txtAnyoPub = new JTextField();
		txtAnyoPub.setBounds(499, 148, 86, 20);
		frame.getContentPane().add(txtAnyoPub);
		txtAnyoPub.setColumns(10);
		txtAnyoPub.setVisible(false);
		
		txtEditorial = new JTextField();
		txtEditorial.setBounds(499, 180, 86, 20);
		frame.getContentPane().add(txtEditorial);
		txtEditorial.setColumns(10);
		txtEditorial.setVisible(false);
		
		txtNoPag = new JTextField();
		txtNoPag.setBounds(499, 211, 86, 20);
		frame.getContentPane().add(txtNoPag);
		txtNoPag.setColumns(10);
		txtNoPag.setVisible(false);
		
		txtThumbnail = new JTextField();
		txtThumbnail.setBounds(499, 242, 86, 20);
		frame.getContentPane().add(txtThumbnail);
		txtThumbnail.setColumns(10);
		txtThumbnail.setVisible(false);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(496, 287, 89, 23);
		frame.getContentPane().add(btnCrear);
		btnCrear.setVisible(false);
		
		JButton btnActulizar = new JButton("Actualizar");
		btnActulizar.setBounds(496, 321, 89, 23);
		frame.getContentPane().add(btnActulizar);
		btnActulizar.setVisible(false);
		
		txtAutor = new JTextField();
		txtAutor.setBounds(499, 82, 86, 20);
		frame.getContentPane().add(txtAutor);
		txtAutor.setColumns(10);
		txtAutor.setVisible(false);
		
		Controller c = new Controller();
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\cesar\\Desktop\\2DAM\\AD\\AE03\\src\\mongoUsers.json")), StandardCharsets.UTF_8);
					boolean login = c.login(json, textField.getText(), passwordField.getText());
					
					
					if (!login) {
						JOptionPane.showInternalMessageDialog(null,
			                    "Error al iniciar sesion", "Atencion", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showInternalMessageDialog(null,
			                    "Sesion iniciada correctamente", "Atencion", JOptionPane.INFORMATION_MESSAGE);
						String jsonBooks = new String(Files.readAllBytes(Paths.get("C:\\Users\\cesar\\Desktop\\2DAM\\AD\\AE03\\src\\mongoBooks.json")), StandardCharsets.UTF_8);
						JSONArray books = c.getBooks(jsonBooks);
						
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < books.length(); i ++) {
							JSONObject book = books.getJSONObject(i);
							System.out.println(book);
							sb.append("ID: " + book.getInt("Id") + " - Titulo: " + book.getString("Titulo") + "\n");
						}
												
						JOptionPane.showInternalMessageDialog(null,
			                    "Libros: " + books.length() + "\n" + sb, "Atencion", JOptionPane.INFORMATION_MESSAGE);
						
						lblUser.setVisible(false);
						lblPwd.setVisible(false);
						textField.setVisible(false);
						passwordField.setVisible(false);
						btnLogin.setVisible(false);
						lblID.setVisible(true);
						txtID.setVisible(true);
						btnRead.setVisible(true);
						btnRemove.setVisible(true);
						txtThumbnail.setVisible(true);
						txtId.setVisible(true);
						txtAutor.setVisible(true);
						txtAnyoNac.setVisible(true);
						txtAnyoPub.setVisible(true);
						txtEditorial.setVisible(true);
						txtNoPag.setVisible(true);
						btnActulizar.setVisible(true);
						btnCrear.setVisible(true);
						txtTitulo.setVisible(true);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnRead.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String jsonBooks = new String(Files.readAllBytes(Paths.get("C:\\Users\\cesar\\Desktop\\2DAM\\AD\\AE03\\src\\mongoBooks.json")), StandardCharsets.UTF_8);
					JSONObject book = c.getBookInfo(jsonBooks, txtID.getText());
					
					String info = book.getString("Titulo") + "\n" + book.getString("Autor") + "\n" + book.getInt("Anyo_nacimiento") + "\n" 
							+ book.getInt("Anyo_publicacion") + "\n" + book.getString("Editorial") + "\n" + book.getInt("Numero_paginas");
					JOptionPane.showInternalMessageDialog(null,
		                    info, "Informacion", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String jsonBooks;
				try {
					jsonBooks = new String(Files.readAllBytes(Paths.get("C:\\Users\\cesar\\Desktop\\2DAM\\AD\\AE03\\src\\mongoBooks.json")), StandardCharsets.UTF_8);
					c.removeBook(jsonBooks, txtID.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
//		btnCrear.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					String jsonBooks = new String(Files.readAllBytes(Paths.get("C:\\Users\\cesar\\Desktop\\2DAM\\AD\\AE03\\src\\mongoBooks.json")), StandardCharsets.UTF_8);
//					JSONObject book = c.getBookInfo(jsonBooks, txtID.getText());
//					c.createBooks(txtId,txtTitulo, txtAutor, txtAnyoNac, txtAnyoPub, txtEditorial, txtEditorial, txtThumbnail);
//					
//					
//					
//					String info = book.getString("Titulo") + "\n" + book.getString("Autor") + "\n" + book.getInt("Anyo_nacimiento") + "\n" 
//							+ book.getInt("Anyo_publicacion") + "\n" + book.getString("Editorial") + "\n" + book.getInt("Numero_paginas");
//					JOptionPane.showInternalMessageDialog(null,
//		                    info, "Informacion", JOptionPane.INFORMATION_MESSAGE);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
//		
//		btnActulizar.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					String jsonBooks = new String(Files.readAllBytes(Paths.get("C:\\Users\\cesar\\Desktop\\2DAM\\AD\\AE03\\src\\mongoBooks.json")), StandardCharsets.UTF_8);
//					JSONObject book = c.getBookInfo(jsonBooks, txtID.getText());
//					c.createBooks(txtId,txtTitulo, txtAutor, txtAnyoNac, txtAnyoPub, txtEditorial, txtEditorial, txtThumbnail);
//					
//					
//					
//					String info = book.getString("Titulo") + "\n" + book.getString("Autor") + "\n" + book.getInt("Anyo_nacimiento") + "\n" 
//							+ book.getInt("Anyo_publicacion") + "\n" + book.getString("Editorial") + "\n" + book.getInt("Numero_paginas");
//					JOptionPane.showInternalMessageDialog(null,
//		                    info, "Informacion", JOptionPane.INFORMATION_MESSAGE);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});

	}
}
