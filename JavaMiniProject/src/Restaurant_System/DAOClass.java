package Restaurant_System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DAOClass {

	public static void searchRestaurant()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		System.out.println("Searching Restaurant...");
		DBConnection dbconnect = new DBConnection(
				"jdbc:mysql://localhost:3306/RestaurantDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "malhar");
		Connection con = dbconnect.getConnection();
		String sql = "select * from restaurantDetails where Name=?;";
		PreparedStatement ps = con.prepareStatement(sql);

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Restaurant name for searching: ");
		String name = scanner.next();
		
		ps.setString(1, name);
		boolean b=ps.execute();
		System.out.println(b);
		System.out.println("Success");
		
		ps.close();
		con.close();
		scanner.close();

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

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Restaurant name: ");
		String name = scanner.next();

		System.out.print("Enter opening time : ");
		String openingTime = scanner.next();

		System.out.print("Enter closing time : ");
		String closingTime = scanner.next();

		System.out.print("Enter phone : ");
		String phoneNo = scanner.next();

		System.out.print("Enter Address : ");
		String address = scanner.next();

		System.out.print("Enter cuisine : ");
		String cuisine = scanner.next();

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
	    scanner.close();


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

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Restaurant name: ");
		String name = scanner.next();

		System.out.print("Enter New opening time : ");
		String openingTime = scanner.next();
		
		ps.setString(2, name);
		ps.setString(1, openingTime);
	
		int i=ps.executeUpdate();

		if(i>0)
			System.out.println("Updated Successfully...");
		else 
			System.out.println("No such Record found!!");
		ps.close();
		con.close();
		scanner.close();

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

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Restaurant name to delete : ");
		String name = scanner.next();

		ps.setString(1, name);
		int temp = ps.executeUpdate();
		if (temp == 1)
		{
			System.out.println("Record Deleted Successfully...");
		}
		else
		{
			System.out.println("Sorry no record found");
		}

		
		ps.close();
		con.close();
		scanner.close();
	}

	public static void activateDeactivate() {

	}

}
