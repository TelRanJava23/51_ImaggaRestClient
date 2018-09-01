package telran.imagga.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import telran.imagga.dto.ResponseDto;
import telran.imagga.dto.Tag;

public class ImaggaClientAppl {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization",
				"<you security code>");
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		String url = "https://api.imagga.com/v1/tagging";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("url", "https://24smi.org/public/media/235x307/person/2017/12/22/4sqqykgn04bo-cheburashka.jpg")
				.queryParam("language", "fr");
		//String endpoints = "?url=https://24smi.org/public/media/235x307/person/2017/12/22/4sqqykgn04bo-cheburashka.jpg";
		ResponseEntity<ResponseDto> response = restTemplate.exchange(builder.build().encode().toUri(),
				HttpMethod.GET,requestEntity, ResponseDto.class);
		displayTags(response.getBody().getResults()[0].getTags());
		
		

	}

	private static void displayTags(Tag[] tags) {
		Arrays.stream(tags)
			.limit(3)
			.forEach(t -> System.out.println(t.getTag()+
				" -> "+t.getConfidence()) );
		
	}

}
