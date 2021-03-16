package main;

import main.controllers.GoodsInStoresController;
import main.enums.Commands;
import main.services.UserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

	private UserInputService inputService;
	private GoodsInStoresController goodsInStoresController;

	@Autowired
	public void setInputService(UserInputService inputService) {
		this.inputService = inputService;
	}

	@Autowired
	public void setGoodsInStoresController(GoodsInStoresController goodsInStoresController) {
		this.goodsInStoresController = goodsInStoresController;
	}

	public static void main(String[] args)	{
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... strings) {

		pointStartOfCycle:
        while (true)    {
			try	{
			    Thread.sleep(100);
				System.out.print("Введите команду: ");
				String line = inputService.getLine();
				List<String> splittedLine = new ArrayList<>(Arrays.asList(line.split("\\s")));
				Commands currentCommand = Commands.valueOf(splittedLine.get(0));
				splittedLine.remove(0);
				switch (currentCommand)	{
					case ДОБАВИТЬ_ТОВАР -> goodsInStoresController.setProduct(splittedLine);
					case ДОБАВИТЬ_МАГАЗИН -> goodsInStoresController.setStore(splittedLine);
					case ВЫСТАВИТЬ_ТОВАР -> goodsInStoresController.putProductToStore(splittedLine);
					case СТАТИСТИКА_ТОВАРОВ -> goodsInStoresController.showProductStatistics();
					case ВЫХОД -> {
						break pointStartOfCycle;
					}
				}
			} catch (Exception ex)	{
				ex.printStackTrace();
			}
        }
		System.out.print("Программа успешно завершила работу...");

	}

}