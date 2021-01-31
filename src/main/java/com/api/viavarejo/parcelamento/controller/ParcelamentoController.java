package com.api.viavarejo.parcelamento.controller;

import java.awt.List;
import java.util.ArrayList;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.viavarejo.parcelamento.data.CondicaoPagamento;
import com.api.viavarejo.parcelamento.data.Parcela;
import com.api.viavarejo.parcelamento.data.Parcelamento;
import com.api.viavarejo.parcelamento.data.Produto;

@RestController
@RequestMapping("/parcelamento")
public class ParcelamentoController {

	@Autowired(required=true)
	//private SelicClient selicClient;
	
    @GetMapping
	public ResponseEntity<String> get(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ArrayList<Parcela> Post(@RequestBody Parcelamento parcelamento)
    {

    	ArrayList<Parcela> parcelas = new ArrayList<>();
    	
    	/*ArrayList errorList = new ArrayList();
    	if (validateProduto(parcelamento.getProduto()).size() > 0) {
    		errorList.addAll(validateProduto(parcelamento.getProduto()));
    	}
    	
    	if (validateCondicaoPagamento(parcelamento.getCondicaoPagamento()).size() > 0) {
    		errorList.addAll(validateCondicaoPagamento(parcelamento.getCondicaoPagamento()));
    	}*/

    	if (parcelamento.getCondicaoPagamento().getQtdeParcelas() < 6) {
    		parcelas = this.parcelamentoSimples(parcelamento);
    	}else {
    		parcelas = this.parcelamentoComJuros(parcelamento);
    	}
    	
    	
    	return parcelas;
    }

    private ArrayList<Parcela> parcelamentoSimples(Parcelamento parcelamento){
    	
    	ArrayList<Parcela> parcelas = new ArrayList<>();
    	
    	
    	double valorParcela = 
    			(parcelamento.getProduto().getValor() - 
    					parcelamento.getCondicaoPagamento().getValorEntrada()) /
    						parcelamento.getCondicaoPagamento().getQtdeParcelas();
    	
    	for (int i = 0; i < parcelamento.getCondicaoPagamento().getQtdeParcelas(); i++) {
			Parcela parcela = new Parcela();
			parcela.setNumeroParcela(i + 1);
			parcela.setValor(valorParcela);
			parcela.setTaxaJurosAoMes(0);
			
			parcelas.add(parcela);
		}
    	
    	return parcelas;
    }
   	
    private ArrayList<Parcela> parcelamentoComJuros(Parcelamento parcelamento){
    	
    	ArrayList<Parcela> parcelas = new ArrayList<>();
    	
    	
    	double valorParcela = 
    			(parcelamento.getProduto().getValor() - 
    					parcelamento.getCondicaoPagamento().getValorEntrada()) /
    						parcelamento.getCondicaoPagamento().getQtdeParcelas();
    	
    	double percetualJuros = this.GetJuros(); 
    	percetualJuros = ((valorParcela * percetualJuros) / 100);
    	valorParcela += percetualJuros;
    	
    	for (int i = 0; i < parcelamento.getCondicaoPagamento().getQtdeParcelas(); i++) {
			Parcela parcela = new Parcela();
			parcela.setNumeroParcela(i + 1);
			parcela.setValor(valorParcela);
			parcela.setTaxaJurosAoMes(percetualJuros);
			
			parcelas.add(parcela);
		}
    	
    	return parcelas;
    }    
   	
    private ArrayList validateProduto(Produto produto){
    	
    	ArrayList errorList = new ArrayList();
    	
    	if(produto.getCodigo() <= 0) {
    		errorList.add("Codigo de produto inválido.");
    	}
    	
    	if(produto.getNome().equals("")) {
    		errorList.add("Nome de produto inválido.");
    	}
    	
    	if(produto.getValor() <= 0) {
    		errorList.add("Valor de produto inválido.");
    	}
    	
    	return errorList;
    }
    
    private double GetJuros() {
    	
    	//Employee emp = loadBalancer.getData();
		//System.out.println(emp.getEmpId());
    	
    	//SelicResponseExternalData selicData = selicClient.getData();
    	//System.out.println(selicData.getValor());
    	
    	return 1.15;
    }
    
    private ArrayList validateCondicaoPagamento(CondicaoPagamento condicaoPagamento){
    	ArrayList errorList = new ArrayList();
    	
    	if(condicaoPagamento.getQtdeParcelas() <= 0) {
    		errorList.add("Quantida de parcelas inválida.");
    	}
    	
    	if(condicaoPagamento.getValorEntrada() <= 0) {
    		errorList.add("Quantidade de parcelas inválida.");
    	}
    	
    	return errorList;
    }

    
}