package company.walmart.design;


import java.util.*;

class File {
    private String fileName;

    public File(String name) {
        fileName = name;
    }
}

class Directory {

    private String dirname;
    private List<File> files;
    private List<Directory> subdirs;

    public Directory(String name) {
        dirname = name;
        files = new ArrayList<>();
        subdirs = new ArrayList<>();
    }

    public void add(File file) {
        files.add(file);
    }

    public void add(Directory dir) {
        subdirs.add(dir);
    }

    public boolean empty() {
        return checkRecursively(this);
    }

    public boolean checkRecursively(Directory dir) {
        if (dir.files.size() > 0) {
            return false;
        }
        for (Directory subdir : dir.subdirs) {
            if (!checkRecursively(subdir)) {
                return false;
            }
        }
        return true;
    }

}

public class FileSystem {
}
