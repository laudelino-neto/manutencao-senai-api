package br.com.senai.manutencaosenaiapi;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.senai.manutencaosenaiapi.entity.Login;
import br.com.senai.manutencaosenaiapi.service.LoginService;
import br.com.senai.manutencaosenaiapi.view.TelaConsultaDePeca;
import br.com.senai.manutencaosenaiapi.view.TelaLogin;

@SpringBootApplication
public class InitApp {
	
  @Autowired
  private TelaLogin telaDeLogin;

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
	}
			
	@Bean	
	public CommandLineRunner commandLineRunner(ApplicationContext ac) {
		return args -> {
      
			try {				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {							
							telaDeLogin.setVisible(true);							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}catch (Exception e) {				
				System.out.println(e.getMessage());
			}
			
		};
	}	

}
