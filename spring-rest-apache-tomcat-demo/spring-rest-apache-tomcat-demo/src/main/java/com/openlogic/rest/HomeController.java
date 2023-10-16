package com.openlogic.rest;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	private static final int INSERTS = 1;
	private static final String THE_URL = "http://pleasechangethistotheurlofthehttpserveryouwanttoretrieveapagefrom.com/";
	private static final String OK = "OK";
	
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/list")
	public List<Product> checkThread() throws InterruptedException {
		return productRepository.findAll();
	}

	@PostMapping("/save")
	@GetMapping("/save")
	public String saveItem() throws InterruptedException {
		try {
			for (int i = 0; i < INSERTS; i++) {
				Product item = new Product();
				item.setName(RandomStringUtils.randomAlphanumeric(5));
				item.setPrice(RandomUtils.nextLong(10, 1000));
				productRepository.save(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OK;
	}
	
	@SuppressWarnings("unused")
	@GetMapping("/get")
	public String getPage() throws IOException {
		Document document = Jsoup.connect(THE_URL).get();
	    String webpage = document.html();
	    return OK;
	}  
	
	@SuppressWarnings("unused")
	@GetMapping("/multiply")
	public String multiplyDoubles() {
		Random random = new Random();
		for (int i = 0; i < 50000; i++) {
			double j = random.nextDouble(1000) % random.nextDouble(1000);
		}
	    return OK;
	}  
}
