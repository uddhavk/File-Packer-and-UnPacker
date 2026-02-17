// Packing Application
import java.io.*;
import java.util.*;
public static void main(String A[]) throws Exception
{
    String Header = null;

    byte key = 0x11;

    int iRet = 0;
    int i = 0, j = 0;

    byte Buffer[] = new byte[1024];
    byte bHeader[] = new byte[100];

    Scanner sobj = new Scanner(System.in);

    System.out.println("Enter the name of folder : ");
    String FolderName = sobj.nextLine();

    System.out.println("Enter the name of packed file : ");
    String PackName = sobj.nextLine();

    File SearchFolder = new File(FolderName);

    if((SearchFolder.exists()) && (SearchFolder.isDirectory()))
    {
        System.out.println("Folder is present");

        File PackFile = new File(PackName);
        PackFile.createNewFile();

        // write the data in any type of file (text file,binary file,etc)
        FileOutputStream SendData = new FileOutputStream(PackFile);

        // Read the data from file
        FileInputStream ReadData = null;

        // Count the total number of files in searched folder
        File fArr[] = SearchFolder.listFiles();
        
        System.out.println("Number of files in the folder are : "+fArr.length);


        for(i = 0; i < fArr.length; i++)
        {
            ReadData = new FileInputStream(fArr[i]);

            // Checks Only .txt extension files
            if(fArr[i].getName().endsWith(".txt"))
            {
                // Header Function
                Header = fArr[i].getName() + " " + fArr[i].length();

                //It counts all characters, including spaces and special characters
                for(j = Header.length(); j < 100; j++)
                {
                    Header = Header + " ";
                }

                // Converts the String into byte array
                bHeader = Header.getBytes();

                // Write Header into pack file
                SendData.write(bHeader,0,100);

                while((iRet = ReadData.read(Buffer)) != -1)
                {

                    // Encryption
                    for(j = 0; j < iRet; j++)
                    {
                        Buffer[j] = (byte)(Buffer[j] ^ key);
                    }

                    // write the files data into pack file
                    SendData.write(Buffer,0,iRet);
                }  // End of while

            } // End of if
            ReadData.close();
        } // End of for
        SendData.close();

    } // End of if
    else
    {
        System.out.println("There is no such folder");
    }
    sobj.close();
}