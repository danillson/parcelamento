package com.api.viavarejo.parcelamento.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.4390/dados?formato=json&dataInicial=01/01/2021&dataFinal=31/01/2021")
public interface SelicClient {
	
	@RequestMapping(method=RequestMethod.GET, value="")
	public SelicResponseExternalData getData();
	
}
