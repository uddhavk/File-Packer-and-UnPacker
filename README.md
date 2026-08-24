# Java File Packer & Unpacker

A Java-based File Packer and Unpacker that combines multiple `.txt` files into a single packed file and extracts them back when required.

The project also demonstrates basic XOR-based encryption and decryption using a byte key.

## 🚀 Features

- Pack multiple `.txt` files into a single file
- Unpack files from the packed file
- Store file name and file size in a 100-byte header
- XOR-based encryption during packing
- XOR-based decryption during unpacking
- Buffer-based file reading and writing
- Uses Java File I/O streams

## 🛠️ Technologies

- Java
- File Handling
- FileInputStream
- FileOutputStream
- Byte Arrays
- XOR Operation

## 📂 Project Structure

JavaFilePacker/
│
├── Pack.java
├── UnPack.java
└── README.md

## 🔄 Working Flow

### Packing

Input Folder
     ↓
Find .txt Files
     ↓
Create 100-byte Header
     ↓
Read File Data
     ↓
XOR Encryption (Key: 0x11)
     ↓
Write Header + Encrypted Data
     ↓
Packed File

### Unpacking

Packed File
     ↓
Read 100-byte Header
     ↓
Get File Name & File Size
     ↓
Read Encrypted Data
     ↓
XOR Decryption (Key: 0x11)
     ↓
Create Original File

## 📦 Packed File Format

Each file stored in the packed file follows this format:

┌────────────────────────┐
│ Header - 100 Bytes     │
│ File Name + File Size  │
├────────────────────────┤
│ Encrypted File Data    │
└────────────────────────┘

The same structure is repeated for every `.txt` file.

## ▶️ How to Run

### Compile

1.java Pack.java
2.java UnPack.java

### Pack Files

java Pack

Enter the name of the source folder:

Marvellous

Enter the name for packed file:

MarvellousPack

### Unpack Files

java UnPack

Enter the name of the packed file:

MarvellousPack

## 🔐 Encryption

The project uses XOR with:

byte key = 0x11;

Encryption:

data ^ key

Decryption:

encryptedData ^ key

The same XOR operation is used for both encryption and decryption because:

A ^ B ^ B = A

> Note: The XOR implementation is for educational purposes and is not suitable for securing sensitive data.

## ⚠️ Current Limitations

- Currently supports `.txt` files only
- Uses a fixed XOR key
- Does not preserve directory structure
- Console-based application
- Header parsing has limitations with filenames containing spaces

## 🔮 Future Enhancements

- Support all file types
- Recursive directory packing
- Preserve file metadata
- Add compression
- Add Java Swing GUI
- Use stronger encryption
- Add file integrity checking

## 👨‍💻 Author

**Uddhav Khatal**
