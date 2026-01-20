# Shipping Calculator System Design

เอกสารนี้รวบรวมการออกแบบระบบคำนวณค่าจัดส่งสินค้า (Shipping Calculator) ประกอบด้วย Flowchart และ Pseudo Code

## 1. Flowchart (Mermaid)

แผนผังแสดงลำดับขั้นตอนการทำงานของโปรแกรม ตั้งแต่เริ่มต้นจนจบ

```mermaid
graph TD
    Start([เริ่มทำงาน Start]) --> InputLoc[/รับค่า พื้นที่จัดส่ง/]
    InputLoc --> InputWeight[/รับค่า น้ำหนักพัสดุ/]
    InputWeight --> InputExpress[/รับค่า บริการด่วน Express/]
    InputExpress --> InputVIP[/รับค่า สมาชิก VIP/]
    
    InputVIP --> CheckLoc{พื้นที่ กทม./ปริมณฑล?}
    
    %% --- กรณี กทม. ---
    CheckLoc -- ใช่ (1) --> CheckW_BKK{ตรวจสอบน้ำหนัก}
    CheckW_BKK -- 0-1 kg --> SetBase40[Base Price = 40]
    CheckW_BKK -- 1-3 kg --> SetBase60[Base Price = 60]
    CheckW_BKK -- 3-5 kg --> SetBase80[Base Price = 80]
    CheckW_BKK -- > 5 kg --> SetBase100[Base Price = 100]
    
    %% --- กรณี ต่างจังหวัด ---
    CheckLoc -- ไม่ใช่ (2) --> CheckW_UPC{ตรวจสอบน้ำหนัก}
    CheckW_UPC -- 0-1 kg --> SetBase60_2[Base Price = 60]
    CheckW_UPC -- 1-3 kg --> SetBase90[Base Price = 90]
    CheckW_UPC -- 3-5 kg --> SetBase120[Base Price = 120]
    CheckW_UPC -- > 5 kg --> SetBase150[Base Price = 150]

    %% --- รวมเส้นทาง ---
    SetBase40 --> CheckExpress
    SetBase60 --> CheckExpress
    SetBase80 --> CheckExpress
    SetBase100 --> CheckExpress
    SetBase60_2 --> CheckExpress
    SetBase90 --> CheckExpress
    SetBase120 --> CheckExpress
    SetBase150 --> CheckExpress

    %% --- คำนวณค่าด่วน ---
    CheckExpress{เลือก Express?}
    CheckExpress -- ใช่ (+30) --> AddExpress[Total = Base Price + 30]
    CheckExpress -- ไม่ใช่ --> NoExpress[Total = Base Price]
    
    AddExpress --> CheckVIP
    NoExpress --> CheckVIP

    %% --- คำนวณส่วนลด VIP ---
    CheckVIP{เป็น VIP?}
    CheckVIP -- ใช่ (-20%) --> CalcDiscount[Final = Total - (Total * 0.20)]
    CheckVIP -- ไม่ใช่ --> NoDiscount[Final = Total]
    
    CalcDiscount --> DisplayOutput
    NoDiscount --> DisplayOutput

    DisplayOutput[/แสดงผลสรุปค่าใช้จ่าย/] --> End([จบการทำงาน End])