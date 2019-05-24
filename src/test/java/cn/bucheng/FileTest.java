package cn.bucheng;

import org.junit.Test;

import java.io.RandomAccessFile;

/**
 * @ClassName FileTest
 * @Author buchengyin
 * @Date 2019/5/24 17:03
 **/
public class FileTest {

    @Test
    public void testInsert(){
        String file ="D://test//test22.txt";
        try {
            RandomAccessFile rf = new RandomAccessFile(file, "rw");
            rf.seek(0);
            rf.setLength(1024*1024);
            byte[] datas ="hello word".getBytes();
            rf.write(datas);


            RandomAccessFile rf2 = new RandomAccessFile(file,"rw");
            rf2.seek(datas.length+20);
             datas ="i love you".getBytes();
            rf2.write(datas);

            rf.close();
            rf2.close();
            System.out.println("-------------->finish");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
