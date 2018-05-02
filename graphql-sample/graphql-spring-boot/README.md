###  create author
```
mutation Mutation{writeAuthor(id:null, name: "vinson Ding", birthday: "1991-12-12"){
  id  
  name
  birthday
}}
```
###  create book
```
mutation Mutation{newBook(book:{name: "book one", price: 123,authorId:1}){
  id  
  name
  
}}
```
### seach book
```
{recentBooks(count: 10, offset: 0){
  id  
  name
  author{id,name,birthday}
  
}}
```