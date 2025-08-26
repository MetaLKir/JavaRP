package file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileAppl {
    public static void main(String[] args) throws IOException {

        // make a file
        File f1 = new File("file1.txt");
        System.out.println("File exists: " + f1.exists());

        File f2 = new File("file2.txt");

        System.out.println("Create file: " + f1.createNewFile());
        System.out.println("File exists: " + f1.exists());

        // make a directory
        File d1 = new File("dir1");
        System.out.println("Create directory: " + d1.mkdir()); // mkdir_

        File d2 = new File("dir2/dir3/dir4");
        System.out.println("Create directory: " + d2.mkdirs()); // mkdirS

        File f3 = new File("dir2/dir3/dir4", "file3.txt");
        f3.createNewFile();

        File f4 = new File("dir2/dir3", "file4.txt");
        f4.createNewFile();

        File d3 = new File(d1 + File.separator + "dir11" + File.separator + "dir12");
        // .separator is symbol which separates directory in your OS
        // for example: "folder/folder1" or "folder\folder1"
        d3.mkdirs();
        System.out.println(d1.delete()); // works only in directories empty
        System.out.println(d3.delete());
        File c = d3.getParentFile();
        System.out.println(c.delete());
        System.out.println(d1.delete());

        // check .isFile, .isDirectory
        System.out.println(f1.isFile());
        System.out.println(d1.isDirectory());

        // get full path to file
        System.out.println(f1.getAbsolutePath());

        // show parent directories
        System.out.println(f3.getParent());

        // amount of bytes
        System.out.println(f1.length());

        System.out.println(f1.canRead());
        System.out.println(f1.canWrite());
        System.out.println(f1.setWritable(true, true));
        System.out.println(f1.canWrite());

        System.out.println("Total space: " + f1.getTotalSpace() / 1024 / 1024 / 1024. + " Gb");
        System.out.println("Free space: " + f1.getFreeSpace() / 1024 / 1024 / 1024. + " Gb");

        String[] list = d2.list();
        if (list != null) Arrays.stream(list).forEach(System.out::println);

        printDir("dir2/dir3/dir4");
    }

    public static void printDir(String dirName) {
        File dir = new File(dirName);
        if (!dir.exists()) throw new RuntimeException(dirName + " not found");
        if (!dir.isDirectory()) System.out.println(dir.getName() + " is file!!!");
        else
            Arrays.stream(dir.listFiles()).
                    forEach(f -> System.out.printf("%s -> %s\n", f.getName(), f.isFile() ? " file" : " dir"));
    }
}
