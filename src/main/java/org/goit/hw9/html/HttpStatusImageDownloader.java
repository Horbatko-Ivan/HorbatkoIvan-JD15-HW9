package org.goit.hw7.html;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpStatusImageDownloader {

    public void downloadStatusImage(int code) throws IOException {
        String url = new HttpStatusChecker().getStatusImage(code);

        if (!url.contains("Error request for")) {

            String imagePath = "src/main/resources/downloadImages" + code + ".jpg";

            try {
                Connection connection = Jsoup.connect(url).ignoreContentType(true);
                Connection.Response response = connection.execute();
                int statusCode = response.statusCode();

                if (statusCode == 200) {

                    try (InputStream inputStream = response.bodyStream();
                         OutputStream outputStream = new FileOutputStream(imagePath)) {

                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }
}
