package sbp.io;

import java.io.File;

public class MyIOExample
{
    /**
     * Создать объект класса {@link java.io.File}, проверить существование и чем является (файл или директория).
     * Если сущность существует, то вывести в консоль информацию:
     *      - абсолютный путь
     *      - родительский путь
     * Если сущность является файлом, то вывести в консоль:
     *      - размер
     *      - время последнего изменения
     * Необходимо использовать класс {@link java.io.File}
     * @param fileName - имя файла
     * @return - true, если файл успешно создан
     */
    public boolean workWithFile(String fileName)
    {
        File file = new File(fileName);
        boolean exists = file.exists();
        boolean isFile = file.isFile();

        if (exists){
            System.out.println("абсолютный путь " + file.getAbsolutePath());
            System.out.println("родительский путь "+ file.getParentFile());
            if (isFile){
                System.out.println("размер файла "  + file.length());
                System.out.println("время последнего изменения " +  file.lastModified());
                return true;
            }
        }
        return false;
    }

    /**
     * Метод должен создавать копию файла
     * Необходимо использовать IO классы {@link java.io.FileInputStream} и {@link java.io.FileOutputStream}
     * @param sourceFileName - имя исходного файла
     * @param destinationFileName - имя копии файла
     * @return - true, если файл успешно скопирован
     */
    public boolean copyFile(String sourceFileName, String destinationFileName)
    {
        /*
        ...
         */
        return false;
    }

    /**
     * Метод должен создавать копию файла
     * Необходимо использовать IO классы {@link java.io.BufferedInputStream} и {@link java.io.BufferedOutputStream}
     * @param sourceFileName - имя исходного файла
     * @param destinationFileName - имя копии файла
     * @return - true, если файл успешно скопирован
     */
    public boolean copyBufferedFile(String sourceFileName, String destinationFileName)
    {
        /*
        ...
         */
        return false;
    }

    /**
     * Метод должен создавать копию файла
     * Необходимо использовать IO классы {@link java.io.FileReader} и {@link java.io.FileWriter}
     * @param sourceFileName - имя исходного файла
     * @param destinationFileName - имя копии файла
     * @return - true, если файл успешно скопирован
     */
    public boolean copyFileWithReaderAndWriter(String sourceFileName, String destinationFileName)
    {
        /*
        ...
         */
        return false;
    }
}
