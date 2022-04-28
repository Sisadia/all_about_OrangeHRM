package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class main {

    public static void main(String[] args) throws InterruptedException {
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        final String baseUrl = "https://opensource-demo.orangehrmlive.com/index.php/admin/registerOAuthClient";

        // WEBSITE LOADING
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // LOGIN
        driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
        Thread.sleep(2000);
        

        // TITLE VALIDATION
        String ActualTitle = driver.getTitle();
		String ExpectedTitle = "OrangeHRM";
		Assert.assertEquals(ExpectedTitle, ActualTitle);

        // ADD EMPLOYEE
        WebElement addEmployee = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(text(),'PIM')]")));
        new Actions(driver).moveToElement(addEmployee).perform();
		
        driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']")).click();
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Islam");
        driver.findElement(By.xpath("//input[@id='middleName']")).sendKeys("Sadia");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Shoron");
        WebElement uploadImage=driver.findElement(By.xpath("//input[@id='photofile']"));
        uploadImage.sendKeys("EmployeePic.jpeg");
        driver.findElement(By.xpath("//input[@id='chkLogin']")).click();

        driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("IslamSadia");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("Testtest1234#");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='re_password']")).sendKeys("Testtest1234#");
        driver.findElement(By.xpath("//input[@id='btnSave']")).click();
        Thread.sleep(2000);
        
        // SEARCH EMPLOYEE
        driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).sendKeys("Islam");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
        Thread.sleep(1000);
        
        // EDIT EMPLOYEE
        driver.findElement(By.xpath("//a[contains(text(),'Islam')]")).click();
        driver.findElement(By.xpath("//input[@id='btnSave']")).click();
        driver.findElement(By.id("personal_optGender_2")).click();
        
        Select status = new Select(driver.findElement(By.xpath("//select[@id='personal_cmbMarital']")));
		status.selectByVisibleText("Single");

        Select nationality = new Select(driver.findElement(By.xpath("//select[@id='personal_cmbNation']")));
		nationality.selectByVisibleText("Bangladeshi");
        
        driver.findElement(By.xpath("//ol[3]//li[4]//img[1]")).click();
        Thread.sleep(1000);

        Select month = new Select(driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")));
        month.selectByVisibleText("Sep");

        Select year = new Select(driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")));
        year.selectByVisibleText("1997");

        driver.findElement(By.xpath("//tbody/tr[1]/td[2]/a[1]")).click();
        driver.findElement(By.xpath("//input[@id='btnSave']")).click();

        driver.findElement(By.xpath("//body/div[@id='wrapper']/div[@id='content']/div[@id='employee-details']/div[@id='sidebar']/ul[@id='sidenav']/li[6]/a[1]")).click();
        driver.findElement(By.xpath("//input[@id='btnSave']")).click();

        Select jobTitle = new Select(driver.findElement(By.xpath("//select[@id='job_job_title']")));
        jobTitle.selectByVisibleText("QA Engineer");

        Select employeeStatus = new Select(driver.findElement(By.xpath("//select[@id='job_emp_status']")));
        employeeStatus.selectByVisibleText("Full-Time Permanent");
        driver.findElement(By.xpath("//input[@id='btnSave']")).click();
        Thread.sleep(1000);
        

        // DELETE EMPLOYEE
        driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).click();
        driver.findElement(By.id("ohrmList_chkSelectRecord_28")).click();
        driver.findElement(By.id("ohrmList_chkSelectRecord_37")).click();
        driver.findElement(By.xpath("//input[@id='btnDelete']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='dialogDeleteBtn']")).click();
        Thread.sleep(2000);
        

        // LOGOUT
        driver.findElement(By.xpath("//a[@id='welcome']")).click();
        
        Actions logout = new Actions(driver);
	    WebElement logoutLocator= driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
	    logout.moveToElement(logoutLocator).perform();
        Thread.sleep(2000);

        System.out.println("Scenario: As an Admin, I can CRUD & Search Employee"+
        "\nTest Cases:"+
        "\n ✓ Logged In by username and password"+
        "\n ✓ Title Matched"+
        "\n ✓ Hovered on PIM"+
        "\n ✓ Add Employee"+
        "\n ✓ Searched Employee"+
        "\n ✓ Edit Employee"+
        "\n ✓ Delete Employee"+
        "\n ✓ Logged out by username: Admin");

        // CLOSE BROWSER
        driver.quit();
    }
    
}
