import java.util.*;
import java.io.*;

class UnPack
{
    public static void main(String A[]) throws Exception
    {
        // Variable declaration
        File PackFile = null;
        File ExtractFile = null;

        Scanner sobj = null;
        String PackFileName = null;
        String Header = null;
        String Tokens[] = null;

        // write the data in any type of file(text file, binary file, etc)
        FileOutputStream SendData = null;

        // Read the data from file
        FileInputStream ReadData = null;

        byte bHeader[] = new byte[100];
        byte Buffer[];
        byte key = 0x11;

        int iRet = 0, i = 0;
        int FileSize = 0;

        sobj = new Scanner(System.in);

        System.out.println("Enter the name of packed file : ");
        PackFileName = sobj.nextLine();

        PackFile = new File(PackFileName);

        if(PackFile.exists() == false)
        {
            System.out.println("Error : There is no such packed file");
            return;
        }

        ReadData = new FileInputStream(PackFile);

        while((iRet = ReadData.read(bHeader, 0, 100)) != -1)
        {
            Header = new String(bHeader);
            // Removing WhiteSpaces from header
            Header = Header.trim();

            // Seperating the Filename and its Size
            Tokens = Header.split(" ");

            System.out.println("File Name : " + Tokens[0]);
            System.out.println("File Size : " + Tokens[1]);

            // Creating new file for writing encrypted data
            ExtractFile = new File(Tokens[0]);
            ExtractFile.createNewFile();

            SendData = new FileOutputStream(ExtractFile);

            //Actual FileSize of its name
            FileSize = Integer.parseInt(Tokens[1]);

            // Buffer for reading the data
            Buffer = new byte[FileSize];

            // Read from packed file
            ReadData.read(Buffer, 0, FileSize);

            // Decrypt the data
            for(i = 0; i < FileSize; i++)
            {
                Buffer[i] = (byte)(Buffer[i] ^ key);
            }

            // Write into extracted file
            SendData.write(Buffer, 0, FileSize);

            SendData.close();
        }   // End of while

    ReadData.close();

    sobj.close();
        
    }
}