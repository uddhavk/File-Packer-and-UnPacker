// UnPacking Application
import java.io.*;
import java.util.*;

class UnPack
{
    public static void main() throws Exception
    {
        int FileSize = 0;
        int i = 0;
        int iRet = 0;

        byte Key = 0x11;

        Scanner sobj = null;
        String FileName = null;
        String Header = null;
        String Tokens[] = null;

        File FilePack = null;
        File ExtractFile = null;

        // write the data in any type of file (text file,binary file,etc)
        FileInputStream ReadData = null;
        // Read the data from file
        FileOutputStream SendData = null;

        byte bHeader[] = new byte[100];
        byte Buffer[] = null;

        sobj = new Scanner(System.in);

        System.out.println("Enter the name to packed file : ");
        FileName = sobj.nextLine();

        FilePack = new File(FileName);

        if(FilePack.exists() == false)
        {
            System.out.println("Error : There is no such packed file");
            return;
        }

        ReadData = new FileInputStream(FilePack);

        while((iRet = ReadData.read(bHeader,0,100)) != -1)
        {
            Header = new String(bHeader);

            Header = Header.trim();

            // Seprating the FileName and Its Size
            Tokens = Header.split(" ");

            System.out.println("File Name : "+Tokens[0]);
            System.out.println("File Size : "+Tokens[1]);

            ExtractFile = new File(Tokens[0]);

            ExtractFile.createNewFile();

            SendData = new FileOutputStream(ExtractFile);

            FileSize = Integer.parseInt(Tokens[1]); // Actual FileSize of its name

            // Buffer for reading the data
            Buffer = new byte[FileSize];

            // Read from packed file
            ReadData.read(Buffer,0,FileSize);

            // Decrypt the data

            for(i = 0; i < FileSize; i++)
            {
                Buffer[i] = (byte)(Buffer[i] ^ Key);
            }
            
            // Write into extracted file
            SendData.write(Buffer,0,FileSize);
        }
    }
}