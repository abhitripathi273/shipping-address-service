# shipping-address-service
Spring Boot Shipping Address Demo Application for E-commerce Application.

User can able to get shipping address details like;
  1- Get all the shipping addresses of a user
  2- Get details of specific shipping address for a user
  3- Create a shipping address for a user
  4- Update the details of specific shipping address for a user
  5- Delete the specific shipping address for a user

 API Endpoints:
 
 1 - GET /user/{userId}/shippingAddressList
 2 - GET /user/{userId}/shippingAddress/{addressId}
 3 - POST /user/{userId}/shippingAddress
   Request Payload:
   	{
    "pinCode": 12,
    "area": "sector 29",
    "district": "Gurgaon",
    "state": "Haryana",
    "country": "India",
    "defaultAddress": false
	}
4 - PUT /user/{userId}/updateShippingAddress
	Request Payload:
	{
        "userId": 38719,
        "addressId": 71908,
        "pinCode": 12,
        "area": "sector 29",
        "district": "Gurgaon",
        "state": "Haryana",
        "country": "India",
        "defaultAddress": false
    }
5 - DELETE /user/{userId}/shippingAddress/{addressId}