package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

import static com.javarush.test.level16.lesson13.bonus01.common.ImageTypes.BMP;
import static com.javarush.test.level16.lesson13.bonus01.common.ImageTypes.JPG;
import static com.javarush.test.level16.lesson13.bonus01.common.ImageTypes.PNG;

/**
 * Created by 1 on 01.03.2017.
 */
public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes imageTypes)
    {

            if (imageTypes == BMP)  return new BmpReader();
            else if (imageTypes == JPG)  return new JpgReader();
            else if (imageTypes == PNG)  return new PngReader();
            else throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
