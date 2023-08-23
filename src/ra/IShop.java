package ra;

import java.util.List;
import java.util.Scanner;

public interface IShop<T> {

    void inputData(Scanner scanner, List<T> data);

    void displayData();
}
