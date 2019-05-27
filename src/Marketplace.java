import java.util.ArrayList;

public class Marketplace {

	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	private final String ITEM_FILE = "items.bin";
	private final String USER_FILE = "users.bin";

	public Marketplace() {
		// Load data from file(s)
	}

	/**
	 * Add a User to the Marketplace database
	 * 
	 * @param u:
	 *            User object to be added
	 */
	public void addUser(User u) {
		this.users.add(u);
	}

	/**
	 * Delete a User from the database
	 * 
	 * @param u:
	 *            User object to be deleted
	 */
	public void deleteUser(User u) {
		this.users.remove(u);
	}

	/**
	 * Checks if the password matches the saved password
	 * 
	 * @param s:
	 *            Password to check
	 */
	public boolean checkPassword(String s) {
		return false;
	}

	/**
	 * Add an Item object to the database
	 * 
	 * @param i:
	 *            Item object to be added
	 */
	public void addItem(Item i) {
		this.items.add(i);
	}

	/**
	 * Delete an Item object from the databse
	 * 
	 * @param i:
	 *            Item to be deleted
	 */
	public void deleteItem(Item i) {
		this.items.remove(i);
	}

	/**
	 * @return An ArrayList<User> containing all users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @return An ArrayList<Item> contianing all items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Save all current data
	 */
	public void savaData() {

	}

	/**
	 * Load data from file
	 */
	public void loadData() {

	}

}
