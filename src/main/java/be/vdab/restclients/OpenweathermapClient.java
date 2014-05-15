package be.vdab.restclients;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import be.vdab.valueobjects.Temperatuur;

@Component
class OpenweathermapClient implements TemperatuurClient {
	private final URI openweathermapURL;
	private final RestTemplate restTemplate;

	@Autowired
	OpenweathermapClient(@Value("${openweathermapURL}") URI openweathermapURL,
			RestTemplate restTemplate) {
		this.openweathermapURL = openweathermapURL;
		this.restTemplate = restTemplate;
	}

	@Override
	public Temperatuur getTemperatuur() {
		Current current = restTemplate.getForObject(openweathermapURL,
				Current.class);
		return new Temperatuur(current.temperature.value,
				current.lastupdate.value);
	}

}