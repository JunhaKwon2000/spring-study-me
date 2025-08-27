package com.winter.app.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/api/*")
public class RestTestController {
	
	@GetMapping("mono")
	public void m1() {
		WebClient webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com/photos/1").build();
		
		Mono<ResponseEntity<PhotoVO>> response = webClient.get().retrieve().toEntity(PhotoVO.class);
		PhotoVO result = response.block().getBody();
		System.out.println(result);
	}
	
	@GetMapping("list")
	public void m2() {
		WebClient webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
		
		Flux<UserVO> response = webClient.get().uri("/users").retrieve().bodyToFlux(UserVO.class);
		System.out.println(response.blockFirst());
		response.subscribe(flux -> {
			UserVO userVO = flux;
			System.out.println(userVO);
		});
	}
	
	@GetMapping("post")
	public void m3() {
		PostVO postVO = new PostVO();
		postVO.setTitle("test title");
		postVO.setBody("test body");
		postVO.setUserId(99L);
		
		WebClient webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
		Mono<PostVO> response = webClient.post().uri("/posts").bodyValue(postVO).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(PostVO.class);
		PostVO result = response.block();
		System.out.println(result);
	}
	
}
