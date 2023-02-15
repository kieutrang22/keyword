package com.database

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import java.sql.DriverManager

import java.sql.ResultSet

import java.sql.Statement

import com.kms.katalon.core.annotation.Keyword

import java.sql.Connection
public class PostgreSQLJDBC {

	private static final String DB_URL="jdbc:postgresql://192.168.2.100/realestate"
	private static final String DB_USERNAME="postgres"
	private static final String DB_PASSWORD="Evotek@1234"
	private static Connection connection = null;
	@Keyword (keywordObject = 'connect DB')
	public static Connection getConnectDB(){
		try {
			if(connection != null && !connection.isClosed()){
				connection.close()
			}
			connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD)
			System.out.println("connect successfully!");
		} catch (Exception e) {
			System.out.println("connect failure!");
			e.printStackTrace()
		}
		return connection
	}


	@Keyword (keywordObject = 'query')
	public static ResultSet executeQuery(String queryString) {
		Statement stm = getConnectDB().createStatement()
		ResultSet rs = stm.executeQuery(queryString)
		return rs
	}


	@Keyword (keywordObject = 'close connect DB')
	public static void closeDatabaseConnection() {
		if(connection != null && !connection.isClosed()){
			connection.close()
		}
		connection = null
	}

	/**
	 * Execute non-query (usually INSERT/UPDATE/DELETE/COUNT/SUM...) on database
	 * @param queryString a SQL statement
	 * @return single value result of SQL statement
	 */

	@Keyword (keywordObject = 'non-query')
	public static ResultSet execute(String queryString) {
		Statement stm = connection.createStatement()
		boolean result = stm.execute(queryString)
		return result
	}
}
