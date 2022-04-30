package br.com.senai.manutencaosenaiapi;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.manutencaosenaiapi.entity.Cliente;
import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.entity.Tecnico;
import br.com.senai.manutencaosenaiapi.enums.Sexo;
import br.com.senai.manutencaosenaiapi.repository.PecasRepository;
import br.com.senai.manutencaosenaiapi.repository.TecnicosRepository;
import br.com.senai.manutencaosenaiapi.service.ClienteService;
import br.com.senai.manutencaosenaiapi.service.OrdemDeServicoService;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import br.com.senai.manutencaosenaiapi.service.TecnicoService;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
		
	}
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PecaService pecaService;
	
	@Autowired
	private OrdemDeServicoService ordemService;	
	
	@Bean	
	public CommandLineRunner commandLineRunner(ApplicationContext ac) {
		return args -> {
			try {
				Cliente novoCliente = new Cliente();
				novoCliente.setNome("Jhonny");
				novoCliente.setSobrenome("Depp");
				novoCliente.setDataDeNascimento(LocalDate.of(1973, 3, 15));
				novoCliente.setCpf("005.900.289-10");
				novoCliente.setSexo(Sexo.M);
				novoCliente.setEndereco("Rua josé das couves");
				this.clienteService.inserir(novoCliente);
			}catch (Exception e) {				
				System.out.println(e.getMessage());
			}
			
		};
	}	

}
