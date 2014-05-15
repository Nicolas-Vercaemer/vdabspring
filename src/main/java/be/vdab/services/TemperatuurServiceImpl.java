package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.restclients.TemperatuurClient;
import be.vdab.valueobjects.Temperatuur;

@Service
public class TemperatuurServiceImpl implements TemperatuurService {

	private final TemperatuurClient temperatuurClient;

	@Autowired
	TemperatuurServiceImpl(TemperatuurClient temperatuurClient) {
		this.temperatuurClient = temperatuurClient;
	}

	@Override
	public Temperatuur temperatuur() {
		return temperatuurClient.getTemperatuur();
	}

}