/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domain.SalariedEmployee;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author chris
 */
public class DataFile {
     private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
  //  private static FileHelper fileHelper;
    
 public DataFile() {
    
}
 public int writeToFile(String fileName, LinkedList<SalariedEmployee> employeeList) {
        try {

            File file = new File(fileName);
            boolean isFileExisting = file.exists();
            int employeeCount =0;
            this.fos = new FileOutputStream(fileName, true);
            /**
             * if creating new file use ObjectOutputStream which will write header
             */
            if (!isFileExisting)  
                this.oos = new ObjectOutputStream(fos);
            /**
             * if the file exists and opened for appending use AppendingObjectOutputStream
             * to avoid addition of stream header again.
             */
            else
                this.oos = new AppendingObjectOutputStream (fos);
//            if (isFileExisting )
//                oos.reset();
            for (SalariedEmployee emp:employeeList)  {
               oos.writeObject(emp);
               employeeCount++;
            }
                oos.flush();
                oos.close();

           return employeeCount;
        } catch (FileNotFoundException ffe) {
            System.out.println("FNF: " + ffe.getMessage());
        } catch (IOException ioe) {
            System.out.println("IOE: " + ioe.getMessage());
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                System.out.println("IOE : " + ex.getMessage());
            }
        }

        return -1;
    }
 
    public LinkedList<SalariedEmployee> readFromFile(String fileName) {
        try {
            
            File file = new File(fileName);
            
            if(!file.exists()){
                return null;
            }
                
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);

            LinkedList<SalariedEmployee> list = new LinkedList<>();

            //while there is still objects to read, keep reading and add them to the list
            while (fis.available() > 0) {
                list.add((SalariedEmployee)ois.readObject());
            }
            return list;

        } catch (FileNotFoundException ex) {
            System.out.println("FNF : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOE : " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("CNF : " + ex.getMessage());
        } finally {
            //close all resources
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                System.out.println("IOE : " + ex.getMessage());
            }
        }
        return null;
    }
    /**
     * This is an extension to the ObjectOutputStream to enable appending data to 
     * a file without writing stream headers.
     */
    class AppendingObjectOutputStream extends ObjectOutputStream {

      public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
            this.writeStreamHeader();
      }

      @Override
       protected void writeStreamHeader() throws IOException {
       // do not write a header, but reset:
       // this line added after another question
       // showed a problem with the original
        return;
      }

}
    /**
     * this method can be used to test the file reading and writing alone.
     * @param args 
     */
    
    public static void main(String [] args){
        DataFile df = new  DataFile();
        SalariedEmployee [] list = { new SalariedEmployee("mary","Tom",4567.0f),
                                     new SalariedEmployee("mark","paul",4567.0f),
                                     new SalariedEmployee("lily","Tom",2345.0f)
                                     };
        LinkedList<SalariedEmployee> ls = new LinkedList<SalariedEmployee>();
        for (SalariedEmployee s:list)
            ls.add (new SalariedEmployee (s));
        df.writeToFile("emp.ser", ls);
        LinkedList<SalariedEmployee> employeeList = new LinkedList<SalariedEmployee> ();
        employeeList = df.readFromFile("emp.ser");
        System.out.println(employeeList);
    }
}
