
import java.util.Scanner;

public class ShippingCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------");
        System.out.println("    ระบบคำนวณค่าจัดส่งสินค้า (Shipping System)");
        System.out.println("----------------------------------------");

        // 1. รับข้อมูลพื้นที่จัดส่ง
        System.out.println("กรุณาเลือกปลายทาง:");
        System.out.println("1. กรุงเทพฯ และปริมณฑล");
        System.out.println("2. ต่างจังหวัด");
        System.out.print("เลือกหมายเลข (1 หรือ 2): ");
        int locationChoice = scanner.nextInt();

        // 2. รับข้อมูลน้ำหนัก
        System.out.print("ระบุน้ำหนักพัสดุ (กิโลกรัม): ");
        double weight = scanner.nextDouble();

        // 3. รับข้อมูลบริการด่วน
        System.out.print("ต้องการบริการด่วน Express (+30 บาท) หรือไม่? (y/n): ");
        char expressChar = scanner.next().toLowerCase().charAt(0);
        boolean isExpress = (expressChar == 'y');

        // 4. รับข้อมูลสมาชิก VIP
        System.out.print("คุณเป็นสมาชิก VIP หรือไม่? (y/n): ");
        char vipChar = scanner.next().toLowerCase().charAt(0);
        boolean isVip = (vipChar == 'y');

        // --- เริ่มการคำนวณ ---
        double basePrice = 0;

        // ตรวจสอบพื้นที่และกำหนดราคาพื้นฐาน
        if (locationChoice == 1) {
            // กรุงเทพฯ และปริมณฑล
            if (weight <= 1) {
                basePrice = 40;
            } else if (weight <= 3) {
                basePrice = 60;
            } else if (weight <= 5) {
                basePrice = 80;
            } else {
                basePrice = 100;
            }
        } else {
            // ต่างจังหวัด
            if (weight <= 1) {
                basePrice = 60;
            } else if (weight <= 3) {
                basePrice = 90;
            } else if (weight <= 5) {
                basePrice = 120;
            } else {
                basePrice = 150;
            }
        }

        // คำนวณค่าบริการด่วน
        double expressFee = isExpress ? 30 : 0;
        double subTotal = basePrice + expressFee;

        // คำนวณส่วนลด VIP (20%)
        double discount = 0;
        if (isVip) {
            discount = subTotal * 0.20;
        }

        double finalPrice = subTotal - discount;

        // --- แสดงผลลัพธ์ ---
        System.out.println("\n----------------------------------------");
        System.out.println("สรุปค่าจัดส่งสินค้า");
        System.out.println("----------------------------------------");
        System.out.printf("ค่าส่งพื้นฐาน (%.1f kg): \t%.2f บาท\n", weight, basePrice);

        if (isExpress) {
            System.out.println("ค่าบริการด่วน (Express): \t+30.00 บาท");
        }

        System.out.printf("รวมก่อนหักส่วนลด: \t\t%.2f บาท\n", subTotal);

        if (isVip) {
            System.out.printf("ส่วนลด VIP (20%%): \t\t-%.2f บาท\n", discount);
        }

        System.out.println("----------------------------------------");
        System.out.printf("ยอดสุทธิที่ต้องชำระ: \t\t%.2f บาท\n", finalPrice);
        System.out.println("----------------------------------------");

        scanner.close();
    }
}
