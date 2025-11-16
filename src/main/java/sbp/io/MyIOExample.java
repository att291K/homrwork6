package sbp.io;

import java.io.*;
import java.util.Arrays;

import static java.lang.System.out;

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
            out.println("абсолютный путь " + file.getAbsolutePath());
            out.println("родительский путь "+ file.getParentFile());
            if (isFile){
                out.println("размер файла "  + file.length());
                out.println("время последнего изменения " +  file.lastModified());
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
        File sourceFile = new File(sourceFileName);
        File destinationFile = new File(destinationFileName);
        if (destinationFile.exists()) return false;

        if(sourceFile.exists() && sourceFile.isFile()){
            try(final FileInputStream fileInputStream = new FileInputStream(sourceFile);
                final FileOutputStream fileOutputStream = new FileOutputStream(destinationFile, true)){

                byte[] buffer = new byte[256];
                int count;
                while ((count = fileInputStream.read(buffer)) != -1){
                    fileOutputStream.write(buffer, 0, count);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return destinationFile.exists();
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
        File sourceFile = new File(sourceFileName);
        File destinationFile = new File(destinationFileName);
        if (destinationFile.exists()) return false;

        if(sourceFile.exists() && sourceFile.isFile()){
            try(FileInputStream fileInputStream = new FileInputStream(sourceFile);
                FileOutputStream fileOutputStream = new FileOutputStream(destinationFile, true);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)){

                byte[] buffer = new byte[256];
                int count;
                while ((count = bufferedInputStream.read(buffer)) != -1){
                    bufferedOutputStream.write(buffer,0,count);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return destinationFile.exists();
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
        File sourceFile = new File(sourceFileName);
        File destinationFile = new File(destinationFileName);
        if (destinationFile.exists()) return false;

        if(sourceFile.exists() && sourceFile.isFile()){
            try(FileReader fileReader = new FileReader(sourceFile);
            FileWriter fileWriter = new FileWriter(destinationFile, true)){

                char[] buffer = new char[256];
                int count;
                while ((count = fileReader.read(buffer)) > 0){
                    if (count < 256){
                        buffer = Arrays.copyOf(buffer,count);
                    }
                    fileWriter.write(buffer);
                }
                fileWriter.flush();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return destinationFile.exists();
    }
}
