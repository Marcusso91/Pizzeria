# Pizzeria Application Using Java
![Pizzeria](https://user-images.githubusercontent.com/111382157/187478411-94fa6754-e892-4cb2-85f0-77c3b4a00a2b.png)



In this program, I present the short operation of a pizzeria in an application that is connected to a mysql database via xampp.

You can order a pizza in it, you have to enter the name and size of the pizza and you can add extra toppings that the original pizza does not contain
then add it to the database

In addition, you can add a type of pizza, where you have to enter the name of the pizza, its size and what kind of toppings it should be on, then add this as well
to the database.

<span style="color:blue">**Users Have Following Access for this Pizzeria site:-**</span>
- Create New Pizza.
- Add An Order.
- Query Of All The Toppings For The Given Order

### Technologies used:-
1. Beginner Back-End Development
- Java [JDK 8+]
- JDBC
- MySQL
- Apache Maven

2. Database used.
- MySql

### ==== Software And Tools Required ====
- MySQL
- NetBeans IDE
- Java [JDK 8+]
- Tomcat v8.0+
- Apache Maven

### Structure of classes:- ###
1. DataBaseConnection.class
  - From where I create the connection between java and mysql.
  
2. Feldat.class
  - I instantiate the program in the main function.
  
3. MyException.class
  - I create my own exception class for special exceptions

4. Pizzeria.class
  - I create the main jframe for application where I download the orders from the database and where I can call the other functions

5. addPizza.class
  - I make a new jframe that I invite from the pizzeria.class and with i can add pizza to the database
  
6. addRendeles.class
  - I make a new jframe that I invite from the pizzeria.class and with i can add new order to the database
  
7. getFeltetek.class
  - I make a new jframe that I invite from the pizzeria.class and with I can request all the ingredients from the given
  
  

Note:- This is a Sample Project, there is an .sql file in the SQL_file folder
