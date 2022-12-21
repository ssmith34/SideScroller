import levels.Level;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;

public class lvlDataTest {

    Level level;

    @Before
    public void update() {
        BufferedImage img = new BufferedImage(20, 20, 1);
        level = new Level(img);
    }

    @Test
    public void lvlData_should_return_proper_numbers() {
        // Arrange
        int expected = 5;
        int[][] testArr = {
                {0, 1, 2, 3, 4},
                {5, 6, 7, 8, 9}
        };

        // Act
//        level.setLvlData(testArr);
//        int actual = level.getSpriteIndex(0, 1);

        for (int i = 0; i < level.getLevelData().length - 1; i++)
            for (int j = 0; j < level.getLevelData()[0].length - 1; i++)
                System.out.println("Array at [" + i + "] and [" + j + "] = " + level.getSpriteIndex(i, j));
        // Assert
        System.out.println("Expected: " + expected);
//        System.out.println("Actual: " + actual);
    }

}
