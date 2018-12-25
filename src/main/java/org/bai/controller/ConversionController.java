package org.bai.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.bai.ConversionBean;
import org.bai.proxy.ConversionServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ConversionServiceProxy proxy;

	@GetMapping("/change/from/{from}/to/{to}/quantity/{quantity}")
	public ConversionBean convertDevise(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<ConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/taux/from/{from}/to/{to}", ConversionBean.class,
				uriVariables);

		ConversionBean response = responseEntity.getBody();

		return new ConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

	@GetMapping("/change-feign/from/{from}/to/{to}/quantity/{quantity}")
	public ConversionBean convertDeviseFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		ConversionBean response = proxy.getTaux(from, to);

		logger.info("{}", response);

		return new ConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

}
