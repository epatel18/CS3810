use books
db.createCollection("books")

db.books.insert({
	isbn : "978-0-143-13184-7",
	title : "Frankenstein",
	author : "Mary Shelley",
	date : {
		year : 2018,
		month : "January"
	},
	pages : 288
})

db.books.insert({
	isbn : "978-0-307-74486-9",
	title : "The Devil All the Time",
	author : "Donald Ray Pollock",
	date : {
		year : 2012,
		month : "July"
	},
	pages : 320
})

db.books.insert({
	isbn : "978-0-307-34155-6",
	title : "Sharp Objects",
	author : "Gillian Flynn",
	date : {
		year : 2007,
		month : "July"
	},
	pages : 254
})

db.books.insert({
	isbn : "978-0-312-33087-3",
	title : "And Then There Were None",
	author : "Agatha Christie",
	date : {
		year : 2004,
		month : "May"
	},
	pages : 264
})

db.books.insert({
	isbn : "978-1-501-13923-9",
	title : "The Seven Husbands of Evelyn Hugo",
	author : "Taylor Jenkins Reid",
	date : {
		year : 2017,
		month : "June"
	},
	pages : 391
})

// list all books
db.books.find()

// list all book titles
db.books.find({}, {title: true})

// list all books written by X, where X is an author that you know is listed in your collection
db.books.find({author: 'Gillian Flynn'})

// list all books published in Y, where Y is a year
db.books.find({"date.year": 2017})

// list all books that have more than 100 pages but less than 500 pages
db.books.find({pages: {$gt: 100, $lt: 500}})