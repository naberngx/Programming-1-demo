package resource;

public class Menu {
    public String menu(int menu) {
        String menuContent = "";

        switch (menu) {
            case 0 -> menuContent = """
                    1. Customer
                    2. Member
                    3. Admin
                    4. Exit
                    """;
            case 1 -> menuContent = """
                    1. Register to become a member
                    2. View information
                    3. View products
                    4. View product detail
                    5. Search available products
                    6. Sort all products by price
                    0. Back
                    """;
            case 2 -> menuContent = """
                    1. View information
                    2. View products
                    3. View product detail
                    4. Search available products
                    5. Sort all products by price
                    6. Order
                    7. List orders
                    0. Back
                    """;
            case 3 -> menuContent = """
                    1. View information
                    2. View products
                    3. View product detail
                    4. View orders
                    5. View members
                    6. Add new Products
                    7. Update prices
                    8. Get orders by member's ID
                    9. Change orders' status
                    0. Back
                    """;
            case 4 -> System.exit(0);
        }
        return menuContent + "\nEnter your choice: ";
    }
}