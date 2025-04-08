@DePalletisation
Feature: As an user to verify the depalletisation

@Full_Depalletisation @BoxedGoods
  Scenario: To depalletise a full pallet
    Given A full depal SKU with the stocks at HBW location
    
    
 @Partial_Depalletisation @BoxedGoods
  Scenario: To partially depalletise a pallet
    Given A partial depal SKU with the stocks at HBW location
    
    
  @Add_Depalletisation_Manually @BoxedGoods
  Scenario: To partially depalletise a pallet by manually adding depalletising order
    Given A add depal SKU with the stocks at HBW location
    
    
   @Delete_Palletisation @BoxedGoods
  Scenario: To delete depalletising order by manually adding depalletising order
    Given A delete depal SKU with the stocks at HBW location
    
     @Update_Depal_Demand @BoxedGoods
  Scenario: To update demand in depalletising order 
    Given A update demand depal SKU with the stocks at HBW location
    
    
        @Update_Depal_Priority @BoxedGoods
  Scenario: To update Priority in depalletising order 
    Given A update priority depal SKU with the stocks at HBW location
    
    
    