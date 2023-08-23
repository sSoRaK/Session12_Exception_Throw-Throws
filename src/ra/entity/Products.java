package ra.entity;

import ra.IShop;

import java.util.List;
import java.util.Scanner;

import static ra.run.ShopManagement.categoriesList;

public class Products implements IShop<Products> {
    private String productId;
    private String productName;
    private float price;
    private String title;
    private int catalogId;
    private boolean status;

    public Products() {
    }

    public Products(String productId, String productName, float price, String title, int catalogId, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.catalogId = catalogId;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner, List<Products> productsList) {
        System.out.print("Chọn mã danh mục: ");
        boolean checkCatalogId;
        do {
            // sau khi chọn catalogId cho phép nhập thông tin của product, nếu để trống hoặc catalogId chưa được tạo sẵn có thể return "Yêu cầu nhập catalogId"
            this.catalogId = Integer.parseInt(scanner.nextLine());
            checkCatalogId = isExistCatalogIdInList(categoriesList, catalogId);
            if (checkCatalogId) {
                System.out.print("Nhập mã sản phẩm: ");
                boolean checkProductId = false;
                do {
                    this.productId = scanner.nextLine();
                    if (productId.length() == 5 && productId.charAt(0) == 'P') {
                        if (uniqueProductID(productsList, productId)) {
                            break;
                        } else {
                            System.err.println("Mã sản phẩm đã tồn tại. Vui lòng nhập lại!");
                        }
                    } else {
                        System.err.println("Mã sản phẩm bắt đầu ký tự 'P' và bao gồm 5 ký tự.");
                    }
                } while (!checkProductId);
                System.out.print("Nhập tên sản phẩm: ");
                boolean checkProductName = false;
                do {
                    this.productName = scanner.nextLine();
                    if (uniqueProductName(productsList, productName)) {
                        break;
                    } else {
                        System.err.println("Tên sản phẩm đã tồn tại. Vui lòng nhập lại!");
                    }
                } while (!checkProductName);
                System.out.print("Nhập giá sản phẩm: ");
                this.price = Float.parseFloat(scanner.nextLine());
                System.out.print("Nhập tiêu đề sản phẩm: ");
                this.title = scanner.nextLine();
                System.out.print("Nhập trạng thái sản phẩm: ");
                this.status = Boolean.parseBoolean(scanner.nextLine());
            } else {
                System.err.println("Vui lòng chọn mã danh mục đã đăng ký.");
            }
        }
        while (!checkCatalogId);
    }

    @Override
    public void displayData() {
        String displayStatus = this.status ? "active" : "inactive";
        System.out.printf("%-15d%-15s%-25s%-20.2f%-35s%-20s\n", catalogId, productId, productName, price, title, displayStatus);
    }

    public boolean isExistCatalogIdInList(List<Categories> categoriesList, int catalogId) {
        for (Categories category : categoriesList) {
            if (category.getCatalogId() == this.catalogId) {
                return true;
            }
        }
        return false;
    }

    public boolean uniqueProductID(List<Products> productsList, String productId) {
        for (Products product : productsList) {
            if (product.productId.equals(productId)) {
                return false;
            }
        }
        return true;
    }

    public boolean uniqueProductName(List<Products> productsList, String productName) {
        for (Products product : productsList) {
            if (product.productName.equals(productName)) {
                return false;
            }
        }
        return true;
    }

    public void updatePrice(Float newPrice) {
        this.price = newPrice;
    }
}
