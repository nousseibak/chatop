package com.openclassrooms.chatop.config.media;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
public class FileHelper {


    /**
     * Saves a file to the specified directory.
     *
     * @param uploadDir     the directory to save the file to
     * @param fileName      the name of the file
     * @param multipartFile the file to save
     * @throws IOException if an I/O error occurs
     */
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save the file: " + fileName, ioe);
        }
    }

    /**
     * Deletes a file from the specified directory.
     *
     * @param deleteDir the directory to delete the file from
     * @param filename  the name of the file to delete
     * @return true if the file was deleted, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public static boolean deleteFile(String deleteDir, String filename) throws IOException {
        Path filePath = Paths.get(deleteDir).resolve(filename);
        return Files.deleteIfExists(filePath);
    }

    /**
     * Reads a file from the specified directory and returns its content as a byte array.
     *
     * @param directory the directory to read the file from
     * @param filename  the name of the file to read
     * @return a byte array containing the content of the file
     * @throws IOException if an I/O error occurs
     */
    public static byte[] getByteFile(String directory, String filename) throws IOException {
        Path filePath = Paths.get(directory).resolve(filename);
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new IOException("Could not read file: " + filename, e);
        }
    }


    public static void deleteFile(String pathName) throws IOException {
        Path filePath = Paths.get(pathName);

        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }

    public static boolean doesFileExist(String pathName) {
        Path filePath = Paths.get(pathName);
        return Files.exists(filePath);
    }

    public static byte[] getFileBytes(String pathName) throws IOException {
        Path filePath = Paths.get(pathName);
        return Files.readAllBytes(filePath);
    }

    public static void remplacerNomFichier(String cheminFichier, String nouveauNom) {
        File fichier = new File(cheminFichier);

        if (fichier.exists()) {
            String cheminRepertoire = fichier.getParent();
            String nouveauChemin = cheminRepertoire + File.separator + nouveauNom;

            File nouveauFichier = new File(nouveauChemin);

            if (fichier.renameTo(nouveauFichier)) {
                log.info("Le nom du fichier a été remplacé avec succès.");
            } else {
                log.error("Impossible de remplacer le nom du fichier.");
            }
        } else {
            log.error("Le fichier spécifié n'existe pas.");
        }
    }

}