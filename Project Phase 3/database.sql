use cnguyen;  

drop table if exists Email;
drop table if exists InvoiceItem;
drop table if exists Invoice;
drop table if exists Store;
drop table if exists Person;
drop table if exists Item;
drop table if exists Address;
drop table if exists State;
drop table if exists Country;

# This table is used for variable state in class Address
create table if not exists State (
	stateId int primary key not null auto_increment,
    stateName varchar(255) not null unique
);

# This table is used for variable country in class Address
create table if not exists Country (
	countryId int primary key not null auto_increment,
    countryName varchar(255) not null unique
);

# This table is used for class Address
create table if not exists Address (
	addressId int primary key not null auto_increment,
    street varchar(255) not null,
    city varchar(255) not null,
    zipCode varchar (255) not null,
    stateId int not null,
    countryId int not null,
    foreign key (stateId) references State(stateId),
    foreign key (countryId) references Country(countryId),
    constraint `uniqueGroup` unique index(street, city, stateId, countryId)
);

# This table is used for class Person
create table if not exists Person (
	personId int primary key not null auto_increment,
    personCode varchar(255) not null unique,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    addressId int not null,
    foreign key (addressId) references Address(addressId)
);

# This table is used for variable email in class Email
create table if not exists Email (
	emailId int primary key not null auto_increment,
    personId int not null,
    userName varchar(255) not null,
    foreign key (personId) references Person(personId),
    constraint `uniquePairEmail` unique index(personId,userName)
);

# This table is used for class Item, and its subclasses, which are Equipment, Product, and Service
create table if not exists Item (
	itemId int primary key not null auto_increment,
    itemCode varchar(255) not null unique,
    nameItem varchar(255) not null,
    typeItem varchar(1) not null,
    model varchar(255),
    unit varchar(255),
    unitPrice float,
    hourlyRate float,
    check (typeItem in ("E","P","S"))
);

# This table is used for class Store
create table if not exists Store (
	storeId int primary key not null auto_increment,
    storeCode varchar(255) not null unique,
    managerId int not null,
    addressId int not null,
    foreign key (managerId) references Person(personId),
	foreign key (addressId) references Address(addressId)
);

# This table is used for class Invoice
create table if not exists Invoice (
	invoiceId int primary key not null auto_increment,
    invoiceCode varchar(255) not null unique,
    storeId int not null,
    customerId int not null,
    salesPersonId int not null,
    invoiceDate varchar(255) not null,
    foreign key (storeId) references Store(storeId),
    foreign key (customerId) references Person(personId),
    foreign key (salesPersonId) references Person(personId)
);

# This table is used as the intermediate table between table Invoice, and table Item
create table if not exists InvoiceItem (
	invoiceItemId int primary key not null auto_increment,
    invoiceId int not null,
    itemId int not null,
    typeEquipment varchar(1),
    price float,
    beginLease varchar(255),
    endLease varchar(255),
    quantity float,
    amountOfHour float,
    foreign key (invoiceId) references Invoice(invoiceId),
	foreign key (itemId) references Item(itemId),
    check (typeEquipment in ("P","L",null))
);

-- Insert data to State table
INSERT INTO State (`stateName`) VALUES
	('MN'),
	('MD'),
	('MO'),
	('DC'),
	('WA'),
	('CA'),
	('UT'),
	('NC'),
	('DE'),
	('PA'),
	('TX'),
	('SD'),
    ('JJ');

-- Insert data to Country table
INSERT INTO Country (`countryName`) VALUES 
	('US'),
    ('UK');
    
-- Insert data to Address table
# Person's address
INSERT INTO Address (street, city, zipCode, countryId, stateId) VALUES
	('2 Hauk Alley', 'Monticello', '55565', 1, 1),
	('3896 Shelley Terrace', 'Gaithersburg', '20883', 1, 2),
	('56059 Hollow Ridge Court', 'Lees Summit', '64082', 1, 3),
	('2 Canary Lane', 'Washington', '20268', 1, 4),
	('19211 Maywood Circle', 'Vancouver', '98687', 1, 5),
	('501 Kennedy Pass', 'San Jose', '95194', 1, 6),
    ('9 Upper Gray St', 'Edinburgh', 'EH9 1SN', 2, 13),
	('80 Shasta Alley', 'Salt Lake City', '84189', 1, 7),
	('2 Buell Trail', 'Irvine', '92710', 1, 6),
	('651 Florence Junction', 'Greensboro', '27404', 1, 8),
	('41 Bluestem Terrace', 'Washington', '20057', 1, 4),
	('2306 Stang Court', 'Newark', '19714', 1, 9),
	('2 Loftsgordon Drive', 'Erie', '16550', 1, 10);
    
# Store's address
INSERT INTO Address (addressId, street, city, zipCode, countryId, stateId) VALUES
	(501, '265 Tony Place', 'Philadelphia', '19136', 1, 10),
	(502, '60181 Cascade Road', 'Dallas', '75080', 1, 11),
	(503, '263 Tony Junction', 'Scranton', '18503', 1, 10),
	(504, '206 Main Court', 'Pittsburgh', '15106', 1, 10),
	(505, '75579 Morningstar Hill', 'Houston', '77090', 1, 11),
	(506, '538 Summit Parkway', 'Sioux Falls', '57198', 1, 12),
    (507, '42 Craigmount Terrace', 'Edinburgh', 'EH12 8BW', 2, 13),
    (508, '154 Home Lake Avery', 'Scranton', '18503', 1, 10);

-- Insert data to Person table
INSERT INTO Person (personCode, firstName, lastName, addressId) VALUES
	('SAL001', 'Terrance', 'Rayman', 1),
	('SAL002', 'Candie', 'Crannage', 2),
	('SAL003', 'Randal', 'Briddle', 3),
	('SAL004', 'Corbin', 'Brine', 4),
	('SAL005', 'Reece', 'Haggerwood', 5),
	('SAL006', 'Amy', 'Jenton', 6),
    ('SAL007', 'Antony', 'Wyatt', 7),
	('SAL008', 'Helen', 'Miller',7),
	('PUR001', 'Elsbeth', 'Reveley', 8),
	('PUR002', 'Faber', 'Race', 9),
	('PUR003', 'Berk', 'Meanwell', 10),
	('PUR004', 'Prentiss', 'Dungey', 11),
	('PUR005', 'Hermia', 'Bunford', 12),
	('PUR006', 'Nollie', 'Blesdill', 13);
    
-- Insert data to Email table
# Terrance Rayman
INSERT INTO Email (personId, userName) VALUES
	(1, 'trayman0@sitemeter.com'),
	(1, 'squarry0@salon.com');

# Candie Crannage
INSERT INTO Email (personId, userName) VALUES
	(2, 'ccrannage1@digg.com'),
	(2, 'minamiCrimson@gmail.com'),
    (2, 'crimsonQueen@yahoo.com.vn');

# Randal Brinddle
INSERT INTO Email (personId, userName) VALUES
	(3, 'rbriddle2@networkadvertising.org'),
	(3, 'thisisme117@yahoo.com');
    
# Corbin Brine
INSERT INTO Email (personId, userName) VALUES
	(4, 'cbrine3@webnode.com'),
	(4, 'forVahala1992@crystal.com');

# Reece Haggerwood
INSERT INTO Email (personId, userName) VALUES	
    (5, 'rhaggerwood4@histats.com');

# Amy Jenton
INSERT INTO Email (personId, userName) VALUES
    (6, 'ajenton5@eepurl.com'),
	(6, 'potteralex457@yahoo.com'),
	(6, 'potteralex457@red.com'),
	(6, 'potteralex457@witcher.com');

# Antony Wyatt (This person is used to test for deleting person's info)
INSERT INTO Email (personId, userName) VALUES
	(7, 'antonyW@yahoo.com');

# Elsbeth Reveley
INSERT INTO Email (personId, userName) VALUES
	(9, 'ereveley6@upenn.edu'),
	(9, 'gerad.Harad1009@this.com');
 
# Faber Race
INSERT INTO Email (personId, userName) VALUES
	(10, 'frace7@ning.com');
    
# Berk Meanwell
INSERT INTO Email (personId, userName) VALUES
	(11, 'bmeanwell8@parallels.com'),
	(11, 'sjlkThesia@hmail.com');
 
# Prentiss Dungey
INSERT INTO Email (personId, userName) VALUES
	(12, 'nowhere134@gmail.com'),
	(12, 'pdungey9@hubpages.com'),
	(12, 'universal76forthis09@haha.com');
    
# Hermia Bunford
INSERT INTO Email (personId, userName) VALUES
	(13, 'hbunforda@businesswire.com'),
	(13, 'thisisit918@yahoo.com');
 
# Nollie Blesdil
INSERT INTO Email (personId, userName) VALUES
	(14, 'nblesdillb@harvard.edu'),
	(14, 'iamNotaBot9018@facebook.com');
    
-- Insert data to Store table
INSERT INTO Store (storeCode, managerId, addressId) VALUES
	('TITSV1', 1, 501),
	('TITSV2', 2, 502),
	('TITSV3', 1, 503),
	('TITSV4', 1, 504),
	('TITSV5', 2, 505),
	('TITSV6', 3, 506),
    ('TITSV7', 7, 507),
    ('TITSV8', 5, 508);

-- Insert data to Item table
# Equipment
INSERT INTO Item (itemCode, typeItem, nameItem, model) VALUES
	('IE001A', 'E', 'Farm truck', 'BuzzKit 117'),
	('IE0139', 'E', 'Sprayer', 'Evolve Gen.II-109T'),
	('IE345F', 'E', 'Seed drill', 'RAZOR 666'),
	('IE117E', 'E', 'Cultipacker', 'Warrior X3'),
	('IE873W', 'E', 'Subsoiler', 'BlueSteel Type A1'),
	('IE549T', 'E', 'Land Imprinter', 'NightHound MAC V'),
	('IE96T3', 'E', 'Tractor', 'WTD 1999');

# Product
INSERT INTO Item (itemCode, typeItem, nameItem, unit, unitPrice) VALUES
	('IP094P', 'P', 'Potato seeds', 'bag', 25),
	('IP067C', 'P', 'Carrot seeds', 'bag', 25),
	('IP084C', 'P', 'Cabbage seeds', 'bag', 25),
	('IP012M', 'P', '2% Milk', 'bottle', 12),
	('IP028S', 'P', 'Great soil', 'ton', 20),
	('IP0F87', 'P', 'Grass Seeds', 'pound', 25);
    
# Service
INSERT INTO Item (itemCode, typeItem, nameItem, hourlyRate) VALUES
	('IS002T', 'S', 'Transportation', 100),
	('IS004M', 'S', 'Maintaining', 100),
	('IS008A', 'S', 'Auditing', 50),
	('IS017H', 'S', 'Harvesting', 10),
	('IS010C', 'S', 'Constructing', 100),
	('IS023T', 'S', 'Tracking', 50),
	('IS025T', 'S', 'Teaching assistant', 20);

-- Insert data to Invoice table
INSERT INTO Invoice (invoiceCode, storeId, customerId, salesPersonId, invoiceDate) VALUES
	('BIL001', 1, 9, 1, '2023-01-25'),
	('BIL002', 1, 14, 4, '2023-01-31'),
	('BIL003', 3, 11, 1, '2023-02-12'),
	('BIL004', 2, 10, 2, '2023-02-15'),
	('BIL005', 4, 13, 1, '2023-02-22'),
	('BIL006', 5, 9, 6, '2023-02-28'),
	('BIL007', 6, 8, 8, '2023-03-02'),
    ('BIL008', 4, 12, 5, '2023-03-07'),
    ('BIL009', 4, 9, 7, '2023-03-10');
      
-- Insert data to InvoiceItem table
# Invoice 1
INSERT INTO InvoiceItem (invoiceId, itemId, typeEquipment, price, beginLease, endLease, quantity, amountOfHour) VALUES
	(1, 1, 'P', 59000, null, null, null, null),
	(1, 4, 'L', 3000, '2023-02-01', '2024-01-31', null, null),
	(1, 13, null, null, null, null, 50, null),
	(1, 14, null, null, null, null, null, 2);

# Invoice 2
INSERT INTO InvoiceItem (invoiceId, itemId, typeEquipment, price, beginLease, endLease, quantity, amountOfHour) VALUES
	(2, 12, null, null, null, null, 10, null);
    
# Invoice 3
INSERT INTO InvoiceItem (invoiceId, itemId, typeEquipment, price, beginLease, endLease, quantity, amountOfHour) VALUES
	(3, 7, 'P', 68000, null, null, null, null),
	(3, 7, 'P', 62000, null, null, null, null),
    (3, 14, null, null, null, null, null, 5);

# Invoice 4
INSERT INTO InvoiceItem (invoiceId, itemId, typeEquipment, price, beginLease, endLease, quantity, amountOfHour) VALUES
	(4, 20, null, null, null, null, null, 40);

# Invoice 5
INSERT INTO InvoiceItem (invoiceId, itemId, typeEquipment, price, beginLease, endLease, quantity, amountOfHour) VALUES
	(5, 8, null, null, null, null, 10, null),
	(5, 9, null, null, null, null, 10, null),
	(5, 10, null, null, null, null, 10, null);

# Invoice 6
INSERT INTO InvoiceItem (invoiceId, itemId, typeEquipment, price, beginLease, endLease, quantity, amountOfHour) VALUES
	(6, 15, null, null, null, null, null, 8),
	(6, 16, null, null, null, null, null, 8);

# Invoice 7 (This invoice is use to test the Query 11 - duplicated products test, and Query 12 - fraud test)
INSERT INTO InvoiceItem (invoiceId, itemId, typeEquipment, price, beginLease, endLease, quantity, amountOfHour) VALUES
	(7, 6, 'P', 60000, null, null, null, null),
	(7, 13, null, null, null, null, 10, null),
	(7, 13, null, null, null, null, 25, null);

# Invoice 8 don't have the sold items, to test the number of sales

# Invoice 9 (This invoice is use to test the Query 4 - deleted information of a specific person)
INSERT INTO InvoiceItem (invoiceId, itemId, typeEquipment, price, beginLease, endLease, quantity, amountOfHour) VALUES
	(9, 3, 'L', 30000, '2023-04-01', '2024-03-31', null, null),
    (9, 11, null, null, null, null, 50, null);

