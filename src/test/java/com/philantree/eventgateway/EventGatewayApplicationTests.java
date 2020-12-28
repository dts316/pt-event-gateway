package com.philantree.eventgateway;

import static org.assertj.core.api.Assertions.assertThat;
import com.philantree.eventgateway.controller.DefaultController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventGatewayApplicationTests {

	@Autowired
	private DefaultController defaultController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(defaultController).isNotNull();
	}

}
