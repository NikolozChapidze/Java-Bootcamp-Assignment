[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=8157492&assignment_repo_type=AssignmentRepo)
# OmediaBootcamp-java-lang-week-12-assignment

**ბაზებთან მუშაობის დავალება**

დაწერეთ ანოტაცია ```@Column```, `@Id` და ```@Table```, იმავე პრინციპით როგორც `CSV` ფაილთან მუშაობის დავალებაში გვქონდა
<br/>
<br/>
`@Table` -> უნდა ქონდეს აუცილებელი ველები `scheme` და `name`;  <br/>
`@Column` -> უნდა ქონდეს აუცილებლი `name` ველი; <br/>
`@Id` -> ნიშნავს უბრალოდ შესაბამის ველს Id -ად; <br/>
<br/>

`entityManager` კლასის გამოყენებით უნდა დააიმპლემენტიროთ შემდეგი მეთოდები

`თითოეული მეთოდი უნდა ფუნქციონირებდეს დინამიურად პარამეტრი კლასის მიხედვით, რაც მოცემული კლასის @Table ანოტაციაში წერია იმ თეიბლში უნდა შეასრულოს შესაბამისი ოპერაცია   `

`public boolean insert(Object o )` -> უნდა დააინსერტოს ახალი მონაცემი <br/> 
`public boolean update (Object o )` -> უნდა განაახლოს არსებული მონაცემი // id is required <br/> 
`public boolean delete (Object o )` -> უნდა წაშალოს მონაცემი ობიექტის მიხედვით, // id is required <br/>
`public boolean delete (Class<E>o, id )` -> უნდა წაშალოს მონაცემი id-ის მიხედვით <br/>
`public List<E> select (Class<E>o )` -> უნდა დაასელექტოს შესაბამისი თეიბლი მთლიანად <br/> 
`selectbyId (Class<E>o, id )` -> კონკრედული თეიბლიდან უნდა ამოიღოს შესაბამის id -ზე მონაცემი<br/>
