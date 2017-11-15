import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

/**
 * Created by john_liu on 2017/7/18.
 */
public class HDFSoperation {
    /**
     * 移动文件
     *
     * @param src 文件路径
     * @param dst 目标路径

     */
    public  static boolean rename(String src, String dst) throws IOException {

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Boolean flag = fs.rename(new Path(src), new Path(dst));
        if (flag) System.out.println("Rename success");
        else System.out.println(src + "Rename failed");
        return flag;

    }

    /**获得当前路径下目录所有子目录
     *
     *
     *
     * @param dst 目标路径

     */
    public static FileStatus[] getDirectoryFromHdfs(String dst) throws FileNotFoundException, IOException {

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        FileStatus fileList[] = fs.listStatus(new Path(dst));

        fs.close();
        return fileList;
    }
    /**生成directory
     *
     *
     *
     * @param dir 目标路径

     */
    public static void mkdir(String dir) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        System.out.println(fs.mkdirs(new Path(dir)));


    }
    /**删除HDFS 文件/文件夹
     *
     *
     *
     *@param filePath 文件路径

     */
    public static void delete(String filePath) throws IOException{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(filePath);
        boolean isok = fs.deleteOnExit(path);
        if(isok){
            System.out.println(filePath + " delete ok!");
        }else{
            System.out.println(filePath + " delete failure");
        }
        fs.close();
    }
    /**删除HDFS 文件/文件夹
     *
     *
     *
     *@param filePath 文件路径
     *@param pathtobeput 上传的路径
     */
    public static void putfiles(String filePath,String pathtobeput) throws IOException{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path fpath = new Path(filePath);
        Path tpath = new Path(pathtobeput);
        fs.copyFromLocalFile(fpath,tpath);
        fs.close();
    }


}
