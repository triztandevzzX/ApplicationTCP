package com.example.sae302applicationdevauxtristan;

public class FileItem {
    private String fileName;
    private long fileSize; // Taille du fichier en octets
    private String filePath; // Chemin complet du fichier

    // Constructeur
    public FileItem(String fileName, long fileSize, String filePath) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    // Getters
    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFilePath() {
        return filePath;
    }
}
