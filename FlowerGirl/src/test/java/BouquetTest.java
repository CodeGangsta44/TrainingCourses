import org.junit.*;

import com.company.model.Bouquet;
import com.company.model.entities.*;

import java.util.List;

public class BouquetTest {
    private Bouquet bouquet;
    private int expectedPrice;
    private int lowerStemLengthBound;
    private int upperStemLengthBound;

    @Before
    public void initBouquet() {
        bouquet = new Bouquet(new Flower("Bluebell", 100, 90, 25),
                              new Flower("Bluebell", 100, 100, 25),
                              new Flower("Bluebell", 100, 80, 25),
                              new Flower("Rose", 50, 53, 50),
                              new Flower("Rose", 50, 59, 50),
                              new Flower("Rose", 50, 57, 50),
                              new Flower("Rose", 50, 35, 50),
                              new Flower("Rose", 50, 56, 50),
                              new Grass("Lavender", 50, 50),
                              new BouquetAccessory("Paper", 50),
                              new BouquetAccessory("Strip", 10));

        expectedPrice = 660;
        lowerStemLengthBound = 30;
        upperStemLengthBound = 60;
    }

    @After
    public void clearBouquet() {
        bouquet = null;
    }

    @Test
    public void bouquetPriceTest() {
        int actual = bouquet.getPrice();

        Assert.assertEquals(expectedPrice, actual, 0.01);
    }

    @Test
    public void bouquetSortedByFreshnessPlantsTest() {
        List<Plant> plantsList = bouquet.getSortedByFreshnessPlants();
        int currentFreshnessLevel = 100;

        for (Plant i : plantsList) {
            Assert.assertTrue(i.getFreshnessLevel() <= currentFreshnessLevel);
            currentFreshnessLevel = i.getFreshnessLevel();
        }
    }

    @Test
    public void bouquetGetFlowersByStemLengthTest() {
        List<Flower> flowersList = bouquet.getFlowersByStemLength(lowerStemLengthBound, upperStemLengthBound);

        for (Flower i : flowersList) {
            Assert.assertTrue(i.getStemLength() >= lowerStemLengthBound
                                     && i.getStemLength() <= upperStemLengthBound);
        }
    }
}
