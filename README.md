# KSRTC Automation Testing 

## ⚙️ Installation

### 1. Clone the repository

git clone https://github.com/Ibrahem-Abdelaziz/Travel_Task.git
cd KSRTC-Automation-Testing


### 2. Install dependencies
Ensure **Java JDK 25** and **Maven** are installed and configured on your system.

Dependencies used:
<dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.38.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple -->
        <dependency>
            <groupId>com.googlecode.json-simple</groupId>
            <artifactId>json-simple</artifactId>
            <version>1.1.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.testng/testng -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.11.0</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.geotab/java-sdk-common -->
        <dependency>
            <groupId>com.geotab</groupId>
            <artifactId>java-sdk-common</artifactId>
            <version>10.0.1</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.13</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.json/json -->
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20250517</version>
        </dependency>

    </dependencies>

### 3. Run the project

mvn clean test

---

## 🧪 Test Scenario

Automates an end-to-end booking flow on the KSRTC website:  
🔗 [https://ksrtc.in/oprs-web/guest/home.do?h=1](https://ksrtc.in/oprs-web/guest/home.do?h=1)

### Steps:
1. Open KSRTC homepage  
2. Choose **From:** CHIKKAMAGALURU and **To:** BENGALURU  
3. Select arrival date only  
4. Click **Search for Bus**  
5. Select a seat (data-driven for multiple seats)  
6. Choose boarding & dropping points  
7. Fill **Customer Details:**  
   - Phone: "6789125987"  
   - Email: "Ibrahem@asd.com"  
8. Fill **Passenger Details:**  
   - Name: "Ibrahem"  
   - Gender: "Male"  
   - Age: "27"  
9. Click **Make Payment** and fill payment fields (without submitting)

---

## 🧱 Project Structure

```
KSRTC-Automation-Testing/
│
├── pom.xml
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/
│   │           ├── HomePage.java
│   │           ├── SearchResultsPage.java
│   │           ├── PaymentPage.java
│   │           └── PageBase.java
│   │
│   └── test/
│       └── java/
│           ├── data/
│           │    ├── DataFile.json
│           │    └── JsonReader.java
│           └── tests/
│           │     ├── BookingBusTripTest.java
│           │     └── TestBase.java
│           └── Utilities/
│                  └── Helper.java
│
│           
│
└── README.md


**Framework Design:**  
- Page Object Model (POM)  
- Data-driven Testing (JSON)  
- Maven + TestNG  
- WebDriverManager  
- Selenium WebDriver  

---

## 🧠 Skills Demonstrated
- Java (JDK 25)  
- Selenium WebDriver  
- TestNG Framework  
- POM  
- DDT (Data-driven Testing)  
- Maven  

---

## 👤 Author
**Ibrahem Abdelaziz**  
Software QA Engineer – EFG Holding  
📧 Ibrahemabdelaziz1298@gmail.com  
📞 011102981303