package com.androiddoc.parser;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 获取主要内容 Design: GetStarted,Style,Pattern,BuildingBlocks,Downloads, Videos
 * Develop: Training, APIGuides, Tools
 * 
 * @author ScorpioNeal2013
 * 
 */
public class GetMainFile {

    private static final String BASEURL = "http://developer.android.com";

    public static void main(String[] args) {
        GetMainFile mGetMainFile = new GetMainFile();
        System.out.println("--------------get design html -------------");
        mGetMainFile.getDesignHtml();
        System.out.println("\n\n--------------get training html-------------");
        mGetMainFile.getTrainingHtml();
    }

    public void getTraining() {
        try {
            Document document = Jsoup.parse(new File("doc.html"), "UTF-8");
            Elements elements = document.select("ul#nav>li.nav-section");
            for (int i = 0; i < elements.size(); i++) {
                Elements firstElements = elements.get(i).select(
                        "div.nav-section-header>a");
                System.out.println("第一级名称: " + firstElements.get(0).text());
                System.out.println("第一级URL : "
                        + firstElements.get(0).attr("href"));

                Elements secondElements = elements.get(i).select(
                        "ul>li.nav-section");
                for (int j = 0; j < secondElements.size(); j++) {
                    Elements secondElementsParent = secondElements.get(j)
                            .select("div>a");
                    System.out.println("\t第二级名称: "
                            + secondElementsParent.get(0).text());
                    System.out.println("\t第二级URL : "
                            + secondElementsParent.get(0).attr("href"));

                    Elements thirdElements = secondElements.get(j).select(
                            "ul>li>a");
                    for (int k = 0; k < thirdElements.size(); k++) {
                        Element thirdElement = thirdElements.get(k);
                        System.out.println("\t\t第三级名称: " + thirdElement.text());
                        System.out.println("\t\t第三级URL : "
                                + thirdElement.attr("href"));
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getDesignHtml() {
        try {
            Document doc = Jsoup.connect(
                    "http://developer.android.com/design/index.html").get();
            Elements mElements = doc.select("ul#nav>li.nav-section");
            for (Element mElement : mElements) {
                Element parentElement = mElement.select("div").first();
                Elements childElements = mElement.select("ul>li");
                System.out.println(parentElement.text() + " (" + BASEURL
                        + parentElement.child(0).attr("href") + ")");
                for (Element childElement : childElements) {
                    System.out.println("\t" + childElement.text() + "  "
                            + BASEURL + childElement.child(0).attr("href"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getTrainingHtml() {
        try {
            Document doc = Jsoup.connect(
                    "http://developer.android.com/training/index.html").get();
            Elements mElements = doc.select("ul#nav>li.nav-section");
            for (Element mElement : mElements) {
                Element parentElement = mElement.select("div").first();
                Elements childElements = mElement.select("ul>li.nav-section");
                System.out.println(parentElement.text() + " (" + BASEURL
                        + parentElement.child(0).attr("href") + ")");
                for (Element childElement : childElements) {
                    Element parentChildElement = childElement.select("div")
                            .first();
                    System.out.println("\t" + parentChildElement.text() + " ("
                            + BASEURL
                            + parentChildElement.child(0).attr("href") + ")");
                    Elements childChildElements = childElement.select("ul>li");
                    for (Element childchildElement : childChildElements) {
                        System.out.println("\t\t" + childchildElement.text()
                                + "  " + BASEURL
                                + childchildElement.child(0).attr("href"));
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
