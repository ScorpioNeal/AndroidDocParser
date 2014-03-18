package com.androiddoc.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 获取主要内容
 * Design: GetStarted,Style,Pattern,BuildingBlocks,Downloads, Videos
 * Develop: Training, APIGuides, Tools
 * @author ScorpioNeal2013
 *
 */
public class GetMainFile {

	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("http://developer.android.com/index.html").get();
			Elements designElements = doc.select("div>div#quicknav>ul>li.design>ul>li");
			System.out.println(designElements.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
