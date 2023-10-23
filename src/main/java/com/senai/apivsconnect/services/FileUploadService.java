package com.senai.apivsconnect.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileUploadService {
    // System.getProperty("user.dir") -> Retorna o diretório raiz do projeto
    private Path diretorioImg = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img");

    public String fazerUpload(MultipartFile imagem) throws IOException {
        if (imagem.isEmpty()) {
            System.out.println("Imagem vazia");
            return null;
        }

        // Dividiremos o nome do arquivo em um array, para termos a extensão da imagem
        String[] nomeArquivoArray = imagem.getOriginalFilename().split("\\.");
        String sufixoArquivo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
        String extensaoArquivo = nomeArquivoArray[nomeArquivoArray.length - 1];

        String nomeImagem = sufixoArquivo + "." + extensaoArquivo;

        File imagemCriada = new File(diretorioImg + "\\" + nomeImagem);

        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imagemCriada));

        stream.write(imagem.getBytes());
        stream.close();

        return nomeImagem;
    }
}
