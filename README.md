# BookApp - Backend Skills test

* Spring Boot, Maven, PostgreSQL

## Assumptions

* A book has one category.

* A new category can be created when cereating a book.

* Book title isn't unique.
* The success of Purchase Transaction API has no association  with the number of books available in the database.

* TDD approach isnt required. 
#### Given more time
* I would have used TDD approach, improve the response from the transaction endpoint. I couldn't finish integrating the transaction API due to time limitations from my end.   

### Dependencies

* Project Lombok
* Swagger

### Running the Application
* Clone the repository
* Create a PostgreSQL DB use credentials in the application.properties file 
* Build and run the application
### End points
* Book
	* POST api/books - create book
	* GET api/books - get all books
	* GET api/books/{category} - get books by category.
* Category
	* POST api/categories - create category
	* GET	 api/categories - get all categories
* Transaction
	* POST api/transactions - purchase a book
