package lab.yearnlune.websocketunittestexample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

	public static final String ENDPOINT = "/stomp/unit";
	public static final String TOPIC = "/topic";
	public static final String DIRECT = "/queue";
	public static final String PREFIX = "/app";

	private final StompInboundHandler stompInboundHandler;

	public StompWebSocketConfig(StompInboundHandler stompInboundHandler) {
		this.stompInboundHandler = stompInboundHandler;
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(ENDPOINT)
			.withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(stompInboundHandler);
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes(PREFIX)
			.enableSimpleBroker(TOPIC, DIRECT);
	}
}
