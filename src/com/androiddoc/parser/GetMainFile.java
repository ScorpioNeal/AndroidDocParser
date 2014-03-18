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
		GetMainFile mGetMainFile = new GetMainFile();
		System.out.println("--------------get design html -------------");
		mGetMainFile.getDesignHtml();
		System.out.println("\n\n--------------get training html-------------");
		mGetMainFile.getTrainingHtml();
	}
	
	
	private void getDesignHtml(){
		try {
			Document doc = Jsoup.connect(
					"http://developer.android.com/design/index.html").get();
			Elements mElements = doc
					.select("ul#nav>li.nav-section");
			for(Element mElement : mElements){
				Element parentElement = mElement.select("div").first();
				Elements childElements = mElement.select("ul>li");
				System.out.println(parentElement.text() +" (" + BASEURL+parentElement.child(0).attr("href") + ")");
				for(Element childElement : childElements){
					System.out.println("\t" + childElement.text() + "  " + BASEURL+childElement.child(0).attr("href"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getTrainingHtml(){
		try {
			Document doc = Jsoup.connect(
					"http://developer.android.com/training/index.html").get();
			Elements mElements = doc
					.select("ul#nav>li.nav-section");
			for(Element mElement : mElements){
				Element parentElement = mElement.select("div").first();
				Elements childElements = mElement.select("ul>li.nav-section");
				System.out.println(parentElement.text() +" (" + BASEURL+parentElement.child(0).attr("href") + ")");
				for(Element childElement : childElements){
					Element parentChildElement = childElement.select("div").first();
					System.out.println("\t" + parentChildElement.text() +" (" + BASEURL+parentChildElement.child(0).attr("href") + ")");
					Elements childChildElements = childElement.select("ul>li");
					for(Element childchildElement : childChildElements){
						System.out.println("\t\t" + childchildElement.text() + "  " + BASEURL+childchildElement.child(0).attr("href"));
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
