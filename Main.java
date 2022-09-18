import java.util.Scanner;

import resource.Menu;
import service.AccountAndInformationService;
import service.OrderService;
import service.ProductService;

public class Main {
    static int customerSelection;
    static boolean loginSuccess = true;

    public static void main(String[] args) {
        AccountAndInformationService accountAndInformationService = new AccountAndInformationService();
        accountAndInformationService.autoGenerateAccountAdmin();
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();

        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        intFunction(menu);

        int choice;
        while (true) {
            switch (customerSelection) {
                case 1 -> {
                    System.out.print(menu.menu(1));
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            System.out.println("Your choice: 1. Register to become a member");
                            accountAndInformationService.register();
                        }
                        case 2 -> {
                            System.out.println("Your choice: 2. View information");
                            accountAndInformationService.viewInformation();
                        }
                        case 3 -> {
                            System.out.println("Your choice: 3. View products");
                            productService.getProducts();
                        }
                        case 4 -> {
                            System.out.println("Your choice: 4. View product detail");
                            productService.getProductsDetail();
                        }
                        case 5 -> {
                            System.out.println("Your choice: 5. Search available products");
                            productService.getAvailableProduct();
                        }
                        case 6 -> {
                            System.out.println("Your choice: 6. Sort all product by price");
                            productService.getProductSortByPrice();
                        }
                        case 0 -> intFunction(menu);
                    }
                }
                case 2 -> {
                    if (loginSuccess) {
                        accountAndInformationService.login(1);
                        loginSuccess = false;
                    }
                    System.out.print(menu.menu(2));
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            System.out.println("Your choice: 1. View information");
                            accountAndInformationService.viewInformation();
                        }
                        case 2 -> {
                            System.out.println("Your choice: 2. View products");
                            productService.getProducts();
                        }
                        case 3 -> {
                            System.out.println("Your choice: 3. View product detail");
                            productService.getProductsDetail();
                        }
                        case 4 -> {
                            System.out.println("Your choice: 4. Search available products");
                            productService.getAvailableProduct();
                        }
                        case 5 -> {
                            System.out.println("Your choice: 5. Sort all products by price");
                            productService.getProductSortByPrice();
                        }
                        case 6 -> {
                            System.out.println("Your choice: 6. Order");
                            orderService.order();
                        }
                        case 7 -> {
                            System.out.println("Your choice: 7. List order");
                            orderService.listOrder();
                        }
                        case 0 -> intFunction(menu);
                    }
                }
                case 3 -> {
                    if (loginSuccess) {
                        accountAndInformationService.login(0);
                        loginSuccess = false;
                    }
                    System.out.print(menu.menu(3));
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            System.out.println("Your choice: 1. View information");
                            accountAndInformationService.viewInformation();
                        }
                        case 2 -> {
                            System.out.println("Your choice: 2. View products");
                            productService.getProducts();
                        }
                        case 3 -> {
                            System.out.println("Your choice: 3. View product detail");
                            productService.getProductsDetail();
                        }
                        case 4 -> {
                            System.out.println("Your choice: 4. View orders");
                            orderService.listOrderAdmin();
                        }
                        case 5 -> {
                            System.out.println("Your choice: 5. View member");
                            accountAndInformationService.getMembers();
                        }
                        case 6 -> {
                            System.out.println("Your choice: 6. Add new Product");
                            productService.addProduct();
                        }
                        case 7 -> {
                            System.out.println("Your choice: 7. Update price");
                            productService.updatePrice();
                        }
                        case 8 -> {
                            System.out.println("Your choice: 8. Get orders by member ID");
                            orderService.listOrderByMemberID();
                        }
                        case 9 -> {
                            System.out.println("Your choice: 9. Change status of order");
                            orderService.changeOrderStatus();
                        }
                        case 0 -> intFunction(menu);
                    }
                }
                case 4 -> {
                    System.out.println("\nThank you for using our service");
                    System.exit(0);
                }
            }
        }
    }

    public static void intFunction(Menu menu) {
        customerSelection = 0;
        while (customerSelection != 1 && customerSelection != 2 && customerSelection != 3 && customerSelection != 4) {
            try {
                System.out.print(menu.menu(0));
                customerSelection = new Scanner(System.in).nextInt();
                loginSuccess = true;
            } catch (Exception e) {
                System.out.println("\nInput must be a number");
            }
        }
    }
}