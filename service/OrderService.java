package service;

import java.util.*;

import model.MessageAndPriceOrderProduct;
import model.Order;
import model.Product;

public class OrderService {
    FileService fileService = new FileService();
    ProductService productService = new ProductService();

    public void order() {
        Scanner sc = new Scanner(System.in);
        int totalItem = 0;
        String productID;
        int orderQuantity;
        String order = "Y";

        List<Product> productOrder = new ArrayList<>();

        UUID uuid = UUID.randomUUID();
        Order order1 = new Order();
        order1.setId(uuid.toString());
        order1.setStatus("Delivering");
        order1.setAccountId(AccountAndInformationService.accountID);

        while (order.equalsIgnoreCase("y") || order.equalsIgnoreCase("yes")) {
            System.out.print("Product ID: ");
            productID = sc.nextLine();
            System.out.print("Quantity: ");
            orderQuantity = new Scanner(System.in).nextInt();
            MessageAndPriceOrderProduct messageAndMoneyOrderProduct = productService.checkProductToOrder(productID, orderQuantity);
            System.out.println(messageAndMoneyOrderProduct.getMessage());

            if (messageAndMoneyOrderProduct.getMessage().equals("Order Successful")) {
                Product product = new Product();
                product.setId(productID);
                product.setAmount(orderQuantity);
                product.setPrice(messageAndMoneyOrderProduct.getPrice());
                product.setTotalMoney(messageAndMoneyOrderProduct.getPrice() * orderQuantity);
                productOrder.add(product);

                order1.setProducts(productOrder);
                order1.setTotalMoney(product.getTotalMoney() + (order1.getTotalMoney() == null ? 0 : order1.getTotalMoney()));
            }
            System.out.print("Do you want to buy more? Y/N: ");
            order = sc.nextLine();
        }
        if (order1.getProducts() != null) {
            String filepath = "src\\data\\Orders.txt";
            List<Order> orders = (List<Order>) fileService.readFile(filepath);
            if (orders == null) {
                orders = new ArrayList<>();
                orders.add(order1);
                fileService.writeFile(filepath, orders);
            } else {
                List<Order> orders1 = (List<Order>) fileService.readFile(filepath);
                orders1.add(order1);
                fileService.writeFile(filepath, orders1);
            }
        }
    }

    public void listOrder() {
        String filepath = "src\\data\\Orders.txt";
        List<Order> orders = (List<Order>) fileService.readFile(filepath);
        if (orders != null) {
            for (Order order : orders) {
                if (AccountAndInformationService.accountID.equals(order.getAccountId())) {
                    System.out.println("Order ID: " + order.getId());
                    System.out.println("Status: " + order.getStatus());
                    System.out.println("Total: " + order.getTotalMoney());
                    if (order.getProducts() != null) {
                        for (Product product : order.getProducts()) {
                            System.out.println(
                                    "Product:(-ID: " + product.getId() + " -Price: " + product.getPrice()
                                            + " -Quantity: "
                                            + product.getAmount() + " -Total Money: " + product.getTotalMoney() + " VND)");
                        }
                    }
                    System.out.println();
                }
            }
        } else {
            System.out.println("There is no order");
        }
    }

    public void listOrderAdmin() {
        String filepath = "src\\data\\Orders.txt";
        List<Order> orders = (List<Order>) fileService.readFile(filepath);
        if (orders != null) {
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getId());
                System.out.println("Member ID: " + order.getAccountId());
                System.out.println("Status: " + order.getStatus());
                System.out.println("Total: " + order.getTotalMoney() + " VND");
                if (order.getProducts() != null) {
                    for (Product product : order.getProducts()) {
                        System.out.println(
                                "Product:(-ID: " + product.getId() + " -Price: " + product.getPrice()
                                        + " -Quantity: "
                                        + product.getAmount() + " -Total Money: " + product.getTotalMoney() + " VND)");
                    }
                    System.out.println();
                }
            }
        } else {
            System.out.println("No order so far...");
        }
    }

    public void listOrderByMemberID() {
        System.out.print("Member ID: ");
        String memberID = new Scanner(System.in).nextLine();
        String filepath = "src\\data\\Orders.txt";
        List<Order> orders = (List<Order>) fileService.readFile(filepath);
        if (orders != null) {
            for (Order order : orders) {
                if (memberID.equals(order.getAccountId())) {
                    System.out.println("Order ID: " + order.getId());
                    System.out.println("Status: " + order.getStatus());
                    System.out.println("Total: " + order.getTotalMoney());
                    if (order.getProducts() != null) {
                        for (Product product : order.getProducts()) {
                            System.out.println(
                                    "Product:(-ID: " + product.getId() + " -Price: " + product.getPrice()
                                            + " -Quantity: "
                                            + product.getAmount() + " -Total Money: " + product.getTotalMoney() + " VND)");
                        }
                    }
                }
                System.out.println();
            }
        }
    }

    public void changeOrderStatus() {
        System.out.print("Order ID: ");
        String orderID = new Scanner(System.in).nextLine();
        String filepath = "src\\data\\Orders.txt";
        List<Order> orders = (List<Order>) fileService.readFile(filepath);
        try {
            for (Order order : orders) {
                if (orderID.equals(order.getId())) {
                    order.setStatus("Delivered");
                    fileService.writeFile(filepath, orders);
                    System.out.println("Order updated");
                }
                break;
            }
        } catch (Exception exception) {
            System.out.println("Update order failed");
        }
    }
}