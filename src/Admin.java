
public class Admin extends User {

	private String type;

	/**
	 * Creates an Admin object
	 * 
	 * @param username
	 * @param password
	 * @param email
	 */
	public Admin(String username, String password, String email) {
		super(username, password, email);
		this.type = "Admin";
	}

	/**
	 * gets the User info
	 * 
	 * @param u
	 * @return
	 */
	public String getUserInfo(User u) {
		return null;
	}

	/**
	 * gets the item info
	 * 
	 * @param i
	 * @return
	 */
	public String getItemInfo(Item i) {
		return null;
	}

	/**
	 * Update item attributes
	 * 
	 * @param s:
	 *            Content to replace current item information
	 */
	public void updateItemInfo(String s) {

	}

	/**
	 * Change current username
	 * 
	 * @param s:
	 *            New username
	 */
	public void changeUserName(String s) {

	}

}