package tests;

import helpers.CredentialsUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import java.util.Properties;

public class AddTheProductToFavoritesTest extends BaseTest {
    private Properties props;
    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeClass
    public void loadCredentials() {
        props = CredentialsUtil.load("src/test/resources/credentials.properties");
    }

    @Test
    public void addProductToFavorites() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);

        loginPage.acceptCookies();
        loginPage.openLoginForm();

        loginPage.login(props.getProperty("zoo.email.valid"), props.getProperty("zoo.password.valid"));
        assert loginPage.isLoggedInSuccessfully();

        String productName = "Purizon Sterilised Adult Curcan cu pui - fără cereale";
        productPage.searchAndOpenProductByName(productName);
        productPage.addToFavorites();
        assert productPage.isProductFavorited();
    }
}







//public class AddTheProductToFavoritesTest {
//
//    private static final Logger log = LoggerFactory.getLogger(AddTheProductToFavoritesTest.class);
//    private WebDriver driver;
//    private Properties props;
//    private LoginPage loginPage; // Declari o instanță a clasei LoginPage
//    private ProductPage productPage; // Și pentru ProductPage, ca să o ai disponibilă

//    public void loadCredentials() throws Exception {
//        props = new Properties();
//        props.load(new FileInputStream("src/test/resources/credentials.properties"));
//    }
//
//
//    @BeforeMethod
//    public void setUp() throws Exception { // Adaugă throws Exception pentru loadCredentials
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://www.zooplus.ro/");
//
//        loadCredentials(); // Incarca credențialele o singura data la setup
//
//        // Inițializează instanțele Page Object-urilor
//        loginPage = new LoginPage(driver);
//        productPage = new ProductPage(driver); // Inițializezi și ProductPage aici
//        // Astfel, va fi disponibilă în toate metodele de test.
//
//        // Apelez acceptCookies aici, deoarece este o acțiune generală de început de sesiune
//        // și e mai bine să o gestionezi o singură dată la începutul testului.
//        loginPage.acceptCookies();
//    }
//
//    @Test
//    public void addProductToFavorites() {
//        loginPage.openLoginForm();
//        // Acum foloseșc metoda login din LoginPage
//        loginPage.login(props.getProperty("zoo.email.valid"), props.getProperty("zoo.password.valid"));
//        Assert.assertTrue(loginPage.isLoggedInSuccessfully(), "Login-ul a eșuat!");
//
//        // Restul testului pentru favorite
//        String productName = "Purizon Sterilised Adult Curcan cu pui - fără cereale";
//        productPage.searchAndOpenProductByName(productName);
//        productPage.addToFavorites();
//        Assert.assertTrue(productPage.isProductFavorited(), "Produsul nu a fost adăugat în favorite!");
//    }
//
//    // Comentata metoda removeProductFromFavoritesAndLogout (nu o foloseșc momentan)
//    /*
//    @Test
//    public void removeProductFromFavoritesAndLogout() {
//        // loginPage = new LoginPage(driver); // Nu mai e nevoie s-o creez din nou, e deja în setUp
//        // loginPage.acceptCookies(); // Si sta se face deja in setUp
//        loginPage.openLoginForm();
//        loginPage.login(props.getProperty("zoo.email.valid"), props.getProperty("zoo.password.valid"));
//        Assert.assertTrue(loginPage.isLoggedInSuccessfully());
//
//        // favoritesPage = new FavoritesPage(driver);
//        // favoritesPage.navigateToFavorites();
//        // favoritesPage.removeProductFromFavorites("Purizon Sterilised Adult Curcan cu pui - fără cereale");
//        // Assert.assertTrue(favoritesPage.isProductUnfavorited());
//
//        loginPage.logout();
//        Assert.assertTrue(loginPage.isAt());
//    }
//    */
//
//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}

// First version
//public class FavoritesTests {
//    private WebDriver driver;
//    private Properties props;
//
//    @BeforeClass
//    public void loadCredentials() throws Exception {
//        props = new Properties();
//        props.load(new FileInputStream("src/test/resources/credentials.properties"));
//    }
//
//    @BeforeMethod
//    public void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://www.zooplus.ro/");
//    }
//
//    @Test
//    public void addProductToFavorites() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.acceptCookies();
//        loginPage.openLoginForm();
//        loginPage.login(props.getProperty("zoo.email.valid"), props.getProperty("zoo.password.valid"));
//        assert loginPage.isLoggedInSuccessfully();
//
//        ProductPage productPage = new ProductPage(driver);
//        String productName = "Purizon Sterilised Adult Curcan cu pui - fără cereale";
//        productPage.searchAndOpenProductByName(productName);
//        productPage.addToFavorites();
//        Assert.assertTrue(productPage.isProductFavorited(), "Produsul nu a fost adaugat in favorite");
//    }
//
//    @Test
//    public void removeProductFromFavoritesAndLogout() {
//       LoginPage loginPage = new LoginPage(driver);
//        loginPage.acceptCookies();
//        loginPage.openLoginForm();
//        loginPage.login(props.getProperty("zoo.email.valid"), props.getProperty("zoo.password.valid"));
//        assert loginPage.isLoggedInSuccessfully();
//
//        FavoritesPage favoritesPage = new FavoritesPage(driver);
//        favoritesPage.navigateToFavorites();
//        favoritesPage.removeProductFromFavorites("Purizon Sterilised Adult Curcan cu pui - fără cereale");
//        assert favoritesPage.isProductUnfavorited();
//
//        loginPage.logout();
//        assert loginPage.isAt();
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}
