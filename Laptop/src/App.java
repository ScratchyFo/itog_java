// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    String brand;
    int ram;
    int hdd;
    int ssd;
    String os;
    String color;

    public Laptop(String brand, int ram, int hdd, int ssd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.ssd = ssd;
        this.os = os;
        this.color = color;
    }

    @Override
    public String toString() {
        return
                brand +
                        ", ram = " + ram +
                        ", hdd = " + hdd +
                        ", ssd = " + ssd +
                        ", os = " + os +
                        ", color = " + color;
    }
}

public class App {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Asus", 16, 512, 0, "Windows OS", "Black"));
        laptops.add(new Laptop("Asus", 16, 512, 0, "No OS", "Black"));
        laptops.add(new Laptop("Apple", 8, 256, 0, "MacOS", "Silver"));
        laptops.add(new Laptop("Lenovo", 4, 1024, 0, "Windows OS", "Gray"));
        laptops.add(new Laptop("Lenovo", 4, 1024, 0, "No OS", "Gray"));
        laptops.add(new Laptop("Xiaomi", 16, 512, 256, "Windows OS", "Black"));
        laptops.add(new Laptop("Xiaomi", 16, 512, 256, "No OS", "Black"));


        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите параметры для фильтрации:");
        System.out.println("1 - ОЗУ (4, 8, 16)");
        System.out.println("2 - Объем HDD (256, 512, 1024)");
        System.out.println("3 - Объем SSD (0, 256)");
        System.out.println("4 - Операционная система (Windows, Mac, No OS)");
        System.out.println("5 - Цвет (Black, Silver, Gray)");
        System.out.println("0 - Завершить выбор");

        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("Минимальный объем ОЗУ?");
                    filters.put("ram", scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Минимальный объем HDD?");
                    filters.put("hdd", scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Минимальный объем SSD?");
                    filters.put("ssd", scanner.nextInt());
                    break;
                case 4:
                    System.out.println("ОС (Windows, MacOC, No OS)?");
                    filters.put("os", scanner.next());
                    break;
                case 5:
                    System.out.println("Цвет?");
                    filters.put("color", scanner.next());
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer && laptop.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("hdd", 0) instanceof Integer && laptop.hdd >= (int) filters.getOrDefault("hdd", 0))
                .filter(laptop -> filters.getOrDefault("ssd", 0) instanceof Integer && laptop.ssd >= (int) filters.getOrDefault("ssd", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("") || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("") || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}