package api

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
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import internal.GlobalVariable

public class abstractApi {
	public static String token=null;
	public static ResponseObject response=null;

	@Keyword (keywordObject = 'getToken')
	def getToken(String username,String password) {
		try {
			ResponseObject response= WS.sendRequestAndVerify(findTestObject('Object Repository/Api/getToken',[('user') :username,('pass'):password]))
			token= WS.getElementPropertyValue(response,'id_token')
			return token
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
	
	@Keyword (keywordObject= 'callApi')
	def ResponseObject callApi(String objectId,Map<String,Object> maps) {
		try {
			response=WS.sendRequestAndVerify(findTestObject(objectId,maps))
			return response
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
	
	@Keyword (keywordObject= 'callApi')
	def ResponseObject callApi(String objectId) {
		try {
			response=WS.sendRequestAndVerify(findTestObject(objectId))
			return response
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
}
