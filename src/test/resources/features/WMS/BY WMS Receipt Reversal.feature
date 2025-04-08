
Feature: BY WMS Receipt Reversal 
	This Feature is to enable various scenarios
  from which a user can chose to perform tests
  specific for Receipt reversal .

#Consolitated Receipt Reversal Case test cases
 #Parameters
    #Receipt type -Blind/delivery/Pre advice/UPI
    #Receipt ID -Blind/Pre advice -receipt id ,Delivery - ASN id,UPI - pallet id
    #line id - Particular lind id for performing Receipt reversal
    #Site id - 5665
    #sku id - sku id to perform receipt reversal
    #tag id - Corresponding Tag id
    #qty to reverse -Number of quantity

 @Receipt_reversal_Swindon
    Scenario: Perfrom Receipt Reversal in Swindon
    Given Login to the JDA screen
    Then I navigate to "Receipt reversal"
    And I enter the Receipt type Receipt id Line id site id Sku id Tag id and qty to reverse to perform Receipt reversal "Blind","","","5665","000000000022135302","45326387","1" 

 #Parameters
   #Site id - 5665
    #Receipt type -Blind/delivery/Pre advice/UPI
    #Receipt ID -Blind/Pre advice -receipt id ,Delivery - ASN id,UPI - pallet id
    #line id - Particular lind id for performing Receipt reversal
    #sku id - sku id to perform receipt reversal
    #tag id - Corresponding Tag id
    #qty to reverse -Number of quantity

 @Receipt_reversal_Welham
   	Scenario: erfrom Receipt Reversal in Welham
	  Given Login to the JDA screen  
	 	 Then Go to "Receipt Reversal"
			And I Reverse the receipt for the SiteID "7401" Receipt type "Blind" ReceiptID "" LineID "" SkuID "000000000022233100" TagID "100029469" QTY "1"	 
		 
 
 #Parameters
    #Site id - 5885/5649
    #Receipt type -Blind/delivery/Pre advice/UPI
    #line id - Particular lind id for performing Receipt reversal
    #sku id - sku id to perform receipt reversal
    #tag id - Corresponding Tag id
    #Receipt ID -Blind/Pre advice -receipt id ,Delivery - ASN id,UPI - pallet id
    #qty to reverse -Number of quantity
#Enter Y for full receipt reversal and enter the quantity for partial receipt reversal
@PerformReceiptReversal_Stoke_WestThurrock
Scenario: Perform receipt reversal in Stoke and West Thurrock
Given Login to the JDA screen
Then Go to "Receipt Reversal"
Then I perform receipt reversal for site "" for type "Blind" reversal with line id "" sku id "" tag id "2963" receipt id "" for quantity "Y"      