package lab.yearnlune.websocketunittestexample.controller;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;

import lab.yearnlune.websocketunittestexample.MessageFrameHandler;
import lab.yearnlune.websocketunittestexample.StompSupport;
import lab.yearnlune.websocketunittestexample.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebsocketControllerTest extends StompSupport {

	@Test
	public void findUsers() throws ExecutionException, InterruptedException, TimeoutException {
		/* GIVEN */
		MessageFrameHandler<UserDTO[]> handler = new MessageFrameHandler<>(UserDTO[].class);
		this.stompSession.subscribe("/user/queue/users", handler);

		/* WHEN */
		this.stompSession.send("/app/users", "");

		/* THEN */
		List<UserDTO> userList = List.of(handler.getCompletableFuture().get(3, TimeUnit.SECONDS));

		assertThat(userList, notNullValue());
		assertThat(userList.size(), greaterThan(0));
	}

	@Test
	public void findUser() throws ExecutionException, InterruptedException, TimeoutException {
		/* GIVEN */
		MessageFrameHandler<UserDTO> handler = new MessageFrameHandler<>(UserDTO.class);
		this.stompSession.subscribe("/user/queue/user", handler);

		/* WHEN */
		this.stompSession.send("/app/user", "aaa");

		/* THEN */
		UserDTO user = handler.getCompletableFuture().get(3, TimeUnit.SECONDS);

		assertThat(user, notNullValue());
		assertThat(user.getName(), is("AAA"));
	}

}
