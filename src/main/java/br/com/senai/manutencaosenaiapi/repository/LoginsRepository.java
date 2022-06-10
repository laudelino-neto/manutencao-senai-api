package br.com.senai.manutencaosenaiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.manutencaosenaiapi.entity.Login;

@Repository
public interface LoginsRepository 
	extends JpaRepository<Login, Integer>{
	
	@Query(value = 
			"SELECT l "
			+ "FROM Login l "
			+ "WHERE l.login = :login "
			+ "AND l.senha = :senha "
			+ "AND l.perfil = :perfil ")
	Login buscarPor(String login, String senha, String perfil);

}
