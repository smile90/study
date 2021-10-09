package org.duancq90.pachong.area;

import java.io.IOException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
public class Area {

  private final static String INDEX_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/index.html";
  private final static String MAIN_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/";

  public static void main(String[] args) throws IOException, InterruptedException {

    Document document = Jsoup.parse(getHTML(INDEX_URL));
//    log.debug("document:{}", document.html());

    Elements elements = document.getElementsByClass("provincetr");
//    log.debug("elements:{}", elements.html());

    log.info("parentCode,areaCode,areaName,areaLevel");

    elements.forEach(element -> {
//      log.debug("\nelement:{}", element.html());
      Elements as = element.getElementsByTag("a");
      as.forEach(a -> {
        String url = a.attr("href");
        String code = url.substring(0, url.indexOf(".")) + "0000000000";
//        log.info("\nparentCode:{},areaCode:{},areaName:{},level:{},url:{}",
//            "000000000000", code, a.text(), 1, url);
          log.info("{},{},{},{}", "000000000000", code, a.text(), 1);

        // 市
        String cityUrl = MAIN_URL + url;
        Document cityDoc = Jsoup.parse(getHTML(cityUrl));
        Elements cityTrElements = cityDoc.getElementsByClass("cityTr");
        cityTrElements.forEach(cityTrElement -> {
          Result count = write(cityTrElement, code, 2, 0, 1);
          // 区
          if (count != null && StringUtils.isNotBlank(count.getUrl())) {
            String countUrl = cityUrl.substring(0, cityUrl.lastIndexOf("/")) + "/" + count.getUrl();
            Document countDoc = Jsoup.parse(getHTML(countUrl));
            Elements countTrElements = countDoc.getElementsByClass("countytr");
            countTrElements.forEach(countTrElement -> {
              Result town = write(countTrElement, count.getCode(), 3, 0, 1);
              // 乡镇
              if (town != null && StringUtils.isNotBlank(town.getUrl())) {
                String townUrl = countUrl.substring(0, countUrl.lastIndexOf("/")) + "/" + town.getUrl();
                Document townDoc = Jsoup.parse(getHTML(townUrl));
                Elements townTrElements = townDoc.getElementsByClass("towntr");
                townTrElements.forEach(townTrElement -> {
                  Result village = write(townTrElement, town.getCode(), 4, 0, 1);
                  // 村、街道
//                  if (village != null && StringUtils.isNotBlank(village.getUrl())) {
//                    String villageUrl = townUrl.substring(0, townUrl.lastIndexOf("/")) + "/" + village.getUrl();
//                    Document villageDoc = Jsoup.parse(getHTML(villageUrl));
//                    Elements villageTrElements = villageDoc.getElementsByClass("villagetr");
//                    villageTrElements.forEach(villageTrElement -> write(villageTrElement, village.getCode(),5, 0, 2));
//                  }
                  sleep();
                });
              }
            });
          }
        });
      });
    });

    Thread.sleep(90000000);
  }

  private static Result write(Element trElement, String parentCode, int level, int codeIndex, int nameIndex) {
    Elements tdElements = trElement.getElementsByTag("td");
    Element codeElement = tdElements.get(codeIndex);
    Element nameElement = tdElements.get(nameIndex);
    String thisCode = codeElement.text();
    String thisUrl = codeElement.getElementsByTag("a").attr("href");
//    log.info("\nparentCode:{},areaCode:{},areaName:{},level:{},url:{}",
//        Optional.of(parentCode).get(), thisCode, nameElement.text(), level, thisUrl);
    log.info("{},{},{},{}", Optional.of(parentCode).get(), thisCode, nameElement.text(), level);

    if (StringUtils.isNotBlank(thisUrl)) {
      return new Result(thisCode, thisUrl);
    } else {
      return null;
    }
  }

  private static String getHTML(String url) {
    HttpClientBuilder builder = HttpClientBuilder.create();
    builder.setUserAgent("Mozilla/5.0(Windows;U;Windows NT 5.1;en-US;rv:0.9.4)");

    HttpResponse response = null;
    String html = null;
    try {
      response = builder.build().execute(new HttpGet(url));
//      log.info("statusLine:{},url:{}", response.getStatusLine(), url);
      html = EntityUtils.toString(response.getEntity(), "GBK");
//      log.debug("html:{}", html);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return html;
  }

  private static void sleep() {
    try {
      Thread.sleep(Double.valueOf(Math.random() * 10.0).intValue() * 100L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Data
  @AllArgsConstructor
  static
  class Result {
    private String code;
    private String url;
  }
}
