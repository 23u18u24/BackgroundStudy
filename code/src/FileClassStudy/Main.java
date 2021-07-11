package FileClassStudy;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        //路径分隔符
//        String pathSeparator = File.pathSeparator;
//        System.out.println(pathSeparator);
//
//        String separator = File.separator;
//        System.out.println(separator);// \ 反斜杠windows系统
        File file = new File("C:\\Users\\wu197\\Desktop\\后端学习\\code\\a.txt");
//        String name = file.getName();
//        //执行权限
//        boolean b = file.canExecute();
//        //是否可读
//        boolean b1 = file.canRead();
//        //改名
//        boolean b2 = file.renameTo(new File("aRename.txt"));
//        //是否可写
//        boolean b3 = file.canWrite();
//        //创建新文件
//        boolean newFile = file.createNewFile();
//        //获取绝对路径
//        File absoluteFile = file.getAbsoluteFile();
//        //获得标准路径
//        File canonicalFile = file.getCanonicalFile();
//        //剩余内存
//        long freeSpace = file.getFreeSpace();
//        //获得根目录
//        String parent = file.getParent();
//        //设置权限
//        boolean b4 = file.setExecutable(true);
//        boolean b5 = file.canWrite();
//        //获取当前文件大小
//        long length = file.length();
//        //判断构造方法路径是否存在
//        boolean exists = file.exists();
//        //判断路径是否为普通文件结尾，只要不是文件夹均是普通文件
//        boolean file1 = file.isFile();
//        //判断路径是否为文件夹结尾
//        boolean directory = file.isDirectory();
////        System.out.println(file1);
////        System.out.println(directory);
//        //创建单级空文件夹
//        File mkdirFile = new File("C:\\Users\\wu197\\Desktop\\后端学习\\code\\src\\FileClass\\Testmkdir");
//        boolean mkdir = mkdirFile.mkdir();
////        System.out.println(mkdir);
////        boolean delete = mkdirFile.delete();
//        //创建单或多级空文件夹，不在乎前面的路径是否存在
//        File mkdirsFile = new File("C:\\Users\\wu197\\Desktop\\后端学习\\code\\src\\FileClass\\Testmkdir\\111\\222");
//        boolean mkdirs = mkdirsFile.mkdirs();
//        mkdirsFile.delete();

//        //获取所有子目录
        File file2 = new File("C:\\Users\\wu197\\Desktop\\后端学习\\code\\src");
//        String[] list = file2.list();
//        for (String s : list) {
//            System.out.println(s);
//        }

        //获取所有子目录文件的详细路径
//        File[] files = file2.listFiles(new FileFilter()/*文件过滤器*/ {
//            //listFiles自动将数组遍历并传参给accept函数
//            @Override
//            public boolean accept(File pathname) {
//                if (!pathname.isDirectory()) {
//                    return pathname.getName().matches(".*?java");
//                }
//                if (pathname.listFiles() != null) {
//                    for (File listFile : pathname.listFiles()) {
////                    System.out.println(listFile);
//                        accept(listFile);
//                    }
//                }
//                return false;
//            }
//        });
//        for (File file1 : files) {
//            System.out.println(file1);
//        }

        LinkedList<File> javaEndFile = getJavaEndFile(file2);
        while (!javaEndFile.isEmpty()) {
            System.out.println(javaEndFile.removeFirst());
        }

    }

    public static LinkedList<File> getJavaEndFile(File file) {
        if (!file.exists()) {
            return null;
        }
        LinkedList<File> endlinkedList = new LinkedList<>();
        File[] files = file.listFiles();
        for (File kidFile : files) {
            if (kidFile.isDirectory()) {
                LinkedList<File> linkedList = getJavaEndFile(kidFile);
                while (!linkedList.isEmpty()) {
                    endlinkedList.add(linkedList.removeFirst());
                }
            }
            if(kidFile.getName().matches(".*?java")) {
                endlinkedList.add(kidFile);
            }
        }
        return endlinkedList;
    }
}
