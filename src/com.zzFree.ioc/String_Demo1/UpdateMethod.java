package com.zzFree.ioc.String_Demo1;
import org.gjt.jclasslib.io.ClassFileWriter;
import org.gjt.jclasslib.structures.CPInfo;
import org.gjt.jclasslib.structures.ClassFile;
import org.gjt.jclasslib.structures.InvalidByteCodeException;
import org.gjt.jclasslib.structures.constants.ConstantUtf8Info;

import java.io.*;

/**
 *    该方法可以修改字节码
 *
 */
public class UpdateMethod {
    public static void main(String[] args) {

        String filePath = "C:\\InventoryDaoImpl.class";
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DataInput di = new DataInputStream(fis);
            ClassFile cf = new ClassFile();
        try {
            cf.read(di);
        } catch (InvalidByteCodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CPInfo[] infos = cf.getConstantPool();

        int count = infos.length;
        for( int i = 0 ; i < count ; i++) {
            if( infos[i] != null){
                System.out.println(i);
                System.out.println("=");
                try {
                    System.out.println(infos[i].getVerbose());
                } catch (InvalidByteCodeException e) {
                    e.printStackTrace();
                }
                System.out.println("=");
                System.out.println(infos[i].getTagVerbose());
                if(i == 588) {
                    ConstantUtf8Info uinfo = (ConstantUtf8Info)infos[i];
                    uinfo.setBytes("  left join (select distinct t1.*,t2.ybmc,t2.bzbm from Kc_Wzml t1,yb_ybmldz t2 where t1.zhbh = t2.zhbh and t1.wzbh = t2.fydm ".getBytes());
                    infos[i] = uinfo;
                }
            }
        }
    cf.setConstantPool(infos);
        try {
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        File f = new File(filePath);
        try {
            ClassFileWriter.writeToFile(f, cf);
        } catch (InvalidByteCodeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
