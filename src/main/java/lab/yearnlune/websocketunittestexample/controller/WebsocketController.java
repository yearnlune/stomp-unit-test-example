package lab.yearnlune.websocketunittestexample.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import lab.yearnlune.websocketunittestexample.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WebsocketController {

	@MessageMapping("/users")
	@SendToUser
	public List<UserDTO> findUserList(
		SimpMessageHeaderAccessor headerAccessor,
		@Payload(required = false) String payload) {
		return Arrays.asList(UserDTO.builder()
				.id("aaa")
				.name("AAA")
				.build(),
			UserDTO.builder()
				.id("bbb")
				.name("BBB")
				.build());
	}

	@MessageMapping("/user")
	@SendToUser
	public UserDTO findUser(
		SimpMessageHeaderAccessor headerAccessor,
		@Payload String payload) {
		return UserDTO.builder()
			.id(payload)
			.name("AAA")
			.build();
	}
}
