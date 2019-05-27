import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Buyer extends User {

	private ArrayList<Item> items;
	private String id;
	

	/**
	 * creates a Buyer object
	 * 
	 * @param username
	 * @param passwordHash
	 * @param email
	 */
	public Buyer(String username, String passwordHash, String email) {
		super(username, passwordHash, email);
		this.id = "03-" + Integer.toString(random.nextInt(89999) + 10000);
	}

	/**
	 * Purchase an Item object from the database
	 * 
	 * @param i:
	 *            Item to be purchased
	 */
	public void purchaseItem(Item i) {
		i.setQuantity(i.getQuantity() - 1);
		this.items.add(i.clone());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		i.setPurchaseTime(dateFormat.format(cal));
	}
}