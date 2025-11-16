import org.junit.jupiter.api.*;
import sbp.io.MyIOExample;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyIOExampleTests {

    MyIOExample tester = new MyIOExample();

    String fileName;
    String pathFile;
    File homeFolder;

    private void Initialise (){
        fileName = "testFile.txt";
        pathFile = Objects.requireNonNull(getClass().getResource(fileName)).getFile();
        homeFolder = FileSystemView.getFileSystemView().getDefaultDirectory();
    }

    @Test
    @Order(1)
    public void MyIOExampleTests_workWithFile(){
        Initialise();
        Assertions.assertTrue(tester.workWithFile(pathFile));

    }

    @Test
    @Order(1)
    public void MyIOExampleTests_copyFile(){

        Initialise();

        String outFileName = "Copy1.txt";
        //пишем в домашнюю папку
        String outFullFileName = homeFolder.getPath()+"/"+outFileName;

        Assertions.assertTrue(tester.copyFile(pathFile, outFullFileName));
    }

    @Test
    @Order(1)
    public void MyIOExampleTests_copyBufferedFile(){

        Initialise();

        String outFileName = "Copy2.txt";
        //пишем в домашнюю папку
        String outFullFileName = homeFolder.getPath()+"/"+outFileName;

        Assertions.assertTrue(tester.copyBufferedFile(pathFile, outFullFileName));
    }

    @Test
    @Order(1)
    public void MyIOExampleTests_copyFileWithReaderAndWriter(){

        Initialise();

        String outFileName = "Copy3.txt";
        //пишем в домашнюю папку
        String outFullFileName = homeFolder.getPath()+"/"+outFileName;

        Assertions.assertTrue(tester.copyFileWithReaderAndWriter(pathFile, outFullFileName));
    }

    @Test
    @Order(10)
    public void MyIOExampleTests_deleteFile() throws IOException {
        Initialise();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> list = new ArrayList<>();
        list.add("Copy1.txt");
        list.add("Copy2.txt");
        list.add("Copy3.txt");

        System.out.println("\nУдаляем копии файлов");
        for(String nameFile : list){
            String pathToDelete = homeFolder.getPath()+"/"+nameFile;

            Files.deleteIfExists(Path.of(pathToDelete));
        }
    }

}
