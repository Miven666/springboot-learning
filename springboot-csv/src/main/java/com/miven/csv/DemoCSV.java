package com.miven.csv;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by mingzhi.xie on 2018/9/26.
 */
public class DemoCSV {
    public static void main(String[] args) {
        String filePath = "F:\\github\\miven666\\SpringBoot-learing\\springboot-csv\\src\\main\\resources\\summary.csv";
        try {
            CsvReader csvReader = new CsvReader(filePath);

            int i = 1;
            // 读表头
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();
            while (csvReader.readRecord()) {
                i++;
                // 读一整行
                System.out.println("第" + i + "行数: " + csvReader.getRawRecord());
                // 读这一行的某列
                System.out.println(headers[0] + ": " + csvReader.get(0));
                System.out.println(headers[1] + ": " + csvReader.get(1));
                System.out.println("================================");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
