package edu.ucla.cs249.littlefuzzyegg.io;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.ucla.cs249.littlefuzzyegg.data.Order;

public class OrderReader {
	private static final String [] FILE_HEADER_MAPPING_TRAIN = {"user","sku","cat","query","qtime","ctime"};
	private static final String [] FILE_HEADER_MAPPING_TEST = {"user","cat","query","qtime","ctime"};
	
	public static List<Order> ReadTrainOrders(String filename) {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING_TRAIN);
		
		List orders = new ArrayList<Order>();
		try {
			fileReader = new FileReader(filename);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List csvRecords = csvFileParser.getRecords(); 
			for (int i = 0; i < csvRecords.size(); i++) {
                CSVRecord record = (CSVRecord) csvRecords.get(i); 
                
                long entered = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(record.get("qtime").substring(0, 19)).getTime();
                orders.add(new Order(record.get("sku"), record.get("user"), entered, record.get("query")));
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }

		}
		return orders;
	}
	
	public static List<Order> ReadTestOrders(String filename) {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING_TEST);
		
		List orders = new ArrayList<Order>();
		try {
			fileReader = new FileReader(filename);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List csvRecords = csvFileParser.getRecords(); 
			for (int i = 0; i < csvRecords.size(); i++) {
                CSVRecord record = (CSVRecord) csvRecords.get(i); 
                
                long entered = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(record.get("qtime").substring(0, 19)).getTime();
                orders.add(new Order("", record.get("user"), entered, record.get("query")));
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }

		}
		return orders;
	}
	
	public static void main(String[] argv) {
		System.out.println(ReadTrainOrders("mytrain.csv"));
		System.out.println(ReadTestOrders("mytest.csv"));
	}
}
