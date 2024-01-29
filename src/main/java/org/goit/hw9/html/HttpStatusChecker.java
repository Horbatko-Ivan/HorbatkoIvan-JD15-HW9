package org.goit.hw9.html;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class HttpStatusChecker {

  private static final String BASE_URL = "https://http.cat/";

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public String getStatusImage(int code) {

    String url = BASE_URL + code + ".jpg";

    try {
      Connection connection = Jsoup.connect(url).ignoreContentType(true);
      Connection.Response response = connection.execute();
      if (response.statusCode() == 200) {
        return url;
      } else {
        throw new FileNotFoundException();
      }
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }
}
