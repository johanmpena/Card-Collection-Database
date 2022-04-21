package com.CardCollectionDB.Utilities;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Uploader {

	public static void main(String[] args) {
		/* SET RESOURCE ADDRESS TO VARAIBLE. */
		final File folder = new File("src/main/resources/static/images/card-images");
							
		
		/* ASSIGN DATABASE INFORMATION TO FIELDS */
		String jdbcUrl = "jdbc:mysql://localhost:3306/cardcollectiondatabase?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String pass = "Superlock3";
		
		try {
			/* CONNECT TO DATABASE. */
			System.out.println("Connecting to database: " + jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection successfull!!!");
			Statement st = myConn.createStatement();
			
			/* CREATE ARRAY TO STORE VALUES. */
			String[] array = new String[194];
			int i = 0;
			int j = 0;
			
			for (final File fileEntry: folder.listFiles()) {
				/* GET FILE NAME THEN ADD IT TO ARRAY INDEX. */
				System.out.println(fileEntry.getName());
				array[i] = fileEntry.getName();
				i++;
			}
			
			/* BREAK UP STRING. */
			String[][] cardInfo = new String[194][4];
			
			/* CREATE VARAIBLE TO SPILT INFORMATION. */
			String[] splitString;
   			
   			/* CREATE FOR EACH LOOP TO TRAVRSE STRING. */
   			for (String string: array) {
   				/* BREAK STRING */
   				splitString = string.split("[_|.]");
   				
   				for(i = 0; i <= 3; i++) {
   					cardInfo[j][i] = splitString[i];
   				}
   				
   				j++;
   			}
   			
   			/* PRINT OUT CARD INFORMATION. */
   			for (j = 0; j <= 193; j++) {
   				
   				for (i = 0; i <= 3; i++) {
   					System.out.print(cardInfo[j][i] + " ");
   				}
   				System.out.println('\n');
   			}
   			
   			/* STORE CARDS IN DATABASE. */
   			for (j = 0; j < cardInfo.length; j++) {
   				
//   				name = "INSERT INTO cards ('cname',"+cardInfo[j][2]+")";
//   	   			color = "INSERT INTO cards ('color',"+cardInfo[j][1]+")";
//   	   			rank = "INSERT INTO cards ('rank',"+cardInfo[j][3]+")";
//   	   			number = "INSERT INTO cards ('cnumber',"+cardInfo[j][0]+")";
//   				st.addBatch(name);
//   				st.addBatch(color);
//   				st.addBatch(rank);
//   				st.addBatch(number);
//   				st.executeBatch();
   				st.addBatch("INSERT INTO cards(cardnumber,color,cardname,cardrank,cardset) VALUES('"+cardInfo[j][0]+"','"
						 +cardInfo[j][1]+"','"+cardInfo[j][2]+"','"+cardInfo[j][3]+"','"+"Kanugawa"+"')");
   				st.executeBatch();
   			}
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
