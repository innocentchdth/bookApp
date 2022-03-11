# BookApp

* Spring Boot, Maven, PostgreSQL

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
