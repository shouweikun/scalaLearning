package Excel;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by john_liu on 2017/8/2.
 */
public class ExcelUtils {

    public Workbook readWorkbook(String path) throws IOException, BiffException {
        InputStream instream = new FileInputStream(path);
        Workbook readwb = Workbook.getWorkbook(instream);
          return  readwb;
    }

}
