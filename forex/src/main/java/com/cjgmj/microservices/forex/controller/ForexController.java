package com.cjgmj.microservices.forex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cjgmj.microservices.forex.entity.ExchangeValue;
import com.cjgmj.microservices.forex.repository.ExchangeValueRepository;

@RestController
public class ForexController {

	// This help us identify which instance service is giving the response back
	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

		// Get the port from environment and set it into the response bean
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		return exchangeValue;
	}

}