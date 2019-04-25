package com.accenture.videobot;

import org.junit.Before;
import org.junit.Test;

import com.accenture.videobot.helper.VideobotMessagesendHelper;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

public class DelayTest {

	private VideobotMessagesendHelper service;
	
	@Before
	public void setUp() throws Exception {
		service = spy(new VideobotMessagesendHelper());
		
	}

	@Test
	public void testDelay() {
		System.out.println("Início das interações do Delay");
		for(int i = 1; i <= 3; i++){
			System.out.println(i);
			service.delay(1);
		}
		System.out.println("FIM!!!!");
		verify(service, times(3)).delay(anyInt());
	}

}
