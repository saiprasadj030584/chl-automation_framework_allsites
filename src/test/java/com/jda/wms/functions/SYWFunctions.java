package com.jda.wms.functions;
import org.sikuli.script.Button;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.StoreTrackingOrderPickingPage;
import com.jda.wms.config.Constants;
import com.jda.wms.config.SiteConstants;
import com.jda.wms.context.Context;
import com.jda.wms.db.OrderLineDB;
import com.jda.wms.db.PreAdviceHeaderDB;
import com.jda.wms.db.SkuDB;
import com.jda.wms.db.InventoryTransactionDB;
import com.jda.wms.db.UPIReceiptHeaderDB;
import com.jda.wms.handlers.DataHandler;
import com.jda.wms.handlers.RDTHandler;
import com.jda.wms.handlers.ValidationHandler;

import cucumber.api.java.en.Given;
import junit.framework.Assert;



public class SYWFunctions {


		// TODO Auto-generated method stub
	
		 private RDTHandler rdthandler;
		private static Context context;
		private SkuDB skudb;
		private Constants constants;
		private InventoryTransactionDB InventorytransactionDB;
		
		Screen screen = new Screen();
		
		@Inject
		public SYWFunctions(RDTHandler rdthandler, Context context,SkuDB skudb,Constants constants,InventoryTransactionDB InventorytransactionDB){
			
			this.rdthandler = rdthandler;
			this.context = context;
			this.skudb = skudb;
			this.constants=constants;
			this.InventorytransactionDB=InventorytransactionDB;
		}
		
		public void validaterecord(String upc) throws Throwable{
			
			System.out.println("Validating record ....");

			try {
				if (skudb.validateSYW(upc)) {
					System.out.println("Validated Successfully!");
				} 
			}
			catch (AssertionError e){
				System.out.println("Entered UPC is Not available in MANDS.REPO_STROKE_PARENT_DC");
				
			}
			
		}
		
	
		@Given("^I perform SYW functionality for the UPC,perf indicator and store id as \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
public void i_perform_SYW_functionality_for_the_UPC_perf_indicator_and_store_id(String UPC, String perf, String store)
		
		throws Throwable {
		    
			validaterecord(UPC);
			String Tdept=skudb.getProductgroup(UPC);
			
			rdthandler.changeKeyboardType();
			Thread.sleep(2000);
			
			rdthandler.typeKey("F3");
			Thread.sleep(3000);
			if(rdthandler.getScreenName().equalsIgnoreCase("QueryMenu"))
			{
				Thread.sleep(1000);
				rdthandler.typeStringAndEnter("9");
				Thread.sleep(1000);
			}
			
			
			
			if(rdthandler.getScreenName().equalsIgnoreCase("QueryOrderStatusEntry"))
			{
			
			rdthandler.typeStringAndTab(UPC);
			Thread.sleep(1000);
			
			rdthandler.typeString(perf);
			Thread.sleep(1000);
			
			rdthandler.typeStringAndEnter(store);
			Thread.sleep(3000);
			
			}
			rdthandler.typeKey("ENTER");
			
			Thread.sleep(3000);
			
			if(constants.Brandswholesale_Tdept.contains(Tdept))
			{
				Thread.sleep(1000);
				System.out.println("Destination check for Brands Wholesale Products");
				 SYWforBrandswholesale(UPC,perf);
			
			}
			else if(constants.Brandsconsignment_Tdept.contains(Tdept))
			{
				Thread.sleep(1000);
				System.out.println("Destination check for Brands Consignment Products");
				 SYWforBrandsconsignment(UPC,perf);
			}
			else if(constants.NonBrands_Tdept.contains(Tdept))
			{
				Thread.sleep(1000);
				System.out.println("Destination check for Non brands Products");
				 SYWforNonBrands(UPC,perf);
			}
			
			
			
			Thread.sleep(3000);
			System.out.println("ITL Validation after SYW check");
			
			
			if(InventorytransactionDB.validateafterSYW(UPC))
			{
				System.out.println("ITL Created successfully!!");
			}
					
				
			
	}
		
		
		// Performing SYW for Brands Wholesale
		
		
		public void SYWforBrandswholesale(String UPC, String perf) throws Throwable{
			
		Thread.sleep(3000);
		
		if(skudb.getuser_def_chk_4(UPC).equals("N") && skudb.getuser_def_type_7(UPC).equals("06") && perf.equals("Y") )
		{
			System.out.println("Destination is OUTLET ");
		}
		else if(skudb.getuser_def_chk_4(UPC).equals("N") && skudb.getuser_def_type_7(UPC).equals("06") && perf.equals("N"))
		{
			System.out.println("Destination is OUTLET");
		}
		else if(skudb.getuser_def_chk_4(UPC).equals("N") && skudb.getuser_def_type_7(UPC).equals("05") &&  perf.equals("N"))
		{
			System.out.println("Destination is Charity");
		
		}
		else if(skudb.getuser_def_chk_4(UPC).equals("N") && skudb.getuser_def_type_7(UPC).equals("05") &&  perf.equals("Y"))
		{
			System.out.println("Destination is Thorncliffe");
		}
		
		else if(skudb.getuser_def_chk_4(UPC).equals("Y") && skudb.getuser_def_type_7(UPC).equals("06") &&  perf.equals("Y"))
		{
			System.out.println("Destination is OUTLET");
		}
		else if(skudb.getuser_def_chk_4(UPC).equals("Y") && skudb.getuser_def_type_7(UPC).equals("06") &&  perf.equals("N"))
		{
			System.out.println("Destination is OUTLET");
		}
		
		else if (skudb.getuser_def_chk_4(UPC).equals("Y") && skudb.getuser_def_type_7(UPC).equals("05") &&  perf.equals("N"))
		{
			System.out.println("Destination is Charity");
		}
		else if(skudb.getuser_def_chk_4(UPC).equals("Y") && skudb.getuser_def_type_7(UPC).equals("05") && perf.equals("Y"))
		{
			System.out.println("Destination is Charity");
		}
		else
		{
			System.out.println("Destination is Unknown");
		}
		
	}

		
		
		//performing SYW for Non brands
		
		
	public void  SYWforNonBrands(String UPC, String perf) throws Throwable{
		
		
			Thread.sleep(3000);
			
			if(skudb.getuser_def_chk_4(UPC).equals("N") && skudb.getuser_def_type_7(UPC).equals("06") && perf.equals("Y") )
			{
				System.out.println("Destination is OUTLET ");
			}
			else if(skudb.getuser_def_chk_4(UPC).equals("N") && skudb.getuser_def_type_7(UPC).equals("06") && perf.equals("N"))
			{
				System.out.println("Destination is Charity");
			}
			else if(skudb.getuser_def_chk_4(UPC).equals("N") && skudb.getuser_def_type_7(UPC).equals("05") && perf.equals("N"))
			{
				System.out.println("Destination is Charity");
			
			}
			else if(skudb.getuser_def_chk_4(UPC).equals("N") && skudb.getuser_def_type_7(UPC).equals("05") &&  perf.equals("Y"))
			{
				System.out.println("Destination is Parent DC");
			}
			
			else if(skudb.getuser_def_chk_4(UPC).equals("Y") && skudb.getuser_def_type_7(UPC).equals("06") && perf.equals("Y"))
			{
				System.out.println("Destination is OUTLET");
			}
			else if(skudb.getuser_def_chk_4(UPC).equals("Y") && skudb.getuser_def_type_7(UPC).equals("06") &&  perf.equals("N"))
			{
				System.out.println("Destination is Charity");
			}
			
			else if (skudb.getuser_def_chk_4(UPC).equals("Y") && skudb.getuser_def_type_7(UPC).equals("05") &&  perf.equals("N"))
			{
				System.out.println("Destination is Charity");
			}
			else if(skudb.getuser_def_chk_4(UPC).equals("Y") && skudb.getuser_def_type_7(UPC).equals("05") && perf.equals("Y"))
			{
				System.out.println("Destination is Charity");
			}
			else
			{
				System.out.println("Destination is Unknown");
			}		
							
			
		}
		
		//Performing SYW for Brands consignment
		
		
   public void  SYWforBrandsconsignment (String UPC, String perf) throws Throwable{
	
	   		Thread.sleep(3000);
			
	        if((skudb.getuser_def_chk_4(UPC).equals("N") || skudb.getuser_def_chk_4(UPC).equals("Y")) && (skudb.getuser_def_type_7(UPC).equals("06") || skudb.getuser_def_type_7(UPC).equals("05")) && (perf.equals("Y") || perf.equals("N")) )
			{
				System.out.println("Destination is Thorncliffe ");
			}
	        else
			{
				System.out.println("Destination is Unknown");
			}			
				
			
		}


	
}


	


