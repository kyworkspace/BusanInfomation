package com.busan.main;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.busan.domain.BusanWeatherVO;

@Controller
@RequestMapping("/*")
public class TourInfoController {
	
	@GetMapping("/TourInformation")
	
	public String TourInformation(Model model) {
		try {
			Document doc = Jsoup.connect("https://weather.naver.com/rgn/cityWetrCity.nhn?cityRgnCd=CT008008#").get();
			Elements dayInfo = doc.select("table.tbl_weather");//���� ����
			String todayDate = dayInfo.select("thead span").eq(0).text();//���� ��¥
			String todayAMTemp = dayInfo.select("tbody span.temp").eq(0).text();//���� ���� 
			String todayPMTemp = dayInfo.select("tbody span.temp").eq(1).text();//���� ���� 
			String todayAMsrc = dayInfo.select("img").eq(0).attr("src");//���� ���� �̹���
			String todayPMsrc = dayInfo.select("img").eq(1).attr("src");//���� ���� �̹���
			String tmoDate = dayInfo.select("thead span").eq(1).text();//���� ��¥
			String tmoAMTemp = dayInfo.select("tbody span.temp").eq(2).text();//���� ���� 
			String tmoPMTemp = dayInfo.select("tbody span.temp").eq(3).text();//���� ���� 
			String tmoAMsrc = dayInfo.select("img").eq(2).attr("src");//���� ���� �̹���
			String tmoPMsrc = dayInfo.select("img").eq(3).attr("src");//���� ���� �̹���
			model.addAttribute("todayDate",todayDate);
			model.addAttribute("todayAMTemp",todayAMTemp);
			model.addAttribute("todayPMTemp",todayPMTemp);
			model.addAttribute("tmoDate",tmoDate);
			model.addAttribute("tmoAMTemp",tmoAMTemp);
			model.addAttribute("tmoPMTemp",tmoPMTemp);
			model.addAttribute("todayAMsrc",todayAMsrc);
			model.addAttribute("todayPMsrc",todayPMsrc);
			model.addAttribute("tmoAMsrc",tmoAMsrc);
			model.addAttribute("tmoPMsrc",tmoPMsrc);
			
			Elements weekly = doc.select("table.tbl_weather").eq(1);
			Elements tr = weekly.select("tr");
			ArrayList<BusanWeatherVO> arr = new ArrayList<>();
			for(int i = 0 ; i<tr.size(); i++) {
				BusanWeatherVO vo = new BusanWeatherVO();
				String weekday = tr.get(i).select("th").text();
				String weekAMTemp = tr.get(i).select("td span.temp").eq(0).text();//�µ�
				String weekPMTemp = tr.get(i).select("td span.temp").eq(1).text();
				String weekAMInfo = tr.get(i).select("td li.info").eq(0).text();//����
				String weekPMInfo = tr.get(i).select("td li.info").eq(1).text();
				String imgAMsrc = tr.get(i).select("img").eq(0).attr("src");//���� �̹���
				String imgPMsrc = tr.get(i).select("img").eq(1).attr("src");//���� �̹���
				vo.setWeekday(weekday);
				vo.setWeekAMInfo(weekAMInfo);
				vo.setWeekAMTemp(weekAMTemp);
				vo.setWeekPMInfo(weekPMInfo);
				vo.setWeekPMTemp(weekPMTemp);
				vo.setImgAMsrc(imgAMsrc);
				vo.setImgPMsrc(imgPMsrc);
				arr.add(vo);
			}
			model.addAttribute("weatherlist",arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "tourInformation";
	}
	@GetMapping(value="/gutour", produces="text/plain;charset=UTF-8")
	public @ResponseBody String MainNews(String area) {
			String url = null;
		if(area.equals("joong")) {
			url="https://www.google.com/search?ei=VnSIXLqqKcqm8QX8sZqACw&q=%EB%B6%80%EC%82%B0+%EC%A4%91%EA%B5%AC%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EC%A4%91%EA%B5%AC%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...41208.41631..42142...0.0..0.126.582.0j5......0....1..gws-wiz.8Q8swyMqE5I";
		}else if(area.equals("seo")) {
			url="https://www.google.com/search?ei=gXSIXIGLLYr38gXbw4DwAw&q=%EB%B6%80%EC%82%B0+%EC%84%9C%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EC%84%9C%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...31445.32787..33274...0.0..1.114.890.0j8......0....1..gws-wiz.......35i39j0i7i30j0.VVqeXTNGou0";
		}else if(area.equals("dong")) {
			url="https://www.google.com/search?ei=pHSIXLuXBIT48QXbgaTABA&q=%EB%B6%80%EC%82%B0+%EB%8F%99%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EB%8F%99%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3..35i39.84120.84575..85305...0.0..0.114.444.0j4......0....1..gws-wiz.......35i304i39.WM9K9OhtkP4";
		}else if(area.equals("youngdo")) {
			url="https://www.google.com/search?ei=z3SIXPWPLcv28AWooJzwDg&q=%EB%B6%80%EC%82%B0+%EC%98%81%EB%8F%84%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EC%98%81%EB%8F%84%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...27078.28619..29020...0.0..0.125.692.0j6......0....1..gws-wiz.t5fkwLF1OS4";
		}else if(area.equals("busanjin")) {
			url="https://www.google.com/search?ei=SnWIXM6rF4qR8gWZjraYDQ&q=%EB%B6%80%EC%82%B0+%EB%B6%80%EC%82%B0%EC%A7%84%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EB%B6%80%EC%82%B0%EC%A7%84%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3..35i39.12588.14946..15196...2.0..1.121.1785.0j16......0....1..gws-wiz.rSJVb13TNUA";
		}else if(area.equals("geumjeong")) {
			url="https://www.google.com/search?ei=VnSIXLqqKcqm8QX8sZqACw&q=%EB%B6%80%EC%82%B0+%EA%B8%88%EC%A0%95%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EA%B8%88%EC%A0%95%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...15902.16620..16814...0.0..0.123.948.0j8......0....1..gws-wiz.SiRYI53FnqY";
		}else if(area.equals("gangseo")) {
			url="https://www.google.com/search?ei=a3WIXL3_Jova8QXt4raYAw&q=%EB%B6%80%EC%82%B0+%EA%B0%95%EC%84%9C%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EA%B0%95%EC%84%9C%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3..0i30.11406.12664..13006...0.0..0.130.1052.0j9......0....1..gws-wiz.......0i13j0i13i30j0i8i13i30j0i13i5i30.ptNAw53zPgc";
		}else if(area.equals("sasang")) {
			url="https://www.google.com/search?ei=eXWIXPzAN4fq8wWb5J2YBw&q=%EB%B6%80%EC%82%B0+%EC%82%AC%EC%83%81%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EC%82%AC%EC%83%81%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...28457.30311..30495...3.0..0.123.1166.0j10......0....1..gws-wiz.......0i13j0i13i30j0i8i13i30j0i13i5i30j0j0i7i30.taNUK2hdpVg";
		}else if(area.equals("dongnea")) {
			url="https://www.google.com/search?ei=mXWIXL-SC4OF8gXKl53QBg&q=%EB%B6%80%EC%82%B0+%EB%8F%99%EB%9E%98%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EB%8F%99%EB%9E%98%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...6691.8084..8516...0.0..0.125.1063.0j9......0....1..gws-wiz.......0i13j0i13i30j0i8i13i30j0i13i5i30.hcqEcC_3DQI";
		}else if(area.equals("nam")) {
			url="https://www.google.com/search?ei=mXWIXL-SC4OF8gXKl53QBg&q=%EB%B6%80%EC%82%B0+%EB%82%A8%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EB%82%A8%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...12213.13717..13964...2.0..0.125.1265.0j11......0....1..gws-wiz.4UENL6LruUc";
		}else if(area.equals("book")) {
			url="https://www.google.com/search?ei=x3WIXI-6HZjq8wXpkY3oAg&q=%EB%B6%80%EC%82%B0+%EB%B6%81%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EB%B6%81%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...102003.102923..103169...0.0..0.111.644.0j6......0....1..gws-wiz.......0i13j0i13i30j0i8i13i30j0i13i5i30.Q1MpMSUnAaU";
		}else if(area.equals("haeundae")) {
			url="https://www.google.com/search?ei=L3aIXJKlHIqmoAT99p3QDg&q=%EB%B6%80%EC%82%B0+%ED%95%B4%EC%9A%B4%EB%8C%80%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%ED%95%B4%EC%9A%B4%EB%8C%80%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...21657.22488..22690...0.0..0.149.1252.0j11......0....1..gws-wiz.......0i13j0i8i13i30.cRdKZrQQS0w";
		}else if(area.equals("saha")) {
			url="https://www.google.com/search?ei=R3aIXJarB4ymoAS_u6GIDw&q=%EB%B6%80%EC%82%B0+%EC%82%AC%ED%95%98%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EC%82%AC%ED%95%98%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...15796.16286..16792...0.0..0.223.964.1j6j1......0....1..gws-wiz.5ce_vslLP-s";
		}else if(area.equals("yeonje")) {
			url="https://www.google.com/search?ei=WHaIXPj-LIOC-QawnpOoCw&q=%EB%B6%80%EC%82%B0+%EC%97%B0%EC%A0%9C%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EC%97%B0%EC%A0%9C%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...14018.15180..15410...0.0..0.120.1080.1j9......0....1..gws-wiz.RR41LGBtQGo";
		}else if(area.equals("suyoung")) {
			url="https://www.google.com/search?ei=fHaIXKKVNZCpoAT-nqeQCw&q=%EB%B6%80%EC%82%B0+%EC%88%98%EC%98%81%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EC%88%98%EC%98%81%EA%B5%AC+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...456.456..953...0.0..0.109.109.0j1......0....1..gws-wiz.uRz3T6THX5c";
		}else if(area.equals("gijang")) {
			url="https://www.google.com/search?ei=fnaIXM-eMpT1wAPchqioDw&q=%EB%B6%80%EC%82%B0+%EA%B8%B0%EC%9E%A5%EA%B5%B0+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&oq=%EB%B6%80%EC%82%B0+%EA%B8%B0%EC%9E%A5%EA%B5%B0+%EA%B0%80%EB%B3%BC%EB%A7%8C%ED%95%9C%EA%B3%B3&gs_l=psy-ab.3...16609.17369..17856...0.0..0.113.1086.0j10......0....1..gws-wiz.PCiUaM3HVIE";
		}
		JSONArray jarr = new JSONArray();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements div = doc.select("div.g");
			for(int i = 0 ; i<10; i++) {
				String title = div.get(i).select("h3.LC20lb").text();
				String link = div.get(i).select("a").attr("href");
				String content = div.get(i).select("div span.st").text();
				JSONObject jobj = new JSONObject();
				jobj.put("title",title);
				jobj.put("link",link);
				jobj.put("content",content);
				jarr.add(jobj);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jarr.toString();
	}
	
	
	
	
	
	
	
	

}