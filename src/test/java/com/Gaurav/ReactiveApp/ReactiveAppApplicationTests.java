package com.Gaurav.ReactiveApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
class ReactiveAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void monoTest(){

		Mono<String> m1 = Mono.just("Testing Mono");
		m1.log().subscribe(data -> System.out.println(data));

	}
}
