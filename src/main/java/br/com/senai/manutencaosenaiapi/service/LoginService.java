package br.com.senai.manutencaosenaiapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.manutencaosenaiapi.entity.Login;
import br.com.senai.manutencaosenaiapi.repository.LoginsRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginsRepository repository;
	
	public Login logar(String login, String senha, String perfil) {
		Login loginEncontrado = repository.buscarPor(login, senha, perfil);
		Preconditions.checkNotNull(loginEncontrado, 
				"Usuário e/ou senha inválidos. "
				+ "Verifique os dados e tente novamente");
		return loginEncontrado;
	}

}
