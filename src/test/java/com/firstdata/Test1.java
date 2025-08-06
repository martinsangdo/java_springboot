package com.firstdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Test1 {
    public static void main(String[] args) {
        readFile();
    }
    static void writeFile1(){
        try {
            FileWriter fileWriter = new FileWriter("thongtin.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Nguyễn Văn A, 20 tuổi");
            bufferedWriter.newLine();
            bufferedWriter.write("Trần Thị B, 21 tuổi");
            bufferedWriter.newLine();
            bufferedWriter.write("abckydadiaf");
            bufferedWriter.close();
            System.out.println("Ghi file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    static void readFile(){
        try {
            FileReader fileReader = new FileReader("thongtin.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(">> " + line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }


    //todo
    static void writeNumbers(String fileName, int num){
        //write numbers from 1 -> num into a file

    }
}
