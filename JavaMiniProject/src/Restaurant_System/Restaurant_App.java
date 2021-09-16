//package Restaurant_System;

import java.sql.SQLException;
import java.util.Scanner;

public class Restaurant_App {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		int ch1;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("\n\n-----------------MENU------------------\n");
			System.out.println("0. To exit the program..");
			System.out.println("1 .Search restaurant");
			System.out.println("2. Add new restaurant");
			System.out.println("3. Update details of a restaurant");
			System.out.println("4. Delete restaurant");
			System.out.println("5. To View the Restaurant table..");

			System.out.println();

			System.out.println("Enter the choice from above :");
			ch1 = scan.nextInt();
			System.out.println();
			switch (ch1) {
			case 1:
				DAOClass.searchRestaurant();
				break;

			case 2:
				DAOClass.addRestaurant();
				break;

			case 3:
				DAOClass.updateDetailsRes();
				break;

			case 4:
				DAOClass.deleteRestaurant();
				break;

			case 5:
				DAOClass.viewRestaurantDetails();
				break;

			}

		} while (ch1 != 0);

		scan.close();
	}

}
