# Computer Science II
# FMT Project
# Assignment 4.0 - Project Phase III
# Database Design
# Queries
#
# Group 11: 
# Cong T Nguyen - cnguyen46@huskers.unl.edu
# Yashraj Purbey - ypurbey2@huskers.unl.edu

# 1. A query to retrieve the main attributes of each person (their code, and last/first name)
SELECT personCode, firstName, lastName FROM Person;

# 2. A query to retrieve the major fields for every person including their address (but excluding emails)
SELECT p.personCode, p.firstName, p.lastName,
	   a.street, a.city, a.zipCode,
       c.countryName, s.stateName FROM Person p 
	   JOIN Address a ON a.addressId = p.addressId
       JOIN Country c ON c.countryId = a.countryId
       JOIN State s ON s.stateId = a.stateId;
       
# 3. A query to get the email addresses of a specific person
# In this test case, we choose "Terrance Rayman".
SELECT p.firstName, p.lastName, e.userName FROM Email e
	   JOIN Person p ON p.personId = e.personId
       WHERE p.personId = 1;

# 4. A query to change the email address of a specific email record
# In this test case, we choose "Terrance Rayman", and change "trayman0@sitemeter.com" to "trayman0@gmail.com"
UPDATE Email SET userName = 'trayman0@gmail.com' WHERE emailId = 1;

# 5. A query (or series of queries) to remove a specific person record
# In this test case, we choose "Anotony Wyatt", who is the manager of the store, the sales person, and share house with other person.
UPDATE Invoice SET salesPersonId = null WHERE salesPersonId = 7;
UPDATE Store SET managerId = null WHERE managerId = 7;
DELETE FROM Email WHERE personId = 7;
DELETE FROM Person WHERE personId = 7;

# 6. A query to get all the items on a specific invoice record
# In this test case, we choose the first invoice
SELECT a.invoiceCode, c.itemCode, c.nameItem FROM Invoice a
	   LEFT JOIN InvoiceItem b ON b.invoiceId = a.invoiceId
       LEFT JOIN Item c ON c.itemId = b.itemId
       WHERE a.invoiceId = 1;
       
# 7. A query to get all the items purchased by a specific person
# In this test case, we choose "Elsbeth Reveley"
SELECT p.firstName, p.lastName, i.invoiceCode, b.itemCode, b.nameItem  FROM Person p 
	   LEFT JOIN Invoice i ON i.customerId = p.personId
       LEFT JOIN InvoiceItem a ON a.invoiceID = i.invoiceId
       LEFT JOIN Item b ON b.itemId = a.itemId
       WHERE i.customerId = 9;
       
# 8. A query to find the total number of sales made at each store
SELECT s.storeCode, COUNT(i.invoiceId) AS numberOfSales FROM Store s
       LEFT JOIN Invoice i ON i.storeId = s.storeId
       GROUP BY s.storeId;
       
# 9. A query to find the total number of sales made by each employee
SELECT p.personCode, p.firstName, p.lastName, 
	   COUNT(i.invoiceId) AS numberOfSales FROM Person p
       JOIN Invoice i ON  p.personId = i.salesPersonId
       GROUP BY i.salesPersonId;
              
# 10. A query to find the subtotal charge of all products in each invoice
SELECT a.invoiceCode, SUM(b.quantity * c.unitPrice) AS subtotalOfProduct FROM Invoice a
	   LEFT JOIN InvoiceItem b ON b.invoiceId = a.invoiceId
       LEFT JOIN Item c ON c.itemId = b.itemId
	   AND c.typeItem = "P"
       GROUP BY a.invoiceId;
     
# 11. A query to detect invalid data in invoice as follows
# In a single invoice, a particular product should only appear once 
# since any number of units can be consolidated to a single record.
# Write a query to find any invoice that includes multiple instances of the same product.
SELECT a.invoiceCode, c.itemCode, c.nameItem FROM Invoice a
	   JOIN InvoiceItem b ON b.invoiceId = a.invoiceId
       JOIN Item c On c.itemId = b.itemId
       WHERE c.typeItem = "P"
       GROUP BY b.invoiceId, b.itemId
       HAVING COUNT(b.itemId) > 1;
       
# 12. Write a query to detect a potential instance of fraud where an employee makes a sale to themselves
SELECT b.invoiceCode, c.personCode, c.firstName, c.lastName FROM InvoiceItem a
	   JOIN Invoice b ON b.invoiceId = a.invoiceId
       JOIN Person c ON c.personId = b.customerId
       WHERE b.customerId = b.salesPersonId
       GROUP BY a.invoiceId;


       