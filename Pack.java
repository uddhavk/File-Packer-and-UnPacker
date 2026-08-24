import java.util.*;
import java.io.*;

class Pack
{
    public static void main(String A[]) throws Exception
    {

        // Variable declaration
        String FolderName = null;
        String PackName = null;
        String Header = null;

        int i = 0, j = 0, iRet = 0;

        Scanner sobj = null;

        // Creating new File for storing data of other files
        File PackFile = null;

        // write the data in any type of file(text file, binary file, etc)
        FileOutputStream SendData = null;

        // Read the data from file
        FileInputStream ReadData = null;

        // Count total number of files in searched folder
        File fArr[];

        byte Buffer[] = new byte[1024];
        byte bHeader[] = new byte[100];
        byte key = 0x11;

        sobj = new Scanner(System.in);

        System.out.println("Enter the name of folder : ");
        FolderName = sobj.nextLine();

        System.out.println("Enter the name for packed file : ");
        PackName = sobj.nextLine();

        File SearchFolder = new File(FolderName);

        if((SearchFolder.exists()) && (SearchFolder.isDirectory()))
        {
            System.out.println("Folder is present");

            PackFile = new File(PackName);
            PackFile.createNewFile();

            SendData = new FileOutputStream(PackFile);
            
            fArr = SearchFolder.listFiles();

            System.out.println("Number of files in the folder are : " + fArr.length);

            for(i = 0; i < fArr.length; i++)
            {

                // Checks Only .txt extension files 
                if(fArr[i].isFile() && fArr[i].getName().endsWith(".txt"))
                {

                    ReadData = new FileInputStream(fArr[i]);

                    // Header function
                    Header = fArr[i].getName() + " " + fArr[i].length();

                    // It counts all characters, Including spaces and special characters
                    for(j = Header.length(); j < 100; j++)
                    {
                        Header = Header + " ";
                    }

                    // Converts the String into byte array
                    bHeader = Header.getBytes();

                    // Write Header into pack file
                    SendData.write(bHeader, 0, 100);

                    // Reading Data 
                    while((iRet = ReadData.read(Buffer)) != -1)
                    {
                        // Encryption
                        for(j = 0; j < iRet; j++)               
                        {
                            Buffer[j] = (byte)(Buffer[j] ^ key);
                        }

                        // write the files data into pack file
                        SendData.write(Buffer, 0, iRet);
                    }   // End of while
                    ReadData.close();

                }   // End of if

            } // End of for
            System.out.println("Data Successfully Encrypted and Packed");


            SendData.close();
        }  // End of if
        else
        {
            System.out.println("There is no such folder");
        } // End of if

        sobj.close();
    }
}