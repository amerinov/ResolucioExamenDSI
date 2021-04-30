package cat.tecnocampus.notes.usersAdapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



import cat.tecnocampus.notes.application.portsOut.CallUserExists;

@Component
public class UserExistsAdapter implements CallUserExists {

	private final RestTemplate restTemplate;
	private final CircuitBreakerFactory circuitBreakerFactory;
	private final String userServiceUrl;
	
	
	public UserExistsAdapter(RestTemplate restTemplate, CircuitBreakerFactory circuitBreakerFactory,
			                       @Value("${app.users-service.host}") String userServiceHost) {
		this.restTemplate = restTemplate;
		this.circuitBreakerFactory =  circuitBreakerFactory;
		this.userServiceUrl = "http://" + userServiceHost + "/users/exists/";
		
	}


	@Override
	
	public String userExists(String userName) {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user");
		return circuitBreaker.run(() -> restTemplate.getForObject( userServiceUrl + userName, String.class),
		throwable -> {
			System.out.println(throwable.getMessage());
			return "open";});
		
	}
}
