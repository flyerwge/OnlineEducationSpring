package flyerwge.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    //写操作
//    public static void main(String[] args) {
//        //实现excel写
//        String fileName = "C:\\Users\\flyer\\Desktop\\OnlineEducation文件\\write.xlsx";
//        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());
//    }

    //读操作
    public static void main(String[] args) {
        String filename = "C:\\Users\\flyer\\Desktop\\OnlineEducation文件\\write.xlsx";
        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            DemoData data = new DemoData();
            data.setSid(i+1);
            data.setSname("gw是大好人"+i);
            list.add(data);
        }

        return list;
    }
}
