package se.chapter05.stream2.file;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * File
 * 构造函数
 * 1.File(String parent,String chlid)
 * 2.File(String name)
 * 3.File(File parent,String chlid)
 * 4.File(URI url)
 **/
public class FileTestDemo01 {

    /*
     * @Autor: 13528
     * @Description:列出子目录下的所有内容，包括子目录的内容，也可理解为深度递归
     * @Date: 9:23 2019/12/1
     * @Param: []
     * @return: void
     **/
    @Test
    public void test01(){
        File f = new File("D:\\baidu");

        listAll(f,0);
    }

    private void listAll(File f,int count) {
        System.out.println(getSpace(count)+f.getName());
        File[] files = f.listFiles();
        count++;
        for (File file:files) {
            if(file.isDirectory()){
                listAll(file,count);
            }
            else
              System.out.println(getSpace(count)+file.getName());
        }
    }

    private String getSpace(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("|--");
        for (int i=0;i<count;i++)
            sb.insert(0,"|  ");
        return sb.toString();
    }

    /*
     * @Autor: 13528
     * @Description: 删除一个有内容的目录
     *                  需要递归删除
     * @Date: 10:17 2019/12/1
     * @Param:
     * @return:
     **/
    @Test
    public void test07(){
        File f = new File("D:\\IOTest");
        remove(f);
    }

    private void remove(File f) {
        File[] files = f.listFiles();
        for (File file:files) {
            if(file.isDirectory()){
                remove(file);
            }else
                System.out.println(file.getName()+":"+file.delete());
        }
        System.out.println(f.getName()+":"+f.delete());
    }

    /*
     * @Autor: 13528
     * 需求：获取指定目录下，指定扩展名的文件列表
     * 思路
     * 1.必须进行深度遍历
     * 2.在遍历的过程中进行过滤，将符合条件的内容都存储到容器中
     * 3.对容器中的内容进行遍历，并写到文件中
     **/
    @Test
    public void test02(){
        File dir = new File("D:\\workspace\\IdeaProjects\\jvm_lecture");
        List<File> fileList = new ArrayList<>();
        getFiles(dir,fileList,x->x.getName().endsWith(".java"));
        for (File file:fileList) {
            System.out.println(file.getAbsolutePath());
        }
    }
    private void getFiles(File dir,List<File> flieList,FileFilter filter) {

        File[] files = dir.listFiles();
        for (File file:files) {
            if(file.isDirectory()){
                getFiles(file,flieList,filter);
            }else {
                if(filter.accept(file)){
                    flieList.add(file);
                }
            }

        }
    }

    @Test
    public void del(){
        File f = null;
        for (int i=1;i<9;i++){
            f = new File(i+"part");
            f.delete();
        }
    }
}
