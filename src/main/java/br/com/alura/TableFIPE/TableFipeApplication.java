package br.com.alura.TableFIPE;

import br.com.alura.TableFIPE.main.MainTableFIPE;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TableFipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TableFipeApplication.class, args);
	}

	public void run(String... args) throws Exception {
		MainTableFIPE mainTableFIPE = new MainTableFIPE();
		mainTableFIPE.ShowMenu();
	}
}
