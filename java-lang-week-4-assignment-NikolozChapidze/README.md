[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7935844&assignment_repo_type=AssignmentRepo)
# java-lang-week-4

---
---

# IMAGE PARSING


**`dev.omedia.image` package-ში**

**დაწერეთ აპლიკაცია**, \
რომელცი კონსოლში მოითხოვს სურათის pathს  `(მაგ me.jpeg)`   
და ასევე რა ტიპის ფაილი უნდათ რომ დაუბრუნდეთ `(IMAGE თუ TXT)` \
`inputs` ვალიდაცია გაუკეთეთ \
და შემდეგ წაიკითხეთ გადმოცემული path-დან სურათი და დააპროცესეთ: 

**თუ სდასაბრუნებელი ტიპი IMAGE** -> \
       თუ პიქსელების `საშუალო` `(red + green + blue  ) /3 >=128` \
      მაშინ სამივე პიქსელი შეავსეთ 255-ით წინააღმდეგ შემთხვევაში 0-ებით,
      ბოლოს კი შეინახავს `me_${UUID}.jpeg`-ად.

**თუ დასაბრუნებელი ტიპი TXT** -> \
      თუ პიქსელების საშუალო `(red + green + blue  ) /3 >=128` \
     მაშინ დასვით . (წერტილი) წინააღმდეგ შემთხვევაში ; (წერტილმძიმე),
     შეინახავს `me_${UUID}.txt` ად
