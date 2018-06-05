[keyword][]priority=salience
[*][]Load product=$product: EqiProduct();
[*][]Product is {productType}= eval($product.getProductType().equalsIgnoreCase({productType}));
[consequence][]Add new deal {deal} =result.applyConsequence({deal}+getDate());