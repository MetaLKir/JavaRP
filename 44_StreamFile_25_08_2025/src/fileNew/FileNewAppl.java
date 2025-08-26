package fileNew;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileNewAppl {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p1 = Paths.get("etc/test");
        System.out.println(p1.getFileName());
        System.out.println(p1.getParent());
        System.out.println(p1.getRoot());

        Path p2 = Paths.get("lol", "foo", "meow");
        System.out.println(p2.getFileName());
        System.out.println(p2.getParent());
        System.out.println(p2.getRoot());
        System.out.println(p1.resolve(p2));

//        Path p3 = Paths.get(new URI("file://tmp"));
//        File f1 = new File("logs/app.log");
//        Path p4 = f1.toPath();
//        System.out.println(p4);

        Path d1 = Paths.get("demo");
        Files.createDirectories(d1);
        Path f11 = d1.resolve("sample.txt");
        if (Files.notExists(f11)) Files.createFile(f11);
        Path d11 = Paths.get("dir1");
        Files.createDirectories(d11);

        Path f12 = d11.resolve("sample1.txt");
        if (Files.notExists(f12)) Files.createFile(f12);

//        Files.writeString(f12, "Hello");
//        Files.writeString(f12, " Group from Karmiel", StandardOpenOption.APPEND);
        List<String> info = Files.readAllLines(f12);
        System.out.println(info);
        BufferedReader br = Files.newBufferedReader(f12);
        System.out.println("===== Buffer Reader =====");
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Br: " + line);
        }

        System.err.print("Privet ya oshibka ;)");
    }
}
