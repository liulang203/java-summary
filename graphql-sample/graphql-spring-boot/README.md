
## put in graphiql page
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

## POST content
###  create author
```
{"query":"mutation Mutation{writeAuthor(id:null, name: \"vinson Ding\", birthday: \"1991-12-12\"){\n  id  \n  name\n  birthday\n}}","variables":null,"operationName":"Mutation"}
```
###  create book
```
{"query":"mutation Mutation{newBook(book:{name: \"book one\", price: 123,authorId:1}){\n  id  \n  name\n  \n}}","variables":null,"operationName":"Mutation"}
```
### seach book
```
{"query":"{recentBooks(count: 10, offset: 0){\n  id  \n  name\n  author{id,name,birthday}\n  \n}}","variables":null,"operationName":null}
```