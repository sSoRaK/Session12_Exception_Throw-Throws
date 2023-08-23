package ra.run;

import ra.entity.Categories;
import ra.entity.Products;

import java.util.*;

public class ShopManagement {
    public static List<Categories> categoriesList = new ArrayList<>();
    public static List<Products> productsList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        Categories categories = new Categories();
//        Products products = new Products();
        do {
            System.out.println("************************* SHOP MANAGEMENT *************************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    boolean catalogMenu = true;
                    do {
                        System.out.println("************************** CATALOG MANAGEMENT *************************");
                        System.out.println("1. Thêm mới danh mục");
                        System.out.println("2. Hiển thị thông tin các danh mục");
                        System.out.println("3. Cập nhật tên danh mục theo mã danh mục");
                        System.out.println("4. Xóa danh mục theo mã danh mục (Danh mục chưa chứa sản phẩm)");
                        System.out.println("5. Thoát (Quay lại Shop Management)");
                        System.out.print("Lựa chọn của bạn: ");
                        int choiceCatalogMenu = Integer.parseInt(scanner.nextLine());
                        switch (choiceCatalogMenu) {
                            case 1:
                                ShopManagement.inputDataCatalog();
                                break;
                            case 2:
                                ShopManagement.displayDataCatalog();
                                break;
                            case 3:
                                ShopManagement.updateNameById();
                                break;
                            case 4:
                                ShopManagement.removeCatalogNameById();
                                break;
                            case 5:
                                System.out.println("Quay lại Shop Management");
                                catalogMenu = false;
                                break;
                            default:
                                System.err.println("Vui lòng chọn lựa chọn từ 1 - 5");
                                break;
                        }
                    } while (catalogMenu);
                    break;
                case 2:
                    boolean productMenu = true;
                    do {
                        System.out.println("************************** PRODUCT MANAGEMENT *************************");
                        System.out.println("1. Thêm mới sản phẩm (Khi thêm cho phép chọn danh mục sản phẩm mà sản phẩm thuộc về)");
                        System.out.println("2. Hiển thị thông tin sản phẩm");
                        System.out.println("3. Cập nhật giá sản phẩm theo mã sản phẩm");
                        System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
                        System.out.println("5. Sắp xếp sản phẩm theo giá sản phẩm tăng dần");
                        System.out.println("6. Sắp xếp sản phẩm theo tên tăng dần");
                        System.out.println("7. Thống kê số lượng sản phẩm theo danh mục sản phẩm");
                        System.out.println("8. Tìm kiếm sản phẩm theo tên sản phẩm");
                        System.out.println("9. Thoát (Quay lại Shop Management)");
                        System.out.print("Lựa chọn của bạn: ");
                        int choiceProductMenu = Integer.parseInt(scanner.nextLine());
                        switch (choiceProductMenu) {
                            case 1:
                                ShopManagement.displayDataCatalog();
                                ShopManagement.inputDataProduct();
                                break;
                            case 2:
                                ShopManagement.displayDataProduct();
                                break;
                            case 3:
                                ShopManagement.updatePriceById();
                                break;
                            case 4:
                                ShopManagement.removeProductById();
                                break;
                            case 5:
                                System.out.println("\t\t\t\t\t\t\t\t\tProducts List (Sort Price Ascending)");
                                ShopManagement.sortProductPrice();
                                break;
                            case 6:
                                System.out.println("\t\t\t\t\t\t\t\t\tProducts List (Sort Product Name Ascending)");
                                ShopManagement.sortProductName();
                                break;
                            case 7:
                                System.out.println("\t\t\t\t\tStatistical number of products by category");
                                ShopManagement.quantityStats(productsList, categoriesList);
                                break;
                            case 8:
                                ShopManagement.searchProductName();
                                break;
                            case 9:
                                System.out.println("Quay lại Shop Management");
                                productMenu = false;
                                break;
                            default:
                                System.err.println("Vui lòng chọn lựa chọn từ 1 - 9");
                                break;
                        }
                    } while (productMenu);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng chọn lựa chọn từ 1 - 3");
                    break;
            }
        } while (true);
    }

    private static void inputDataCatalog() {
        System.out.print("Nhập số lượng danh mục cần nhập thông tin: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập thông tin danh mục " + (i + 1) + "\n");
            Categories category = new Categories();
            category.inputData(scanner, categoriesList);
            categoriesList.add(category);
        }
        System.out.println("Đã thêm danh mục thành công!");
    }

    private static void displayDataCatalog() {
        System.out.println("\t\t\t\t\tCategories List");
        System.out.printf("%-15s%-25s%-20s\n", "ID Categories", "Categories Name", "Status");
        for (Categories category : categoriesList) {
            category.displayData();
        }
    }

    private static void updateNameById() {
        System.out.print("Nhập mã danh mục cập nhật: ");
        int updateCatalogId = Integer.parseInt(scanner.nextLine());
        for (Categories category : categoriesList) {
            if (category.getCatalogId() == updateCatalogId) {
                System.out.print("Nhập tên danh mục mới: ");
                String newCatalogName = scanner.nextLine();
                category.updateCatalogName(newCatalogName);
            }
        }
        System.out.println("Cập nhật tên danh mục thành công!");
    }

    private static void removeCatalogNameById() {
        System.out.print("Nhập mã danh mục muốn xóa: ");
        int removeCatalogById = Integer.parseInt(scanner.nextLine());
        boolean checkExist = false;
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getCatalogId() == removeCatalogById) {
                if (!isExistProductInCategories(categoriesList.get(i), productsList)) {
                    categoriesList.remove(removeCatalogById - 1);
                    System.out.println("Đã xóa danh mục.");
                } else {
                    System.err.println("Danh mục đang chứa sản phẩm không thể xóa!");
                }
                checkExist = true;
            }
        }
        if (!checkExist) {
            System.err.println("Không tìm thấy danh mục [ " + removeCatalogById + " ] trong danh sách.");
        }
    }

    public static boolean isExistProductInCategories(Categories category, List<Products> productsList) {
        for (Products product : productsList) {
            if (product.getCatalogId() == category.getCatalogId()) {
                return true;
            }
        }
        return false;
    }

    private static void inputDataProduct() {
        System.out.print("Nhập số lượng sản phẩm cần nhập thông tin: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập thông tin sản phẩm " + (i + 1) + "\n");
            Products product = new Products();
            product.inputData(scanner, productsList);
            productsList.add(product);
        }
    }

    private static void displayDataProduct() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tProducts List");
        System.out.printf("%-15s%-15s%-25s%-20s%-35s%-20s\n", "ID Categories", "ID Product", "Categories Name", "Price", "Title", "Status");
        for (Products product : productsList) {
            product.displayData();
        }
    }

    public static void updatePriceById() {
        System.out.print("Nhập mã sản phẩm muốn cập nhật giá: ");
        String productId = scanner.nextLine();
        for (Products product : productsList) {
            if (product.getProductId().equals(productId)) {
                System.out.print("Nhập giá sản phẩm mới: ");
                float newPrice = Float.parseFloat(scanner.nextLine());
                product.updatePrice(newPrice);
            } else {
                System.err.printf("Không tìm thấy mã sản phẩm [ " + productId + " ] trong danh sách!");
            }
        }
    }

    public static void removeProductById() {
        System.out.print("Nhập mã sản phẩm muốn xóa: ");
        String removeProductId = scanner.nextLine();
        boolean checkRemove = false;
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getProductId().equals(removeProductId)) {
                productsList.remove(productsList.get(i));
                System.out.println("Đã xóa sản phẩm.");
                checkRemove = true;
            }
        }
        if (!checkRemove) {
            System.err.println("Không có sản phẩm [ " + removeProductId + " ] trong danh sách.");
        }
    }

    public static void sortProductPrice() {
        List<Products> sortProducts = new ArrayList<>(productsList);
        Collections.sort(sortProducts, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return Float.compare(o1.getPrice(), o2.getPrice());
            }
        });
        System.out.printf("%-15s%-15s%-25s%-20s%-35s%-20s\n", "ID Categories", "ID Product", "Categories Name", "Price", "Title", "Status");
        for (Products product : sortProducts) {
            product.displayData();
        }
    }

    public static void sortProductName() {
        List<Products> sortProductsName = new ArrayList<>(productsList);
        Collections.sort(sortProductsName, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return o2.getProductName().compareTo(o1.getProductName());
            }
        });
        System.out.printf("%-15s%-15s%-25s%-20s%-35s%-20s\n", "ID Categories", "ID Product", "Categories Name", "Price", "Title", "Status");
        for (Products product : sortProductsName) {
            product.displayData();
        }
    }

    public static void quantityStats(List<Products> productsList, List<Categories> categoriesList) {
        if (productsList.isEmpty()) {
            System.err.println("Không có sản phẩm nào trong danh sách!");
            return;
        }
        for (Categories category : categoriesList) {
            int count = 0;
            for (Products product : productsList) {
                if (product.getCatalogId() == category.getCatalogId()) {
                    count++;
                }
            }
            System.out.printf("%-15s%-25s%-15s%-10d\n", "Categories: ", category.getCatalogName(), "Quantity: ", count);
        }
    }

    public static void searchProductName() {
        System.out.print("Nhập tên sản phẩm cần tìm kiếm: ");
        String searchName = scanner.nextLine();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tProducts List");
        System.out.printf("%-15s%-15s%-25s%-20s%-35s%-20s\n", "ID Categories", "ID Product", "Categories Name", "Price", "Title", "Status");
        boolean checkSearch = false;
        for (Products product : productsList) {
            if (product.getProductName().toLowerCase().contains(searchName.toLowerCase())) {
                product.displayData();
                checkSearch = true;
            }
        }
        if (!checkSearch) {
            System.err.println("Không tìm thấy sản phẩm [" + searchName + "] trong danh sách.");
        }
    }
}
