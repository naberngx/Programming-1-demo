package service;

import java.util.*;

import model.MessageAndPriceOrderProduct;
import model.Product;

public class ProductService {
    FileService fileService = new FileService();

    public void addProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter price (in VND): ");
        Double price = new Scanner(System.in).nextDouble();
        System.out.print("Enter category: ");
        String detail = sc.nextLine();
        System.out.print("Enter quantity: ");
        int amount = new Scanner(System.in).nextInt();

        UUID uuid = UUID.randomUUID();
        Product product = new Product(uuid.toString(), name, price, amount, detail);

        String filepath = "src\\data\\Products.txt";
        List<Product> products = (List<Product>) fileService.readFile(filepath);
        if (products == null) {
            products = new ArrayList<>();
            products.add(product);
            fileService.writeFile(filepath, products);
        } else {
            List<Product> productsR = (List<Product>) fileService.readFile(filepath);
            productsR.add(product);
            fileService.writeFile(filepath, productsR);
        }
    }

    public void getProducts() {
        String filepath = "src\\data\\Products.txt";
        List<Product> productsR = (List<Product>) fileService.readFile(filepath);

        if (productsR != null) {
            for (Product product : productsR) {
                System.out.println(product.toStringProduct());
            }
        } else {
            System.out.println("There is no product to be shown");
        }
    }

    public void getProductsDetail() {
        System.out.print("Enter product id: ");
        String id = new Scanner(System.in).nextLine();
        String filepath = "src\\data\\Products.txt";
        List<Product> productsR = (List<Product>) fileService.readFile(filepath);
        if (productsR != null) {
            for (Product product : productsR) {
                if (id.equals(product.getId())) {
                    System.out.println(product);
                }
            }
        } else {
            System.out.println("There is no product to be shown");
        }
    }

    public void getAvailableProduct() {
        System.out.print("Enter product id: ");
        String id = new Scanner(System.in).nextLine();
        String filepath = "src\\data\\Products.txt";
        List<Product> productsR = (List<Product>) fileService.readFile(filepath);
        if (productsR != null) {
            for (Product product : productsR) {
                if (id.equals(product.getId())) {
                    System.out.println("Product " + product.getName() + "\t" + (product.getAmount() > 0 ? "Available" : "Unavailable"));
                }
            }
        } else {
            System.out.println("There is no product to be shown");
        }
    }

    public void getProductSortByPrice() {
        String filepath = "src\\data\\Products.txt";
        List<Product> productsR = (List<Product>) fileService.readFile(filepath);
        if (productsR != null) {
            productsR.sort(Comparator.comparing(Product::getPrice));
            for (Product product : productsR) {
                System.out.println(product.toStringProduct());
            }
        } else {
            System.out.println("There is no product to be shown");
        }
    }

    public MessageAndPriceOrderProduct checkProductToOrder(String id, int orderQuantity) {
        String filepath = "src\\data\\Products.txt";
        List<Product> productsR = (List<Product>) fileService.readFile(filepath);
        MessageAndPriceOrderProduct messageAndPriceOrderProduct = new MessageAndPriceOrderProduct();
        String message = "Product does not exist";
        Double price = (double) 0;
        if (productsR != null) {
            for (Product product : productsR) {
                if (id.equals(product.getId())) {
                    if (product.getAmount() < orderQuantity) {
                        message = "The store only has " + product.getAmount() + " products left";
                    } else {
                        message = "Order Successful";
                        product.setAmount(product.getAmount() - orderQuantity);
                        price = product.getPrice();
                    }
                    break;
                }
            }
            if (message.equals("Order Successful")) {
                fileService.writeFile(filepath, productsR);
            }
        }
        messageAndPriceOrderProduct.setMessage(message);
        messageAndPriceOrderProduct.setPrice(price);
        return messageAndPriceOrderProduct;
    }

    public void updatePrice() {
        String filepath = "src\\data\\Products.txt";
        List<Product> productsR = (List<Product>) fileService.readFile(filepath);
        System.out.print("Enter product id: ");
        String id = new Scanner(System.in).nextLine();
        System.out.print("Enter price update (in VND): ");
        Double price = new Scanner(System.in).nextDouble();
        if (productsR != null) {
            for (Product product : productsR) {
                if (id.equals(product.getId())) {
                    product.setPrice(price);
                    fileService.writeFile(filepath, productsR);
                    System.out.println("Update price successful");
                    break;
                }
            }
            System.out.println("Not found product with id: " + id);
        } else {
            System.out.println("There is no product to be shown");
        }
    }
}