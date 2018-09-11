package IO;

import org.junit.Test;

import java.io.File;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description File 类 相关方法demo
 * @date 2018/5/22 16:16$
 */
public class FileDemo {

    @Test
    public void execute()throws Exception{
        File file = new File("");
        File tempFile = File.createTempFile("", "", file);

        boolean mkdir = file.mkdir();
    }
}
