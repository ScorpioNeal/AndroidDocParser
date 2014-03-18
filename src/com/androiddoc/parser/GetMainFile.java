package com.androiddoc.parser;

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
		try {
			Document doc = Jsoup.connect(
					"http://developer.android.com/design/index.html").get();
			Elements mElements = doc
					.select("ul#nav>li.nav-section");
			for(Element mElement : mElements){
				Element parentElement = mElement.select("div").first();
				Elements childElements = mElement.select("ul>li");
				System.out.println(parentElement.text() +" (" + parentElement.child(0).attr("href") + ")");
				for(Element childElement : childElements){
					System.out.println("\t" + childElement.text() + "  " + childElement.child(0).attr("href"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
