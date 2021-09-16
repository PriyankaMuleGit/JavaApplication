
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DAOClass {

	public static Scanner scanner = new Scanner(System.in);

	public static void searchRestaurant()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		System.out.println("Searching Restaurant...");
		DBConnection dbconnect = new DBConnection(
				"jdbc:mysql://localhost:3306/RestaurantDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "malhar");
		Connection con = dbconnect.getConnection();

		System.out.print("Enter Restaurant name for searching: ");
		String name = scanner.next();

		String sql = "select * from restaurantDetails where Name=?;";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		if (rs.next() == false)
			System.out.println("Record not Found!!!!");
		else
			System.out.println("Record Found!!!!");
		ps.close();
		con.close();
	}

	public static void addRestaurant()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println("Adding Restaurant...");
		DBConnection dbconnect = new DBConnection(
				"jdbc:mysql://localhost:3306/RestaurantDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false"
						+ "&serverTimezone=UTC",
				"root", "malhar");
		Connection con = dbconnect.getConnection();
		String sql = "insert into restaurantDetails values (?,?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(sql);

		Validation v = new Validation();

		int check = 0;

		String phoneNo = null, cuisine = null, openingTime = null, closingTime = null;

		System.out.print("Enter Restaurant name: ");
		String name = scanner.next();

		System.out.print("Enter Address : ");
		String address = scanner.next();

		boolean t = false;
		do {
			check = 0;

			if (!t) {
				check = 1;
				System.out.print("Enter opening Time in hh:mm Format : ");
				openingTime = scanner.next();
				t = v.isValidTime(openingTime);
			} else {
				System.out.print("Enter closing Time in hh:mm Format : ");
				closingTime = scanner.next();
				boolean ct = v.isValidTime(closingTime);
				if (!ct) {
					check = 2;
				}
			}

			if (check == 1 && !t) {
				System.out.println("Sorry opening Time is Invalid!!");
			} else if (check == 2) {
				System.out.println("Sorry closing Time is Invalid!!");
			}

		} while (check != 0);

		boolean b = false;

		do {
			check = 0;

			if (!b) {
				check = 1;
				System.out.print("Enter phone : ");
				phoneNo = scanner.next();
				b = v.isValid(phoneNo);
			} else {
				System.out.print("Enter cuisine Italian/Indian/Chinese/Mexican : ");
				cuisine = scanner.next();
				boolean c = v.checkCuisine(cuisine);
				if (!c) {
					check = 2;
				}
			}

			if (check == 1 && !b) {
				System.out.println("Sorry Phone Number is Invalid!!");
			} else if (check == 2) {
				System.out.println("Invalid cuisine!!");
			}

		} while (check != 0);

		System.out.println();

		ps.setString(1, name);
		ps.setString(2, openingTime);
		ps.setString(3, closingTime);
		ps.setString(4, phoneNo);
		ps.setString(5, address);
		ps.setString(6, cuisine);
		ps.executeUpdate();

		System.out.println("Record Inserted Successfully...");
		ps.close();
		con.close();

	}

	public static void updateDetailsRes()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println("Updating opening and Closing time of Restaurant...");
		DBConnection dbconnect = new DBConnection(
				"jdbc:mysql://localhost:3306/RestaurantDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false"
						+ "&serverTimezone=UTC",
				"root", "malhar");
		Connection con = dbconnect.getConnection();
		String sql = "update restaurantDetails set openingTime=? where name=?;";
		PreparedStatement ps = con.prepareStatement(sql);

		System.out.print("Enter Restaurant name: ");
		String name = scanner.next();

		System.out.print("Enter New opening time : ");
		String openingTime = scanner.next();

		ps.setString(2, name);
		ps.setString(1, openingTime);

		int i = ps.executeUpdate();

		if (i > 0)
			System.out.println("Updated Successfully...");
		else
			System.out.println("No such Record found!!");
		ps.close();
		con.close();
	}

	public static void deleteRestaurant()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println("Deleting record from Restaurant details...");
		DBConnection dbconnect = new DBConnection(
				"jdbc:mysql://localhost:3306/RestaurantDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false"
						+ "&serverTimezone=UTC",
				"root", "malhar");
		Connection con = dbconnect.getConnection();
		String sql = "delete from restaurantDetails where name=?;";
		PreparedStatement ps = con.prepareStatement(sql);

		System.out.print("Enter Restaurant name to delete : ");
		String name = scanner.next();

		ps.setString(1, name);
		int temp = ps.executeUpdate();
		if (temp == 1) {
			System.out.println("Record Deleted Successfully...");
		} else {
			System.out.println("Sorry no record found");
		}

		ps.close();
		con.close();
	}

	public static void viewRestaurantDetails()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println("View Restaurant details...");
		DBConnection dbconnect = new DBConnection(
				"jdbc:mysql://localhost:3306/RestaurantDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false"
						+ "&serverTimezone=UTC",
				"root", "malhar");
		Connection con = dbconnect.getConnection();
		String sql = "select * from restaurantDetails;";
		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		System.out.println("----------------------------------------------------");
		while (rs.next()) {
			String name = rs.getString(1);
			String openingTime = rs.getString(2);
			String closingTime = rs.getString(3);
			String phoneNo = rs.getString(4);
			String address = rs.getString(5);
			String cuisine = rs.getString(6);

			System.out.println(
					name + "\t" + openingTime + "\t" + closingTime + "\t" + phoneNo + "\t" + address + "\t" + cuisine);
		}

		ps.close();
		con.close();
	}

}
