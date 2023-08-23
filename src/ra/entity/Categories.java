package ra.entity;

import ra.IShop;

import java.util.List;
import java.util.Scanner;

public class Categories implements IShop<Categories> {
    private int catalogId;
    private String catalogName;
    private boolean status;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.status = status;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void inputData(Scanner scanner, List<Categories> categoriesList) {
        System.out.print("Nhập mã danh mục (số nguyên): ");
        boolean checkId = false;
        do {
            try {
                this.catalogId = Integer.parseInt(scanner.nextLine());
                checkId = uniqueCatalogId(categoriesList, catalogId);
                if (checkId) {
                    System.err.println("Mã danh mục đã tồn tại. Vui lòng nhập lại!");
                    checkId = false;
                } else {
                    break;
                }
            } catch (NumberFormatException e1) {
                System.err.println("Error: " + e1.getMessage());
            }
        } while (!checkId);
        System.out.print("Nhập tên danh mục: ");
        boolean checkName;
        do {
            this.catalogName = scanner.nextLine();
            checkName = uniqueCatalogName(categoriesList, catalogName);
            if (checkName) {
                System.err.println("Tên danh mục đã tồn tại. Vui lòng nhập lại!");
                checkName = false;
            } else {
                break;
            }
        } while (!checkName);
        System.out.print("Nhập trạng thái danh mục (true/false): ");
        status = scanner.nextBoolean();
        while (status != true && status != false) {
            System.err.println("Trạng thái danh mục không đúng!");
            status = scanner.nextBoolean();
        }
    }

    @Override
    public void displayData() {
        String displayStatus = this.status ? "active" : "inactive";
        System.out.printf("%-15d%-25s%-20s\n", catalogId, catalogName, displayStatus);
    }

    public boolean uniqueCatalogId(List<Categories> categoriesList, int catalogId) {
        for (Categories category : categoriesList) {
            if (category.getCatalogId() == catalogId) {
                return true;
            }
        }
        return false;
    }

    public boolean uniqueCatalogName(List<Categories> categoriesList, String catalogName) {
        for (Categories category : categoriesList) {
            if (category.catalogName.equals(catalogName)) {
                return true;
            }
        }
        return false;
    }

    public void updateCatalogName(String newCatalogName) {
        this.catalogName = newCatalogName;
    }

}
