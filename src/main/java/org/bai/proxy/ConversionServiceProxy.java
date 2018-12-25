package org.bai.proxy;

import org.bai.ConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Déclare qu'il s'agit d'un client feign 
//et que l'URL à laquelle le service de change est présent est localhost:8000
@FeignClient(name = "service-forex")
@RibbonClient(name="service-forex")
public interface ConversionServiceProxy {
	
	//URI du service que nous voudrions consommer
	@GetMapping("/taux/from/{from}/to/{to}")
	public ConversionBean getTaux(@PathVariable("from") String from, @PathVariable("to") String to);

}
