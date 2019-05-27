import java.util.Random;

abstract class User {

	Random random = new Random();

	private String username;
	private String passwordHash;
	private String email;
	private String type;
	private String id;

	public User(String username, String password, String email) {
		this.username = username;
		this.passwordHash = hashPassword(password);
		this.email = email;
	}

	/**
	 * Hash a password
	 * 
	 * @param p:
	 *            password to hash
	 * @return hash string
	 */
	public String hashPassword(String p) {
		return Integer.toString(Math.abs((Integer.toString(p.hashCode()) + "3A").hashCode()) / 256);
	}

	/**
	 * @return User's username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Give the user a username
	 * 
	 * @param s:
	 *            Username to be set
	 */
	public void setUsername(String s) {
		this.username = s;
	}
	
	public String getPasswordHash() {
		return this.passwordHash;
	}

	/**
	 * Set the user's password
	 * 
	 * @param s:
	 *            Name to be set
	 */
	public void setPassword(String s) {
		this.passwordHash = hashPassword(s);
	}

	/**
	 * @return User's ID
	 */
	public String getID() {
		return this.id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String toString() {
		return "Username: " + this.username + "\nEmail: " + this.email;
	}
}