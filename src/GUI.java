import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

public class GUI extends JFrame {
	
	private final Color BG = new Color(20, 20, 60);
	private final Color BUTTON = new Color(45, 45, 90);
	private final Font DEFAULT = new Font("Segoe UI", Font.PLAIN, 20);
	
	public ArrayList<Item> items = new ArrayList<>();

	private JPanel homeScreen;
	private JPanel createAccount;
	private JPanel loginScreen;
	private JPanel mainScreen;
	
	private JTextField usernameInput;
	private JTextField loginInput;
	private JLabel usernameLabel;
	private JLabel emailLabel;
	private JTextField emailInput;
	private JButton cancelButton;
	private JLabel pwdLabel;
	private JTextField pwdInput;
	private JPasswordField pwdInput2;
	private JTextField pwdLoginInput;
	private JLabel lblIAmA;
	private JRadioButton consumerButton;
	private JRadioButton merchantButton;
	private JRadioButton adminButton;
	private JButton purchaseButton;
	private JTable table;
	private JTextPane descPane;
	
	private BufferedImage xs;
	private BufferedImage small;
	private BufferedImage large;
	
	private User currentUser = null;
	
	private Marketplace m = new Marketplace();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setContentPane(frame.homeScreen);
					frame.setupAccScreen();
					frame.setupHomeScreen();
					frame.setTitle("JMarket");
					frame.setIconImage(new ImageIcon("K:\\Downloads\\jm.ico").getImage());
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		
		items.add(new Item("NETGEAR AC1900 WiFi Range Extender", "Extends WiFi range up to 500 feet", 25, 65.99));
		items.add(new Item("Stainless Steel Keychain", "Heavy duty stainless steel keychain. Made from high grade "
				+ "carefully polished black nickel plated stainless steel. Never rusts or breaks!", 100, 4.99));
		items.add(new Item("7-Piece Phillps Screwdriver Set", "Includes 7 metric-size Phillips screwdrivers.", 50, 12.99));
		items.add(new Item("TieWarehouse Silk Men's Necktie - Red", "3.5\" x 58\", 100% handmade polyester red necktie.", 200, 9.59));
		items.add(new Item("Fidget Cube Toy", "Small plastic cube with objects on each side such as buttons, sliders, etc. "
				+ "Helps relieve stress and anxiety.", 200, 6.95));
		items.add(new Item("Ziploc Sandwich Bags, 90 count", "Plastic sandwhich bags perfect for storing food or small items.", 150, 3.59));
		items.add(new Item("Otterbox DEFENDER for iPhone 7 Plus", "Protects against drops, spills, etc.", 45, 19.95));
		items.add(new Item("OpenTrail 10x42 Binoculars (Green)", "Magnesium alloy body, wide FOV for less movement.", 25, 149.99));
		items.add(new Item("The Weeknd - Starboy (Digital Album)", "Includes MP3 320kbps digital download", 999, 9.99));
		items.add(new Item("Rubik's Cube 3x3x3", "3x3x3 Rubik's Cube puzzle", 60, 10.99));
		items.add(new Item("BigAudio Bluetooth Wireless Over-Ear headphones", "Bluetooth wireless over-ear headphones for comfort + style."
				+ "Great for exercising or just relaxing.", 50, 199.99));
		items.add(new Item("Gucci Supreme Canvas Bag", "We know it's overpriced but you'll buy it anyway.", 10, 499.99));
		setupHomeScreen();
		//setupAccScreen();
		//setupLoginScreen();
		//setupMainScreen();
		
		
	}
	
	private void loadHomeScreen() {
		this.getContentPane().removeAll();
		this.add(this.homeScreen);
		this.setContentPane(this.homeScreen);
		this.validate();
		this.repaint();
		this.setVisible(true);
	}
	
	private void loadAccScreen() {
		this.getContentPane().removeAll();
		this.add(this.createAccount);
		this.setContentPane(this.createAccount);
		this.validate();
		this.repaint();
		this.setVisible(true);
	}
	
	public void loadLoginScreen() {
		this.getContentPane().removeAll();
		this.add(this.loginScreen);
		this.setContentPane(this.loginScreen);
		this.validate();
		this.repaint();
		this.setVisible(true);
	}
	
	public void createUser() {
		
		String username = usernameInput.getText();
		String email = emailInput.getText();
		String password = pwdInput.getText();
		
		String type = "";
		
		JRadioButton[] buttons = {consumerButton, merchantButton, adminButton};
		
		for (JRadioButton r : buttons) {
			if (r.isSelected()) {
				type = r.getText();
			}
		}
		
		System.out.println("Added user:");
		if (type.equals(" Consumer")) {
			Buyer b = new Buyer(username, password, email);
			m.addUser(b);
			System.out.println(b);
		} else if (type.equals(" Merchant")) {
			Seller s = new Seller(username, password, email);
			m.addUser(s);
			System.out.println(s);
		} else if (type.equals(" Admin")) {
			Admin a = new Admin(username, password, email);
			m.addUser(a);
			System.out.println(a);
		}
		
		JOptionPane.showMessageDialog(this, "User has been added.", "Alert", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void login() {
		String username = loginInput.getText();
		String password = pwdLoginInput.getText();
		
		for (User u : m.getUsers()) {
			if (u.getUsername().equals(username)) {
				
				if (u.hashPassword(password).equals(u.getPasswordHash())) {
					currentUser = u;
					System.out.println("Logged in user " + u.getUsername());
				}
				
			}
		}
	}
	
	class CreateUserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setupAccScreen();
			loadAccScreen();
		}
	}
	
	class HomeScreenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setupHomeScreen();
			loadHomeScreen();
		}
	}
	
	class LoginScreenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setupLoginScreen();
			loadLoginScreen();
		}
	}
	
	class LoginButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			login();
		}
	}
	
	class ListSelectListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				
				purchaseButton.setEnabled(true);
				
				// this is very hacked and should probably never be done in a real program but it works so whatever
				String temp = e.getSource().toString().split("\\{")[1];
				temp = temp.substring(0, temp.length() - 1);
				int index = Integer.parseInt(temp);
				
				String item = (String) table.getModel().getValueAt(index, 0);
				Item selectedItem = itemFromString(item.substring(4));
				
				descPane.setText("Name:\r\n" + selectedItem.getName() + "\r\n\r\nDescription:\r\n" + selectedItem.getDesc()
				+ "\r\n\r\nItem ID:\r\n" + selectedItem.getID() + "\r\n\r\nSeller ID:\r\n" + selectedItem.getSellerID());
				
			}
		}
	}
	
	class SubmitAccListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			createUser();
			setupHomeScreen();
			loadHomeScreen();
		}
	}
	
	public void setFieldLabelLayout(JLabel l) {
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	}
	
	public void setLabelLayout(JLabel l) {
		l.setForeground(Color.WHITE);
		l.setFont(DEFAULT);
	}
	
	public void setButtonLayout(JButton b) {
		b.setForeground(new Color(240, 248, 255));
		b.setFont(DEFAULT);
		b.setBackground(BUTTON);
		b.setBorderPainted(false);
		b.setFocusPainted(false);
	}
	
	public void setRadioButtonLayout(JRadioButton r) {
		r.setForeground(Color.WHITE);
		r.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		r.setBackground(new Color(20, 20, 60));
		r.setBorderPainted(false);
		r.setFocusPainted(false);
	}
	
	public void setTextFieldLayout(JTextField t, String s) {
		t.setForeground(Color.WHITE);
		t.setCaretColor(Color.WHITE);
		t.setToolTipText(s);
		t.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		t.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));
		t.setBackground(BUTTON);
	}
	
	public String[] itemToArray(Item i) {
		String[] result = new String[3];
		result[0] = "    " + i.getName();
		result[1] = "$" + Double.toString(i.getPrice());
		result[2] = Integer.toString(i.getQuantity()) + " x     ";
		return result;
	}
	
	public Item itemFromString(String s) {
		for (Item i : items) {
			if (i.getName().equals(s)) {
				return i;
			}
		}
		return null;
	}
	
	public void setupHomeScreen() {
		
		homeScreen = new JPanel();
		homeScreen.setBackground(BG);
		homeScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		homeScreen.setLayout(null);

		ActionListener CreateAcc = new CreateUserListener();
		JButton signUpButton = new JButton("Create an account");
		signUpButton.setBounds(413, 477, 208, 47);
		signUpButton.addActionListener(CreateAcc);
		setButtonLayout(signUpButton);
		
		
		homeScreen.add(signUpButton);
		
		ActionListener LoginListener = new LoginScreenListener();
		JButton loginButton = new JButton("Log in");
		loginButton.setBounds(679, 477, 208, 47);
		loginButton.addActionListener(LoginListener);
		setButtonLayout(loginButton);
		
		homeScreen.add(loginButton);
		
		try {
			large = ImageIO.read(new File("Jmarket.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel logoLabel = new JLabel(new ImageIcon(large));
		logoLabel.setBounds(359, 217, 581, 202);
		homeScreen.add(logoLabel);
	}
	
	public void setupAccScreen() {
		createAccount = new JPanel();
		createAccount.setBackground(BG);
		createAccount.setBorder(new EmptyBorder(5, 5, 5, 5));
		createAccount.setLayout(null);
		
		ActionListener CreateAccountListener = new SubmitAccListener();
		JButton createAccSubmitButton = new JButton("Submit");
		createAccSubmitButton.setBounds(987, 616, 158, 47);
		createAccSubmitButton.addActionListener(CreateAccountListener);
		setButtonLayout(createAccSubmitButton);
		
		createAccount.add(createAccSubmitButton);
		
		JLabel lblCreateAnAccount = new JLabel("Create an account");
		lblCreateAnAccount.setForeground(new Color(255, 255, 255));
		lblCreateAnAccount.setBackground(new Color(255, 255, 255));
		lblCreateAnAccount.setFont(new Font("Segoe UI", Font.PLAIN, 38));
		lblCreateAnAccount.setBounds(751, 57, 349, 76);
		createAccount.add(lblCreateAnAccount);
		
		try {
			small = ImageIO.read(new File("Jmarket-small.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel logoLabelSmall = new JLabel(new ImageIcon(small));
		logoLabelSmall.setBounds(116, 306, 454, 129);
		createAccount.add(logoLabelSmall);
		
		usernameInput = new JTextField(20);
		usernameInput.setBounds(868, 146, 277, 47);
		setTextFieldLayout(usernameInput, "Username");
		createAccount.add(usernameInput);
		
		usernameLabel = new JLabel("Username");
		setFieldLabelLayout(usernameLabel);
		usernameLabel.setBounds(751, 159, 78, 22);
		createAccount.add(usernameLabel);
		
		emailLabel = new JLabel("Email address");
		emailLabel.setBounds(725, 214, 104, 22);
		setFieldLabelLayout(emailLabel);
		createAccount.add(emailLabel);
		
		emailInput = new JTextField(20);
		emailInput.setBounds(868, 204, 277, 47);
		setTextFieldLayout(emailInput, "Email address");
		createAccount.add(emailInput);
		
		ActionListener homeScreen = new HomeScreenListener();
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(795, 616, 158, 47);
		setButtonLayout(cancelButton);
		cancelButton.addActionListener(homeScreen);
		createAccount.add(cancelButton);
		
		pwdLabel = new JLabel("Password");
		pwdLabel.setBounds(758, 274, 71, 22);
		setFieldLabelLayout(pwdLabel);
		createAccount.add(pwdLabel);
		
		pwdInput = new JPasswordField(20);
		pwdInput.setBounds(868, 262, 277, 47);
		setTextFieldLayout(pwdInput, "Password");
		createAccount.add(pwdInput);
		
		pwdInput2 = new JPasswordField(20);
		pwdInput2.setBounds(868, 320, 277, 47);
		setTextFieldLayout(pwdInput2, "Confirm password");
		createAccount.add(pwdInput2);
		
		JLabel pwdLabel2 = new JLabel("Confirm password");
		pwdLabel2.setBounds(690, 332, 142, 22);
		setFieldLabelLayout(pwdLabel2);
		createAccount.add(pwdLabel2);
		
		lblIAmA = new JLabel("I am a...");
		lblIAmA.setForeground(Color.WHITE);
		lblIAmA.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		lblIAmA.setBackground(Color.WHITE);
		lblIAmA.setBounds(751, 400, 110, 47);
		createAccount.add(lblIAmA);
		
		consumerButton = new JRadioButton(" Consumer");
		consumerButton.setBounds(807, 461, 187, 25);
		setRadioButtonLayout(consumerButton);
		createAccount.add(consumerButton);
		
		merchantButton = new JRadioButton(" Merchant");
		merchantButton.setBounds(807, 501, 187, 25);
		setRadioButtonLayout(merchantButton);
		createAccount.add(merchantButton);
		
		adminButton = new JRadioButton(" Administrator");
		adminButton.setBounds(807, 546, 187, 25);
		setRadioButtonLayout(adminButton);
		createAccount.add(adminButton);
		
		ButtonGroup accTypeButtons = new ButtonGroup();
		accTypeButtons.add(consumerButton);
		accTypeButtons.add(merchantButton);
		accTypeButtons.add(adminButton);
		
	}
	
	public void setupLoginScreen() {
		loginScreen = new JPanel();
		loginScreen.setBackground(BG);
		loginScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		loginScreen.setLayout(null);
		
		ActionListener Login = new LoginButtonListener();
		JButton createAccSubmitButton = new JButton("Submit");
		createAccSubmitButton.setBounds(957, 430, 158, 47);
		createAccSubmitButton.addActionListener(Login);
		setButtonLayout(createAccSubmitButton);
		
		loginScreen.add(createAccSubmitButton);
		
		JLabel lblCreateAnAccount = new JLabel("Log in");
		lblCreateAnAccount.setForeground(new Color(255, 255, 255));
		lblCreateAnAccount.setBackground(new Color(255, 255, 255));
		lblCreateAnAccount.setFont(new Font("Segoe UI", Font.PLAIN, 38));
		lblCreateAnAccount.setBounds(734, 188, 349, 76);
		loginScreen.add(lblCreateAnAccount);
		
		try {
			small = ImageIO.read(new File("Jmarket-small.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel logoLabelSmall = new JLabel(new ImageIcon(small));
		logoLabelSmall.setBounds(147, 306, 454, 129);
		loginScreen.add(logoLabelSmall);
		
		loginInput = new JTextField(20);
		loginInput.setBounds(851, 277, 277, 47);
		setTextFieldLayout(loginInput, "Username");
		loginScreen.add(loginInput);
		
		usernameLabel = new JLabel("Username");
		setFieldLabelLayout(usernameLabel);
		usernameLabel.setBounds(734, 290, 78, 22);
		loginScreen.add(usernameLabel);
		
		ActionListener homeScreen = new HomeScreenListener();
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(765, 430, 158, 47);
		setButtonLayout(cancelButton);
		cancelButton.addActionListener(homeScreen);
		loginScreen.add(cancelButton);
		
		pwdLabel = new JLabel("Password");
		pwdLabel.setBounds(734, 349, 71, 22);
		setFieldLabelLayout(pwdLabel);
		loginScreen.add(pwdLabel);
		
		pwdLoginInput = new JPasswordField(20);
		pwdLoginInput.setBounds(851, 337, 277, 47);
		setTextFieldLayout(pwdLoginInput, "Password");
		loginScreen.add(pwdLoginInput);

	}
	
	public void setupMainScreen() {
		
		mainScreen = new JPanel();
		mainScreen.setBackground(BG);
		mainScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainScreen.setLayout(null);
		
		try {
			xs = ImageIO.read(new File("Jmarket-xs.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel logoLabelXS = new JLabel(new ImageIcon(xs));
		logoLabelXS.setBounds(54, 47, 200, 57);
		mainScreen.add(logoLabelXS);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(303, 47, 158, 47);
		setButtonLayout(searchButton);
		mainScreen.add(searchButton);
		
		purchaseButton = new JButton("Purchase selected");
		purchaseButton.setForeground(new Color(240, 248, 255));
		purchaseButton.setFont(DEFAULT);
		purchaseButton.setFocusPainted(false);
		purchaseButton.setEnabled(false);
		purchaseButton.setBorderPainted(false);
		purchaseButton.setBackground(new Color(45, 45, 90));
		purchaseButton.setBounds(487, 47, 193, 47);
		mainScreen.add(purchaseButton);
		
		String[] columns = {"Item", "Price", "Stock"};
		Object[][] data = new Object[items.size()][3];
		
		for (int i = 0; i < items.size(); i++) {
			data[i] = itemToArray(items.get(i));
		}
		
		table = new JTable(data, columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		ListSelectListener lsl = new ListSelectListener();
		table.getSelectionModel().addListSelectionListener(lsl);
		
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		right.setHorizontalAlignment(JLabel.RIGHT);
		right.setBorder(new EmptyBorder(5, 5, 5, 5));
		table.getColumnModel().getColumn(2).setCellRenderer(right);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setTableHeader(null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setBounds(0, 0, 705, 300);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setForeground(Color.WHITE);
		table.setBackground(BUTTON);
		table.setFont(DEFAULT);
		table.setRowHeight(60);
		table.getColumnModel().getColumn(0).setPreferredWidth(500);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(53, 180, 705, 519);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getViewport().setBackground(BUTTON);
		mainScreen.add(scroll);
		
		JLabel itemLabel = new JLabel("Item");
		itemLabel.setBounds(70, 137, 66, 27);
		setLabelLayout(itemLabel);
		mainScreen.add(itemLabel);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setForeground(Color.WHITE);
		priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		priceLabel.setBounds(562, 137, 66, 27);
		mainScreen.add(priceLabel);
		
		JLabel lblInStock = new JLabel("In Stock");
		lblInStock.setForeground(Color.WHITE);
		lblInStock.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblInStock.setBounds(660, 137, 98, 27);
		mainScreen.add(lblInStock);
		
		descPane = new JTextPane();
		descPane.setEditable(false);
		descPane.setText("");
		descPane.setBackground(BUTTON);
		descPane.setForeground(Color.WHITE);
		descPane.setFont(DEFAULT);
		descPane.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
		descPane.setBounds(787, 180, 444, 519);
		mainScreen.add(descPane);
		
		JLabel lblSelectedItemInfo = new JLabel("Item information");
		lblSelectedItemInfo.setForeground(Color.WHITE);
		lblSelectedItemInfo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSelectedItemInfo.setBounds(789, 137, 183, 27);
		mainScreen.add(lblSelectedItemInfo);
		
		JLabel usernameAtTop = new JLabel("Welcome, fergusch");
		usernameAtTop.setBounds(1049, 47, 182, 38);
		setLabelLayout(usernameAtTop);
		mainScreen.add(usernameAtTop);
		
	}
}
